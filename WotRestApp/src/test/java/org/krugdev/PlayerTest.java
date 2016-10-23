package org.krugdev;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.krugdev.auxiliary.Platform;
import org.krugdev.auxiliary.ResourceNotFoundException;
import org.krugdev.domain.player.Player;
import org.krugdev.domain.player.PlayerProcessor;

public class PlayerTest {

	private static Player player;
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@BeforeClass
	public static void setPlayer() {
		try {
			player = PlayerProcessor.getFromAPI(Platform.XBOX, 6479371);
		} catch (ResourceNotFoundException e){
			fail("player not found");
		}
	}
	
	@Test
	public void testIfPlayerIsReceived() {
		
		assertEquals("Mr Flen", player.getNickname());
	}
	
	@Test
	public void testIfDaysInGameAreSetCorrectly() {
		Date date = new Date();
		int expectedValue = (int)((date.getTime() / 1000) - 1438330823) / 86400;
		assertEquals(expectedValue, player.getDaysInGame());
	}

	@Test
	public void testIfGamesCountersAreSet() {
		assertTrue(5000 < player.getGamesPlayedCounter());
	}
	
	@Test
	public void testIfKillsDeathsAreSet() {
		assertTrue(5000l < player.getKills());
	}
	
	@Test
	public void testIfDamagesAreSet() {
		assertTrue(100000 < player.getDamageDealt());
	}
	
	@Test
	public void testIfExperienceIsSet() {
		assertTrue(1000 < player.getHighestExperience());
	}
	
	@Test
	public void testIfExceptionThrownWhenPlayerNotFound() throws ResourceNotFoundException {
		exception.expect(ResourceNotFoundException.class);
			PlayerProcessor.getFromAPI(Platform.XBOX, 99);
	}
}
