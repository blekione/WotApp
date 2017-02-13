package org.krugdev.wn8;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.krugdev.reader.Reader;
import org.krugdev.wn8.expected.TankExpectedValues;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PlayerTest {
	
	private static final int PLAYER_ID = 6479371;

	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	@Test
	public void shouldCalculatePlayerWN8() {
		Reader reader = mock(Reader.class);
		Player player = mock(Player.class);
		
		TankItem tankitemA = new TankItemBuilder(player, 801)
				.gamesCount(250)
				.frags(269)
				.damageDealt(203644)
				.spottedTanks(212)
				.defencePoints(415)
				.winRatio(51.2)
				.build();
		TankItem tankitemB = new TankItemBuilder(player, 769)
				.gamesCount(76)
				.frags(64)
				.damageDealt(16276)
				.spottedTanks(239)
				.defencePoints(131)
				.winRatio(52.63)
				.build();
		TankItem tankitemC = new TankItemBuilder(player, 10785)
				.gamesCount(93)
				.frags(91)
				.damageDealt(193873)
				.spottedTanks(160)
				.defencePoints(87)
				.winRatio(46.23)
				.build();
		
		Map<Integer, TankExpectedValues> tanksExpectedVal = new HashMap<>();
		TankExpectedValues tankExpVal1 = new TankExpectedValues(1.00, 824.92, 1.07, 0.85, 53.75);
		TankExpectedValues tankExpVal2 = new TankExpectedValues(0.81, 208.34, 2.00, 0.98, 54.05);
		TankExpectedValues tankExpVal3 = new TankExpectedValues(0.91, 1888.88, 1.27, 0.69, 48.75);
		tanksExpectedVal.put(801, tankExpVal1);
		tanksExpectedVal.put(769, tankExpVal2);
		tanksExpectedVal.put(10785, tankExpVal3);
		
		PlayerRepository playerRepository = new PlayerRepository(reader, PLAYER_ID, tanksExpectedVal);
		
		when(reader.getPlayerTanks(PLAYER_ID)).thenReturn(Arrays.asList(tankitemA, tankitemB, tankitemC));
		
		double playerWn8 = playerRepository.calculateWN8();
		System.out.println(playerWn8);
		assertEquals(1598.54, playerWn8, 5.00);
	}

}