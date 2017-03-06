package org.krugdev.io;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.krugdev.io.Reader;
import org.krugdev.io.WN8DBService;
import org.krugdev.io.SessionUtil;
import org.krugdev.wn8.PlayerTanks;
import org.krugdev.wn8.db.DBTankItem;
import org.krugdev.wn8.db.PlayerTimestamp;

public class DatabaseReaderTest {

	private static final String PERSISTENCE_UNIT = "testwn8";
	private final int PLAYER_ID = 6479371;
	private final double ANY_WN8 = 1678;
	
	EntityManagerFactory emf;
	Session session;
	WN8DBService service;
	Reader apiServiceReader;
	List<DBTankItem> tankItems;
	
	@Before
	public void setup() {
		emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		session = SessionUtil.getSession(emf); 
		service = new WN8DBService(session);
		tankItems = new ArrayList<DBTankItem>();
		
	}
	
	@After
	public void cleanup() {
		session.close();
		emf.close();
	}
	
	@Test
	public void shouldAddPlayerTimetstampToDB() {		
		addTanksTimeStampsInDB(1, PLAYER_ID);
	}
	
	@Test
	public void shouldGetOptionalWithLatestPlayerTimestampFromDB() {
		PlayerTanks playerTanks = addTanksTimeStampsInDB(3, PLAYER_ID).get(2);
		Optional<PlayerTimestamp> queryResult = service.findLatestPlayerTanksTimestamp(PLAYER_ID);
		assertEquals(playerTanks, queryResult.get());
	}
	
	@Test
	public void shouldGetLatestPlayerTimestampIfMoreThanOnePlayer() {
		// adds some db entries
		addTanksTimeStampsInDB(2, PLAYER_ID, 56478);
		// adds test entry
		PlayerTanks testTanks = addTanksTimeStampsInDB(1, PLAYER_ID).get(0);
		PlayerTanks playerTimestampFromDB = service.findLatestPlayerTanksTimestamp(PLAYER_ID).get();
		assertEquals(testTanks, playerTimestampFromDB);
	}
	
	@Test
	public void shouldReturnEmptyOptionalIfNoPlayersTimestampInDatabase() {
		// adds any entry
		addTanksTimeStampsInDB(1, PLAYER_ID);
		// check for not existing entry
		Optional<PlayerTimestamp> queryResult = service.findLatestPlayerTanksTimestamp(12345);
		assertFalse(queryResult.isPresent());
	}

	@Test
	public void shouldGetAllPlayerTimestamp() {
		addTanksTimeStampsInDB(3, PLAYER_ID, 123654);
		List<PlayerTimestamp> testPlayerTimestamps = service.findPlayerTanksTimestamps(PLAYER_ID);
		assertEquals(3, testPlayerTimestamps.size());
	}

	@Test
	public void shouldRemoveLatestPlayerTimestamp() {
		addTanksTimeStampsInDB(3, PLAYER_ID);
		service.removeLatestPlayerTimestamp(PLAYER_ID);
		List<PlayerTimestamp> testPlayerTimestamps= service.findPlayerTanksTimestamps(PLAYER_ID);
		assertEquals(2, testPlayerTimestamps.size());
	}

	@Test
	public void shouldGetLastTwoPlayerTimestamps() {
		addTanksTimeStampsInDB(1, PLAYER_ID, 12345);
		PlayerTanks tanksTimestampBeforeLast = addTanksTimeStampsInDB(1, PLAYER_ID).get(0);
		PlayerTanks tanksTimestampLast = addTanksTimeStampsInDB(1, PLAYER_ID).get(0);		
		List<PlayerTimestamp> lastTwoPlayerTanksTimestamps = 
				service.findTwoLastPlayerTanksTimestamps(PLAYER_ID);
		assertEquals(2, lastTwoPlayerTanksTimestamps.size());
		assertEquals(tanksTimestampLast, lastTwoPlayerTanksTimestamps.get(0));
		assertEquals(tanksTimestampBeforeLast, lastTwoPlayerTanksTimestamps.get(1));
	}
	
	@Test
	public void shouldReplaceLastPlayerTimestampByTheNewOne() {
		addTanksTimeStampsInDB(3, PLAYER_ID, 12345);
		PlayerTanks newPlayerTimestamp = getNewPlayerTimestamp(PLAYER_ID);
		service.replaceLastPlayerTimestamp(PLAYER_ID, (PlayerTimestamp)newPlayerTimestamp);
		assertEquals(newPlayerTimestamp, service.findLatestPlayerTanksTimestamp(PLAYER_ID).get());
	}
	
	private List<PlayerTanks> addTanksTimeStampsInDB(int amount, int... playerIds) {
		List<PlayerTanks> playerTanksList = new ArrayList<>();
		for (int i = 0; i < amount; i++) {
			for (int j = 0; j < playerIds.length; j++) {
				PlayerTanks playerTanks = getNewPlayerTimestamp(playerIds[j]);
				service.savePlayerTanks(playerTanks);
				playerTanksList.add(playerTanks);
				try {
					Thread.sleep(1000); // to make timestamps with different time
				} catch (InterruptedException e) {
					System.out.println("thread has been interrupted when sleeping for 1s");
				}
			}
		}		
		return playerTanksList;
	}
	
	private PlayerTanks getNewPlayerTimestamp(int playerId) {
		DBTankItem tankItem1 = new DBTankItem();
		DBTankItem tankItem2 = new DBTankItem();
		tankItems = Arrays.asList(tankItem1, tankItem2);
		PlayerTanks playerTanks = new PlayerTimestamp(playerId, tankItems, ANY_WN8);
		tankItem1.setPlayer((PlayerTimestamp) playerTanks);
		tankItem2.setPlayer((PlayerTimestamp) playerTanks);
		return playerTanks;
	}
}
