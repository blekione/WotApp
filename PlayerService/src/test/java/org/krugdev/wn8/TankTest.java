package org.krugdev.wn8;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import static org.mockito.Mockito.*;

public class TankTest {
	
	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();
	private TankItemBuilder tankItemBuilder;
	
	@Before
	public void setUp() {
		Player player = mock(Player.class);
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
	
	@Test
	public void shouldCalculateWn8Rating() {
		TankExpectedValues tankExpVal = new TankExpectedValues(1.00, 824.92, 1.07, 0.85, 53.75);
		double wn8Value = TankUtils.calculateWN8(tankItemBuilder.build(), tankExpVal);
		assertEquals(1510.35, wn8Value, 10.0);
	}
	
}
