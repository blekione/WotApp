package org.krugdev;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.krugdev.auxiliary.Platform;
import org.krugdev.auxiliary.ResourceNotFoundException;
import org.krugdev.domain.playerTanks.PlayersTanks;

public class PlayerTanksTest {

	private static PlayersTanks playersTanks;
	
	@BeforeClass
	public static void setPlayersTanks() throws ResourceNotFoundException {
		playersTanks = new PlayersTanks();
		playersTanks.getFromAPI(Platform.XBOX, "6479371");	
	}
	
	@Test
	public void testIfPlayersTanksListIsSet() {
		assertTrue(29 < playersTanks.getTanksCounter());
	}
	
	@Test
	public void testIfTankWinRatioIsCalculatedProperly() {
		assertTrue(40.0 < playersTanks.getTanks().get(0).getWinRatio());
	}

}
