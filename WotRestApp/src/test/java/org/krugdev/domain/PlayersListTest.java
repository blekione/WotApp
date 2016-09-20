package org.krugdev.domain;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.krugdev.domain.Platforms;
import org.krugdev.domain.search.PlayerBasic;

public class PlayersListTest {

	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testIfListOfPlayersIsReturned() throws ResourceNotFoundException, JAXBException {
		PlayersList playersList = new PlayersList();
		playersList.get(Platforms.XBOX, "mr flen");
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        playersList.outputResourceAsXML(output);
                
        StreamSource source = new StreamSource(new StringReader(output.toString()));
                
        JAXBContext jaxbContext = JAXBContext.newInstance(MarshallerListWrapper.class, PlayerBasic.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        @SuppressWarnings("unchecked")
		MarshallerListWrapper<PlayerBasic> players = 
        		(MarshallerListWrapper<PlayerBasic>)jaxbUnmarshaller
        			.unmarshal(source, MarshallerListWrapper.class).getValue();
        assertFalse(players.getItems().isEmpty());
	}

	@Test
	public void testIfExceptionThrownWhenPlayersNotFound() throws ResourceNotFoundException {
		exception.expect(ResourceNotFoundException.class);
		PlayersList playersList = new PlayersList();
		playersList.get(Platforms.XBOX, "mr flenaaaaaaaaaaaaaaa");
	}
	
}
