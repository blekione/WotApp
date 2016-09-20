package org.krugdev.domain;


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class MyJsonParserTest {
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Test
	public void testIfParserThrowsExceptionWhenStatusError() throws ResourceNotFoundException {
		MyJsonParser parser = new MyJsonParser();
		String json = "{\"status\":\"error\",\"error\":{\"code\":504,\"message\":\"SOURCE_NOT_AVAILABLE\",\"field\":null,\"value\":null}}";
		exception.expect(ResourceNotFoundException.class);
		parser.trimJsonFromRedundantData(json, "0000000");
	}
	
	@Test
	public void testIfParserThrowsExceptionIfNoDataForPlayer() throws ResourceNotFoundException {
		MyJsonParser parser = new MyJsonParser();
		String json = "{\"status\":\"ok\",\"meta\":{\"count\":1},\"data\":{\"99\":null}}";
		exception.expect(ResourceNotFoundException.class);
		parser.trimJsonFromRedundantData(json, "99");
	}
}