package org.krugdev.wn8;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;
import org.krugdev.reader.Reader;
import org.krugdev.reader.WotAPIServiceReader;
import org.krugdev.wn8.expected.Parser;
import org.krugdev.wn8.expected.TankExpectedValues;
import org.krugdev.wn8.expected.XmlParser;

public class PlayerIntegrationTest {

	private static final int PLAYER_ID = 6479371;
	
	@Test // integration test
//	@Ignore
	public void shouldCalculatePlayerWN8IntegrationTest() {
		Reader reader = new WotAPIServiceReader("http://localhost:8080/WotRestApp/wotAPI/XBOX-plt/players/");
		Parser parser = new XmlParser();
		Map<Integer, TankExpectedValues> tanksExpectedValues = parser.getTankEx("src/main/resources/expected_tank_values_29.xml");
		PlayerRepository playerRepository = new PlayerRepository(reader, PLAYER_ID, tanksExpectedValues);
		assertEquals(1638.89, playerRepository.calculatePlayersWN8(), 10.00);
	}
	
	@Test // integrationTest
//	@Ignore
	public void shouldCalculateIndividualTankWN8IntegrationTest() {
		Reader reader = new WotAPIServiceReader("http://localhost:8080/WotRestApp/wotAPI/XBOX-plt/players/");
		Parser parser = new XmlParser();
		Map<Integer, TankExpectedValues> tanksExpectedValues = parser.getTankEx("src/main/resources/expected_tank_values_29.xml");
		PlayerRepository playerRepository = new PlayerRepository(reader, PLAYER_ID, tanksExpectedValues);
		double wn8 = playerRepository.calculateIndividualTankWN8(801);
		assertEquals(1510.31, wn8, 10.00);
	}
}
