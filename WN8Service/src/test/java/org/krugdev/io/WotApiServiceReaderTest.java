package org.krugdev.io;

import org.junit.Test;
import org.krugdev.io.Reader;
import org.krugdev.io.WotAPIServiceReader;
import org.krugdev.wn8.XML.XMLTankItem;

import java.util.List;


import static org.junit.Assert.*;

public class WotApiServiceReaderTest {

	
	public static final String SERVICE_URI_LOCATION = "http://localhost:8080/WotRestApp/wotAPI/XBOX-plt/players/";
	public static final int PLAYER_ID = 6479371;
	
	@Test
	public void shouldReturnListOfPlayersTanks() {
 		Reader serviceReader = new WotAPIServiceReader(SERVICE_URI_LOCATION);
		List<XMLTankItem> playerTanks = serviceReader.getPlayerTanks(PLAYER_ID);
		assertFalse(playerTanks.isEmpty());
	}

}
