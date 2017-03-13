package org.krugdev.rservice.domain;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.krugdev.wn8.XML.TankItem;
import org.krugdev.wn8.XML.TankItemBuilder;
import org.krugdev.wn8.db.PlayerTimestamp;
import org.krugdev.wn8.expected.TankExpectedValues;
import org.krugdev.wn8.expected.TankExpectedValuesReader;
import org.krugdev.wn8.expected.XMLTankExpectedValuesReader;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SessionWN8RepositoryTest {

	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	@Test
	public void shouldReturnSessionWN8GivenStartAndEndPlayerTimestamp() {
		PlayerTimestamp playerTimestampStart = new PlayerTimestamp(12345, null, 1000);
		TankItem tankItem1 = new TankItemBuilder(playerTimestampStart, 801)
				.damageDealt(203644)
				.defencePoints(415)
				.frags(269)
				.gamesCount(250)
				.spottedTanks(212)
				.winRatio(51.2)
				.build();
		TankItem tankItem2 = new TankItemBuilder(playerTimestampStart, 3121)
				.damageDealt(11031)
				.defencePoints(136)
				.frags(28)
				.gamesCount(38)
				.spottedTanks(120)
				.winRatio(52.63)
				.build();
		
		Map<Integer, TankItem> dbTankItemsStart = new HashMap<>();
		dbTankItemsStart.put(tankItem1.getTankId(), tankItem1);
		dbTankItemsStart.put(tankItem2.getTankId(),	tankItem2);		
		
		tankItem1 = new TankItemBuilder(playerTimestampStart, 801)
				.damageDealt(253644)
				.defencePoints(415)
				.frags(276)
				.gamesCount(255)
				.spottedTanks(223)
				.winRatio(51.4)
				.build();
		
		tankItem2 = new TankItemBuilder(playerTimestampStart, 3121)
				.damageDealt(16031)
				.defencePoints(145)
				.frags(34)
				.gamesCount(44)
				.spottedTanks(130)
				.winRatio(52.51)
				.build();
		List<TankItem> dbTankItemsEnd = Arrays.asList(tankItem1, tankItem2);
		
		String tankExpValuesXML = "src/main/resources/expected_tank_values_29.xml";
		TankExpectedValuesReader parser = new XMLTankExpectedValuesReader();
		Map<Integer, TankExpectedValues> tankExpValues = parser.getTankEx(tankExpValuesXML);
		
		SessionWN8Repository sessionWN8Repository = new SessionWN8Repository(tankExpValues, 12345);
		
		SessionWN8 sessionWN8 = sessionWN8Repository.getSessionWN8(dbTankItemsStart, dbTankItemsEnd);
		
		assertEquals(11, sessionWN8.getGamesCount());
		assertEquals(2, sessionWN8.getTanksPlayed().size());
	}
}
