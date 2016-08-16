package org.krugdev.domain;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class WotWebsiteRequestTest {

	@Test
	public void testIfRequestReturnsPage() {
		WotWebsiteRequest request = new WotWebsiteRequest(Platforms.XBOX, RequestingServices.SEARCH);
		String response = request.getJsonWithPLayers("mr flen");
//		System.out.println(response);
		assertEquals("ok", getResponseStatus(response));
	}

	private String getResponseStatus(String response) {
		JsonParser parser = new JsonParser();
		JsonObject jsonObject = parser.parse(response).getAsJsonObject();
		String status = jsonObject.get("status").getAsString();
		return status;
	}

}
