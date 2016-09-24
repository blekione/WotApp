package org.krugdev;

import static org.junit.Assert.*;

import org.junit.Test;
import org.krugdev.auxiliary.Platform;
import org.krugdev.auxiliary.RequestingServices;
import org.krugdev.auxiliary.WotWebsiteRequest;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class WotWebsiteRequestTest {

	@Test
	public void testIfSearchRequestReturnsPage() {
		WotWebsiteRequest request = new WotWebsiteRequest(Platform.XBOX, RequestingServices.SEARCH);
		String response = request.getJsonFromWotAPI("mr flen");
		assertEquals("ok", getResponseStatus(response));
	}
	
	@Test
	public void testIfPlayerProfileRequuestReturnsPage() {
		WotWebsiteRequest request = new WotWebsiteRequest(Platform.XBOX, RequestingServices.PLAYER_PROFILE);
		String response = request.getJsonFromWotAPI("6479371");
		assertEquals("ok", getResponseStatus(response));
	}

	@Test
	public void testIfPlayerClanReturnsPage() {
		WotWebsiteRequest request = new WotWebsiteRequest(Platform.XBOX, RequestingServices.PLAYER_CLAN);
		String response = request.getJsonFromWotAPI("6479371");
		assertEquals("ok", getResponseStatus(response));
	}
	
	@Test
	public void testIfClanReturnsPage() {
		WotWebsiteRequest request = new WotWebsiteRequest(Platform.XBOX, RequestingServices.CLAN);
		String response = request.getJsonFromWotAPI("1713");
		assertEquals("ok", getResponseStatus(response));
		
	}
	
	@Test
	public void testIfPlayersTanksReturnsPage() {
		WotWebsiteRequest request = 
				new WotWebsiteRequest(Platform.XBOX, RequestingServices.PLAYER_TANKS);
		String response = request.getJsonFromWotAPI("6479371");
		assertEquals("ok", getResponseStatus(response));
	}
	
	private String getResponseStatus(String response) {
		JsonParser parser = new JsonParser();
		JsonObject jsonObject = parser.parse(response).getAsJsonObject();
		String status = jsonObject.get("status").getAsString();
		return status;
	}

}
