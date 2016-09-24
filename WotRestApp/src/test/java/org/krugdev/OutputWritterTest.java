package org.krugdev;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.junit.Test;
import org.krugdev.auxiliary.OutputWritter;
import org.krugdev.auxiliary.Platform;
import org.krugdev.auxiliary.ResourceNotFoundException;
import org.krugdev.domain.searchPlayers.SearchPlayersResult;

public class OutputWritterTest {

	@Test
	public void testIfOutputToXMLWorks() throws ResourceNotFoundException, JAXBException {
		SearchPlayersResult players = new SearchPlayersResult();
		players.getFromAPI(Platform.XBOX, "mr flen");
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        OutputWritter outputWritter = new OutputWritter();
        outputWritter.toXml(players, output);
        
        StreamSource source = new StreamSource(new StringReader(output.toString()));
                
        JAXBContext jaxbContext = JAXBContext.newInstance(SearchPlayersResult.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		SearchPlayersResult testPlayers = (SearchPlayersResult)jaxbUnmarshaller.unmarshal(source, SearchPlayersResult.class).getValue();
        assertFalse(testPlayers.getPlayers().isEmpty());

	}

}
