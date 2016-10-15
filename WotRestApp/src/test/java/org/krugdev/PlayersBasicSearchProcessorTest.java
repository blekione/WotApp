package org.krugdev;

import static org.junit.Assert.*;

import java.util.List;

import javax.xml.bind.JAXBException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.krugdev.auxiliary.Platform;
import org.krugdev.auxiliary.ResourceNotFoundException;
import org.krugdev.domain.searchPlayers.PlayerBasic;
import org.krugdev.domain.searchPlayers.PlayersBasicSearchProcessor;

public class PlayersBasicSearchProcessorTest {

	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testIfListOfPlayersIsReturned() throws ResourceNotFoundException, JAXBException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		List<PlayerBasic> players = PlayersBasicSearchProcessor.getFromAPI(Platform.XBOX, "mr flen");
		assertFalse(players.isEmpty());
	}

	@Test
	public void testIfExceptionThrownWhenPlayersNotFound() throws ResourceNotFoundException {
		exception.expect(ResourceNotFoundException.class);
		PlayersBasicSearchProcessor.getFromAPI(Platform.XBOX, "mr flenaaaaaaaaaaaaaaa");
	}
	
}
