package org.krugdev;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.krugdev.auxiliary.Platform;
import org.krugdev.auxiliary.ResourceNotFoundException;
import org.krugdev.domain.playerTanks.TankItemsProcessor;
import org.krugdev.domain.playerTanks.TankItem;

public class TankItemsProcessorTest {

	private static List<TankItem> playersTanks;
	
	@BeforeClass
	public static void setPlayersTanks() throws ResourceNotFoundException {
		playersTanks = TankItemsProcessor.getFromAPI(Platform.XBOX, 6479371);	
	}
	
	@Test
	public void testIfPlayersTanksListIsSet() {
		assertTrue(29 < playersTanks.size());
	}
	
	@Test
	public void testIfTankWinRatioIsCalculatedProperly() {
		assertTrue(40.0 < playersTanks.get(0).getWinRatio());
	}

}
