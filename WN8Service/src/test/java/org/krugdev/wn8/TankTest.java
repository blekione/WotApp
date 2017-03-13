package org.krugdev.wn8;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.krugdev.wn8.XML.TankItem;
import org.krugdev.wn8.XML.TankItemBuilder;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import static org.mockito.Mockito.*;

public class TankTest {
	
	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();
	private TankItemBuilder tankItemBuilder;
	
	@Before
	public void setUp() {
		PlayerTanks player = mock(PlayerTanks.class);
		tankItemBuilder = new TankItemBuilder(player, 801)
				.gamesCount(250)
				.frags(269)
				.damageDealt(203644)
				.spottedTanks(212)
				.defencePoints(415)
				.winRatio(51.2);
	}
	
	@Test
	public void shouldCreateTankItemFromBuilder() {
		TankItem tankitem = tankItemBuilder.build();
		assertTrue(tankitem instanceof TankItem);
	}
	
}
