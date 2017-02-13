package org.krugdev.reader;

import org.junit.Test;
import org.krugdev.wn8.TankItem;

import java.util.List;


import static org.junit.Assert.*;

public class WotApiServiceReaderTest {

	
	public static final String SERVICE_URI_LOCATION = "http://localhost:8080/WotRestApp/wotAPI/XBOX-plt/players/";
	public static final int PLAYER_ID = 6479371;
	
	@Test
	public void shouldReturnListOfPlayersTanks() {
 		Reader serviceReader = new WotAPIServiceReader(SERVICE_URI_LOCATION);
		List<TankItem> playerTanks = serviceReader.getPlayerTanks(PLAYER_ID);
		assertFalse(playerTanks.isEmpty());
	}

}