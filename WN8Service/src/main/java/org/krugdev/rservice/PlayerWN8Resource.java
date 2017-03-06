package org.krugdev.rservice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.krugdev.io.WN8DBService;
import org.krugdev.io.WotAPIServiceReader;
import org.krugdev.rservice.domain.PlayerWN8;
import org.krugdev.util.Platform;
import org.krugdev.wn8.WN8Repository;
import org.krugdev.wn8.XML.TankItem;
import org.krugdev.wn8.db.DBTankItem;
import org.krugdev.wn8.db.PlayerTanksTimestamp;
import org.krugdev.wn8.expected.TankExpectedValuesReader;
import org.krugdev.wn8.expected.DBTankExpectedValuesReader;
import org.krugdev.wn8.expected.TankExpectedValues;

@Stateless
public class PlayerWN8Resource implements PlayerWN8ResourceRESTAnnotations {

	private static final String WOT_API_SERVICE_URI = "http://localhost:8080/WotRestApp/wotAPI/XBOX-plt/players/";
	
	Platform platform;
	WN8DBService service;
	List<DBTankItem> tankItems;
	
	@PersistenceContext(unitName="servicewn8")
	private EntityManager em;
	
	public PlayerWN8Resource() {
	}
	
	public PlayerWN8Resource(Platform platform) {
		this.platform = platform;
	}

	@Override
	public PlayerWN8 getPlayerWN8(String playerIdString) {
		Session session = em.unwrap(Session.class);
		service = new WN8DBService(session);
		PlayerTanksTimestamp latestPlayerTanksTimestamp = getUpToDatePlayerTanksItems(playerIdString); 
		return new PlayerWN8(latestPlayerTanksTimestamp.getTotalWN8(), playerIdString);
	}
	
	private PlayerTanksTimestamp getUpToDatePlayerTanksItems(String playerIdString ) {
		Integer playerId = Integer.valueOf(playerIdString);
		Session session = em.unwrap(Session.class);
		WN8DBService dbReader = new WN8DBService(session);
		WotAPIServiceReader apiReader = new WotAPIServiceReader(WOT_API_SERVICE_URI);		
		PlayerTanksTimestamp lastPlayerTanksTimestamp;
		List<PlayerTanksTimestamp> dbLastPlayerTanksTimestamps = 
				dbReader.findTwoLastPlayerTanksTimestamps(playerId);
		if (dbLastPlayerTanksTimestamps.isEmpty()						// no tank timestamp for pl;ayer in db  
			|| dbLastPlayerTanksTimestamps.get(0).getTimestamp()			// check if last timestamp is older than 30 min
				.compareTo(LocalDateTime.now().minusMinutes(1)) < 0) {
			List<TankItem> playerTanks = apiReader.getPlayerTanks(playerId);
			List<DBTankItem> dbTanksList = new ArrayList<>();
			for (TankItem item : playerTanks) {
				dbTanksList.add(DBTankItem.instanceOf(item));
			}
			TankExpectedValuesReader parser = new DBTankExpectedValuesReader();
			
			Map<Integer, TankExpectedValues> tanksExpectedValues = parser.getTankEx(session);
			WN8Repository wn8Repository = new WN8Repository(apiReader, playerId, tanksExpectedValues);
			double wn8 = wn8Repository.calculateForPlayers();
			lastPlayerTanksTimestamp = new PlayerTanksTimestamp(playerId, dbTanksList, wn8);
			System.out.println(dbLastPlayerTanksTimestamps.size());
			if (dbLastPlayerTanksTimestamps.size() > 1 &&
				dbLastPlayerTanksTimestamps.get(0).getTimestamp()			
					.compareTo(dbLastPlayerTanksTimestamps.get(1).getTimestamp().plusWeeks(1)) < 0) {
				dbReader.removeLatestPlayerTanks(playerId);
			}
		dbReader.savePlayerTanks(lastPlayerTanksTimestamp);
			return lastPlayerTanksTimestamp;
		} else {
			return dbLastPlayerTanksTimestamps.get(0);
		}		
	}
}
