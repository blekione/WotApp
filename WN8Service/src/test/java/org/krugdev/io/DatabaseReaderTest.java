package org.krugdev.io;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.krugdev.io.Reader;
import org.krugdev.io.WN8Service;
import org.krugdev.io.utility.SessionUtil;
import org.krugdev.wn8.PlayerTanks;
import org.krugdev.wn8.PlayerTanksTimestamp;
import org.krugdev.wn8.TankItem;

public class DatabaseReaderTest {

	private static final String PERSISTENCE_UNIT = "testwn8";
	private final int PLAYER_ID = 6479371;
	private final double ANY_WN8 = 1678;
	
	EntityManagerFactory emf;
	Session session;
	WN8Service service;
	Reader apiServiceReader;
	List<TankItem> tankItems;
	
	@Before
	public void setup() {
		emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		session = SessionUtil.getSession(emf); 
		service = new WN8Service(session);
		tankItems = new ArrayList<TankItem>();
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
		assertEquals(playerTanks, service.findLatestPlayerTanks(PLAYER_ID));
	}
	
	@Test
	public void shouldGetLatestPlayerTanksIfMoreThanOnePlayer() {
		// adds some db entries
		addTanksTimeStampsInDB(2, PLAYER_ID, 56478);
		// adds test entry
		PlayerTanks testTanks = addTanksTimeStampsInDB(1, PLAYER_ID).get(0);
		PlayerTanks playerTanksFromDB = service.findLatestPlayerTanks(PLAYER_ID);
		assertEquals(testTanks, playerTanksFromDB);
	}
	
	@Test(expected=NoResultException.class)
	public void shouldThrowNoResultExceptionIfNoPlayersTanksRecordInDatabase() {
		// adds any entry
		addTanksTimeStampsInDB(1, PLAYER_ID);
		// check for not existing entry
		service.findLatestPlayerTanks(12345);
	}

	@Test
	public void shouldGetAllPlayerTanks() {
		addTanksTimeStampsInDB(3, PLAYER_ID, 123654);
		List<PlayerTanksTimestamp> testPlayerTanksList = service.findPlayerTanks(PLAYER_ID);
		assertEquals(3, testPlayerTanksList.size());
	}
	
	@Test
	public void shouldRemoveLatestPlayerTanks() {
		//TODO complete the test - need first implement shouldGetAllPlayerTanks() test
		addTanksTimeStampsInDB(3, PLAYER_ID);
		service.removeLatestPlayerTanks(PLAYER_ID);
		List<PlayerTanksTimestamp> testPlayerTanksList = service.findPlayerTanks(PLAYER_ID);
		assertEquals(2, testPlayerTanksList.size());
	}
	
	private List<PlayerTanks> addTanksTimeStampsInDB(int amount, int... playerIds) {
		List<PlayerTanks> playerTanksList = new ArrayList<>();
		for (int i = 0; i < amount; i++) {
			for (int j = 0; j < playerIds.length; j++) {
				PlayerTanks playerTanks = new PlayerTanksTimestamp(playerIds[j], tankItems, ANY_WN8);
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
