package org.krugdev;

import static org.junit.Assert.*;


import javax.xml.bind.JAXBException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.krugdev.auxiliary.Platform;
import org.krugdev.auxiliary.ResourceNotFoundException;
import org.krugdev.domain.searchPlayers.SearchPlayersResult;

public class SearchPlayersResultTest {

	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testIfListOfPlayersIsReturned() throws ResourceNotFoundException, JAXBException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		SearchPlayersResult players = new SearchPlayersResult();
		players.getFromAPI(Platform.XBOX, "mr flen");

//		Field field = playersList.getClass().getDeclaredField("players");
//		field.setAccessible(true);
//		
//		Type genericFieldType = field.getGenericType();
//		System.out.println("type:" + genericFieldType);
//		if (genericFieldType instanceof ParameterizedType){
//			ParameterizedType pt = (ParameterizedType) genericFieldType;
//			for (Type t : pt.getActualTypeArguments()) {
//				System.out.println("   " + t);
//				assertEquals(PlayerBasic.class, t);
//			}
//		}
//		
		
		assertFalse(players.getPlayers().isEmpty());
	}

	@Test
	public void testIfExceptionThrownWhenPlayersNotFound() throws ResourceNotFoundException {
		exception.expect(ResourceNotFoundException.class);
		SearchPlayersResult playersList = new SearchPlayersResult();
		playersList.getFromAPI(Platform.XBOX, "mr flenaaaaaaaaaaaaaaa");
	}
	
}
