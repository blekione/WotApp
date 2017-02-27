package org.krugdev.io;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import org.hibernate.LazyInitializationException;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.krugdev.io.Reader;
import org.krugdev.io.WN8DBService;
import org.krugdev.io.utility.SessionUtil;
import org.krugdev.wn8.PlayerTanks;
import org.krugdev.wn8.db.DBTankItem;
import org.krugdev.wn8.db.PlayerTanksTimestamp;

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
	public void shouldAddPlayerTanksTimestampToDB() {		
		addTanksTimeStampsInDB(1, PLAYER_ID);
	}
	
	@Test
	public void shouldGetLatestPlayerTanksFromDB() {
		PlayerTanks playerTanks = addTanksTimeStampsInDB(3, PLAYER_ID).get(2);
		assertEquals(playerTanks, service.findLatestPlayerTanksTimestamp(PLAYER_ID));
	}
	
	@Test
	public void shouldGetLatestPlayerTanksIfMoreThanOnePlayer() {
		// adds some db entries
		addTanksTimeStampsInDB(2, PLAYER_ID, 56478);
		// adds test entry
		PlayerTanks testTanks = addTanksTimeStampsInDB(1, PLAYER_ID).get(0);
		PlayerTanks playerTanksFromDB = service.findLatestPlayerTanksTimestamp(PLAYER_ID);
		assertEquals(testTanks, playerTanksFromDB);
	}
	
	@Test(expected=NoResultException.class)
	public void shouldThrowNoResultExceptionIfNoPlayersTanksRecordInDatabase() {
		// adds any entry
		addTanksTimeStampsInDB(1, PLAYER_ID);
		// check for not existing entry
		service.findLatestPlayerTanksTimestamp(12345);
	}

	@Test
	public void shouldGetAllPlayerTanks() {
		addTanksTimeStampsInDB(3, PLAYER_ID, 123654);
		List<PlayerTanksTimestamp> testPlayerTanksList = service.findPlayerTanksTimestamps(PLAYER_ID);
		assertEquals(3, testPlayerTanksList.size());
	}
	
	@Test
	public void shouldRemoveLatestPlayerTanks() {
		addTanksTimeStampsInDB(3, PLAYER_ID);
		service.removeLatestPlayerTanks(PLAYER_ID);
		List<PlayerTanksTimestamp> testPlayerTanksList = service.findPlayerTanksTimestamps(PLAYER_ID);
		assertEquals(2, testPlayerTanksList.size());
	}

	private List<PlayerTanks> addTanksTimeStampsInDB(int amount, int... playerIds) {
		List<PlayerTanks> playerTanksList = new ArrayList<>();
		for (int i = 0; i < amount; i++) {
			for (int j = 0; j < playerIds.length; j++) {
				DBTankItem tankItem1 = new DBTankItem();
				DBTankItem tankItem2 = new DBTankItem();
				tankItems = Arrays.asList(tankItem1, tankItem2);
				PlayerTanks playerTanks = new PlayerTanksTimestamp(playerIds[j], tankItems, ANY_WN8);
				tankItem1.setPlayer((PlayerTanksTimestamp) playerTanks);
				tankItem2.setPlayer((PlayerTanksTimestamp) playerTanks);				
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
}
