package org.krugdev.rservice;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.krugdev.io.WN8DBService;
import org.krugdev.io.WotAPIServiceReader;
import org.krugdev.rservice.domain.PlayerWN8;
import org.krugdev.util.Platform;
import org.krugdev.wn8.db.DBTankItem;
import org.krugdev.wn8.db.PlayerTimestamp;
import org.krugdev.wn8.db.PlayerTimestampRepository;

@Stateless
public class PlayerWN8Resource implements PlayerWN8ResourceRESTAnnotations {

	private static final String WOT_API_SERVICE_URI = "http://localhost:8080/WotRestApp/wotAPI/XBOX-plt/players/";
	
	Platform platform;
	WN8DBService service;
	List<DBTankItem> tankItems;
	
	@PersistenceContext(unitName="servicewn8")
	private EntityManager em;
	private Session session;
	
	public PlayerWN8Resource() {
	}
	
	public PlayerWN8Resource(Platform platform) {
		this.platform = platform;
	}

	@Override
	public PlayerWN8 getPlayerWN8(String playerIdString) {
		this.session = em.unwrap(Session.class);
		Integer playerId = Integer.valueOf(playerIdString);
		WN8DBService dbReader = new WN8DBService(session);
		WotAPIServiceReader apiReader = new WotAPIServiceReader(WOT_API_SERVICE_URI);
		PlayerTimestampRepository playerTimestampRep = new PlayerTimestampRepository(dbReader, session);
		PlayerTimestamp latestPlayerTanksTimestamp = playerTimestampRep.getUpToDatePlayerTanksItems(playerId, apiReader); 
		return new PlayerWN8(latestPlayerTanksTimestamp.getTotalWN8(), playerIdString);
	}
	
}
