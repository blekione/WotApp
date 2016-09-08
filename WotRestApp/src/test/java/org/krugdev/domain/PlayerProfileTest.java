package org.krugdev.domain;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.krugdev.domain.Platforms;
import org.krugdev.domain.playerProfile.PlayerProfile;

public class PlayerProfileTest {

	static PlayerProfile playerProfile;
	
	@BeforeClass
	public static void setPlayerProfile() {
		playerProfile = 
				PlayerProfile.getPlayerProfile(Platforms.XBOX, "6479371");
	}
	
	@Test
	public void testIfProfileIsReceived() {
		assertEquals("Mr Flen", playerProfile.getNickname());
	}
	
	@Test
	public void testIfDaysInGameAreSetCorrectly() {
		Date date = new Date();
		int expectedValue = (int)((date.getTime() / 1000) - 1438330823) / 86400;
		assertEquals(expectedValue, playerProfile.getDaysInGame());
	}

	@Test
	public void testIfGamesCountersAreSet() {
		assertTrue(5000 < playerProfile.getGamesPlayedCounter());
	}
	
	@Test
	public void testIfKillsDeathsAreSet() {
		assertTrue(5000l < playerProfile.getKills());
	}
	
	@Test
	public void testIfDamagesAreSet() {
		assertTrue(100000 < playerProfile.getDamageDealt());
	}
	
	@Test
	public void testIfExperienceIsSet() {
		assertTrue(1000 < playerProfile.getHighestExperience());
	}
}
