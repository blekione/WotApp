package org.krugdev;

import static org.junit.Assert.*;

import org.junit.Test;
import org.krugdev.auxiliary.RequestURL;

public class RequestURLTest {

	@Test
	public void testReturnedValueFromRequestURL() {
		assertEquals("https://api-xbox-console.worldoftanks.com/wotx/", RequestURL.XBOX_API_URL.toString());
		
	}

}
