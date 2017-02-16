package org.krugdev.reader;

import static org.junit.Assert.*;

import java.io.Writer;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.krugdev.io.Reader;
import org.krugdev.io.WN8DOARepository;
import org.krugdev.io.WotAPIServiceReader;
import org.krugdev.wn8.PlayerTanks;
import org.krugdev.wn8.PlayerTanksTimestamp;
import org.krugdev.wn8.TankItem;

public class DatabaseReaderTest {

	private final String PERSISTANCE_UNIT = "testwn8";
	public static final String SERVICE_URI_LOCATION = "http://localhost:8080/WotRestApp/wotAPI/XBOX-plt/players/";
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
	public void shouldAddPlayerTanksTimestampToDB() {
		WN8DOARepository reader = new WN8DOARepository(em);
		Reader serviceReader = new WotAPIServiceReader(SERVICE_URI_LOCATION);
		List<TankItem> tankItems = serviceReader.getPlayerTanks(PLAYER_ID);
		PlayerTanks player = new PlayerTanksTimestamp(PLAYER_ID, tankItems);
		reader.savePlayerTanks(player);
	}
}
