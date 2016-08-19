package org.krugdev.domain;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.krugdev.domain.Platforms;
import org.krugdev.domain.search.PlayerProfileBasic;

public class PlayerBasicStatisticsTest {

	@Test
	public void testIfListOfPlayersIsReturned() {
		List<PlayerProfileBasic> players = PlayerProfileBasic.getPlayers(Platforms.XBOX, "mr flen");
//		System.out.println(players.size());
		assertFalse(players.isEmpty());
	}

}
