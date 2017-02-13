package org.krugdev.wn8;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.krugdev.reader.Reader;
import org.krugdev.wn8.expected.TankExpectedValues;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerTest {
	
	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	private static final int PLAYER_ID = 6479371;
	private static final Player ANY_PLAYER = mock(Player.class);
	private static final Reader READER = mock(Reader.class);
	private List<TankItem> tankItems;
	private Map<Integer, TankExpectedValues> tanksExpectedVal;

	private TankItem tankItemA;
	private TankItem tankItemB; 
	
	
	@Before
	public void setUp() {

		 tankItemA = new TankItemBuilder(ANY_PLAYER, 801)
				.gamesCount(250)
				.frags(269)
				.damageDealt(203644)
				.spottedTanks(212)
				.defencePoints(415)
				.winRatio(51.2)
				.build();
		tankItemB = new TankItemBuilder(ANY_PLAYER, 769)
				.gamesCount(76)
				.frags(64)
				.damageDealt(16276)
				.spottedTanks(239)
				.defencePoints(131)
				.winRatio(52.63)
				.build();
		TankItem tankItemC = new TankItemBuilder(ANY_PLAYER, 10785)
				.gamesCount(93)
				.frags(91)
				.damageDealt(193873)
				.spottedTanks(160)
				.defencePoints(87)
				.winRatio(46.23)
				.build();
		tankItems = Arrays.asList(tankItemA,tankItemB, tankItemC);
		
		tanksExpectedVal = new HashMap<>();
		TankExpectedValues tankExpVal1 = new TankExpectedValues(1.00, 824.92, 1.07, 0.85, 53.75);
		TankExpectedValues tankExpVal2 = new TankExpectedValues(0.81, 208.34, 2.00, 0.98, 54.05);
		TankExpectedValues tankExpVal3 = new TankExpectedValues(0.91, 1888.88, 1.27, 0.69, 48.75);
		tanksExpectedVal.put(801, tankExpVal1);
		tanksExpectedVal.put(769, tankExpVal2);
		tanksExpectedVal.put(10785, tankExpVal3);
		
	}
	
	@Test
	public void shouldCalculatePlayerWN8() {
		
		when(READER.getPlayerTanks(PLAYER_ID)).thenReturn(tankItems);
		
		PlayerRepository playerRepository = new PlayerRepository(READER, PLAYER_ID, tanksExpectedVal);
		
		double playerWn8 = playerRepository.calculatePlayersWN8();
		assertEquals(1679.83, playerWn8, 2.00);
	}
	
	@Test
	public void shouldCalculateWN8ForIndividualTank() {
		when(READER.getPlayerTanks(PLAYER_ID)).thenReturn(tankItems);
		PlayerRepository playerRepository = new PlayerRepository(READER, PLAYER_ID, tanksExpectedVal);
		
		double tankWN8 = playerRepository.calculatePlayersIndividualTankWN8(801);
		assertEquals(1510.35, tankWN8, 2.00);
	}
	
	@Test
	public void shouldCalculateWN8ForManyINdividualTanks() {
		when(READER.getPlayerTanks(PLAYER_ID)).thenReturn(tankItems);
		PlayerRepository playerRepository = new PlayerRepository(READER, PLAYER_ID, tanksExpectedVal);
		
		assertEquals(1510.35, playerRepository.calculatePlayersIndividualTankWN8(801), 2.00);
		assertEquals(1652.35, playerRepository.calculatePlayersIndividualTankWN8(769), 2.00);
		assertEquals(1800.18, playerRepository.calculatePlayersIndividualTankWN8(10785), 2.00);
	}
	
	@Test(expected = IllegalArgumentException.class) 
	public void shouldThrowIllegalArgumentExcIfCantFindTankWithID() {
		List<TankItem> tankItems = Arrays.asList(tankItemA, tankItemB);
		when(READER.getPlayerTanks(PLAYER_ID)).thenReturn(tankItems);
		PlayerRepository playerRepository = new PlayerRepository(READER, PLAYER_ID, tanksExpectedVal);
		playerRepository.calculatePlayersIndividualTankWN8(10785);
	}
}