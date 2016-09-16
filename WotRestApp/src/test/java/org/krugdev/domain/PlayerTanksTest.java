package org.krugdev.domain;

import static org.junit.Assert.*;

import org.junit.Test;
import org.krugdev.domain.playerTanks.PlayerTanks;

public class PlayerTanksTest {

	@Test
	public void testIfPlayersTanksListIsSet() {
		PlayerTanks playerTanks = PlayerTanks.getTanks(Platforms.XBOX, "6479371");
		
		assertTrue(29 < playerTanks.getTanksCounter());
	}

}
