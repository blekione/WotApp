package org.krugdev.reader;

import static org.junit.Assert.*;

import java.io.Writer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.krugdev.io.WN8DOARepository;
import org.krugdev.wn8.PlayerTanks;
import org.krugdev.wn8.PlayerTanksTimestamp;

public class DatabaseReaderTest {

	private final String PERSISTANCE_UNIT = "testwn8";
	private final int PLAYER_ID = 6479371;
	private EntityManagerFactory emf;
	private EntityManager em;
	
	@Before
	public void setup() {
		emf = Persistence.createEntityManagerFactory(PERSISTANCE_UNIT);
		em = emf.createEntityManager();
	}
	
	@After
	public void cleanup() {
		em.close();
		emf.close();
	}
	
	@Test
	public void shouldAddPlayerTanksToDB() {
		WN8DOARepository reader = new WN8DOARepository(em);
		PlayerTanks player = new PlayerTanksTimestamp(PLAYER_ID);
		reader.savePlayerTanks(player);
	}
}
