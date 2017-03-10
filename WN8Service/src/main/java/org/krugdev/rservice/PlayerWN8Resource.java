package org.krugdev.rservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.GenericEntity;

import org.hibernate.Session;
import org.krugdev.io.WN8DBService;
import org.krugdev.io.WotAPIServiceReader;
import org.krugdev.rservice.domain.PlayerTankWN8;
import org.krugdev.rservice.domain.PlayerWN8;
import org.krugdev.util.Platform;
import org.krugdev.wn8.db.DBTankItem;
import org.krugdev.wn8.db.PlayerTimestamp;
import org.krugdev.wn8.db.PlayerTimestampRepository;

@Stateless
public class PlayerWN8Resource implements PlayerWN8ResourceRESTAnnotations {
	
	private Platform requestPlatform;
	
	Map<Integer, String> sessionWN8 = new HashMap<>();
	
	@PersistenceContext(unitName="servicewn8")
	private EntityManager em;
	private Session session;
	
	public PlayerWN8Resource() {
	}
	
	@Override
	public PlayerWN8 getPlayerWN8(String platform, int playerId) {
		this.requestPlatform = Platform.setPlatform(platform);
		PlayerTimestamp latestPlayerTanksTimestamp = getLatestPlayerTimestamp(playerId);
		return new PlayerWN8(latestPlayerTanksTimestamp.getTotalWN8(), playerId);
	}
	
	@Override
	public GenericEntity<List<PlayerTankWN8>> getPlayerTanksWN8(String platform, int playerId) {
		this.requestPlatform = Platform.setPlatform(platform);
		System.out.println(requestPlatform);
		PlayerTimestamp latestPlayerTanksTimestamp = getLatestPlayerTimestamp(playerId);
		List<DBTankItem> dbTankItems = latestPlayerTanksTimestamp.getDbTankItems();
		List<PlayerTankWN8> tanksWN8 = new ArrayList<>();
		for (DBTankItem dbTankItem : dbTankItems) {
			tanksWN8.add(new PlayerTankWN8(dbTankItem.getTankId(), dbTankItem.getTankWN8()));
		}
		GenericEntity<List<PlayerTankWN8>> list = new GenericEntity<List<PlayerTankWN8>>(tanksWN8) {};
		return list;	
	}

	@Override
	public void startNewWN8Session(String platform, int playerId, int sessionId) {
		sessionWN8.put(1, "test");
		System.out.println("im here");
	}

	
	private PlayerTimestamp getLatestPlayerTimestamp(int playerId) {
		this.session = em.unwrap(Session.class);
		WN8DBService dbReader = new WN8DBService(session);
		WotAPIServiceReader apiReader = new WotAPIServiceReader(requestPlatform.getUrl());
		PlayerTimestampRepository playerTimestampRep = new PlayerTimestampRepository(dbReader, session);
		return playerTimestampRep.getUpToDatePlayerTanksItems(playerId, apiReader); 
	}

	@Override
	public void getWN8Session(String platform, int playerId, int sessionId) {
		System.out.println(sessionWN8.get(1));
	}

}
