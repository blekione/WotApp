package org.krugdev.rservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import org.hibernate.Session;
import org.krugdev.io.Reader;
import org.krugdev.io.WN8DBService;
import org.krugdev.io.WotAPIServiceReader;
import org.krugdev.rservice.domain.PlayerTankWN8;
import org.krugdev.rservice.domain.PlayerWN8;
import org.krugdev.rservice.domain.SessionWN8;
import org.krugdev.rservice.domain.SessionWN8Repository;
import org.krugdev.util.Platform;
import org.krugdev.wn8.WN8Repository;
import org.krugdev.wn8.XML.TankItem;
import org.krugdev.wn8.XML.TankItemBuilder;
import org.krugdev.wn8.db.DBTankItem;
import org.krugdev.wn8.db.PlayerTimestamp;
import org.krugdev.wn8.db.PlayerTimestampRepository;
import org.krugdev.wn8.expected.DBTankExpectedValuesReader;
import org.krugdev.wn8.expected.TankExpectedValues;
import org.krugdev.wn8.expected.TankExpectedValuesReader;

@Stateless
public class PlayerWN8Resource implements PlayerWN8ResourceRESTAnnotations {
	
	private Platform requestPlatform;
	
	Map<Integer, Map<Integer, TankItem>> sessionWN8PlayerTankItems = new HashMap<>();
	
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


	private PlayerTimestamp getLatestPlayerTimestamp(int playerId) {
		this.session = em.unwrap(Session.class);
		WN8DBService dbReader = new WN8DBService(session);
		WotAPIServiceReader apiReader = new WotAPIServiceReader(requestPlatform.getUrl());
		PlayerTimestampRepository playerTimestampRep = new PlayerTimestampRepository(dbReader, session);
		return playerTimestampRep.getUpToDatePlayerTanksItems(playerId, apiReader); 
	}

	
	@Override
	public Response startNewWN8Session(String platform, int playerId, int sessionId) {
		this.requestPlatform = Platform.setPlatform(platform);
		PlayerTimestamp sessionStartPlayerTimestamp = getLatestPlayerTimestamp(playerId);
		Map<Integer, TankItem> sessionTankItems = 
			sessionStartPlayerTimestamp.getTankItems()
				.stream()
				.collect(Collectors.toMap(TankItem::getTankId, Function.identity()));
		sessionWN8PlayerTankItems.put(sessionId, sessionTankItems);
		return Response.ok().build();
	}

	
	@Override
	public SessionWN8 getWN8Session(String platform, int playerId, int sessionId) {
		this.requestPlatform = Platform.setPlatform(platform);
		Map<Integer, TankItem> sessionStartTankItems = sessionWN8PlayerTankItems.get(sessionId);
		List<TankItem> latestTankItems = getLatestPlayerTimestamp(playerId).getTankItems();
		TankExpectedValuesReader parser = new DBTankExpectedValuesReader();		
		Map<Integer, TankExpectedValues> tankExpectedValues = parser.getTankEx(em.unwrap(Session.class));
		SessionWN8Repository sessionWN8Repository = new SessionWN8Repository(tankExpectedValues, playerId);
		SessionWN8 sessionWN8 = sessionWN8Repository.getSessionWN8(sessionStartTankItems, latestTankItems);
		return sessionWN8;
	}

	@Override
	public SessionWN8 finaliseWN8Session(String platform, int playerId, int sessionId) {
		this.requestPlatform = Platform.setPlatform(platform);
		sessionWN8PlayerTankItems.remove(sessionId);
		System.out.println(sessionWN8PlayerTankItems.size());
		return null;
	}

}
