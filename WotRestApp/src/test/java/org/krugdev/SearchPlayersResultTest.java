package org.krugdev;

import static org.junit.Assert.*;


import javax.xml.bind.JAXBException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.krugdev.auxiliary.Platform;
import org.krugdev.auxiliary.ResourceNotFoundException;
import org.krugdev.domain.searchPlayers.SearchPlayersProcessor;

public class SearchPlayersResultTest {

	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testIfListOfPlayersIsReturned() throws ResourceNotFoundException, JAXBException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		SearchPlayersProcessor players = new SearchPlayersProcessor();
		players.getFromAPI(Platform.XBOX, "mr flen");
		assertFalse(players.getPlayers().isEmpty());
	}

	@Test
	public void testIfExceptionThrownWhenPlayersNotFound() throws ResourceNotFoundException {
		exception.expect(ResourceNotFoundException.class);
		SearchPlayersProcessor playersList = new SearchPlayersProcessor();
		playersList.getFromAPI(Platform.XBOX, "mr flenaaaaaaaaaaaaaaa");
	}
	
}
