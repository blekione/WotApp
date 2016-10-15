package org.krugdev;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.krugdev.auxiliary.JSONParserUtils;
import org.krugdev.auxiliary.ResourceNotFoundException;
import org.krugdev.domain.searchPlayers.PlayerBasic;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class JSONParserUttilsTest {
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Test
	public void testIfParserThrowsExceptionWhenStatusError() throws ResourceNotFoundException {
		String json = "{\"status\":\"error\",\"error\":{\"code\":504,\"message\":\"SOURCE_NOT_AVAILABLE\",\"field\":null,\"value\":null}}";
		exception.expect(ResourceNotFoundException.class);
		JSONParserUtils.trimJsonFromRedundantData(json, "0000000");
	}
	
	@Test
	public void testIfParserThrowsExceptionIfNoDataForPlayer() throws ResourceNotFoundException {
		String json = "{\"status\":\"ok\",\"meta\":{\"count\":1},\"data\":{\"99\":null}}";
		exception.expect(ResourceNotFoundException.class);
		JSONParserUtils.trimJsonFromRedundantData(json, "99");
	}
	
	@Test
	public void testIfGetListReturnsArrayOfGenericType() {
		JsonParser parser = new JsonParser();
		String stringJsonArray = "[{\"nickname\":\"Mr Flen\",\"account_id\":6479371},{\"nickname\":\"Mr Flena\",\"account_id\":7735512}]";
		JsonArray jsonArray = parser.parse(stringJsonArray).getAsJsonArray();
		PlayerBasic[] players = JSONParserUtils.getList(jsonArray, new PlayerBasic[1]);
		Assert.assertEquals("Mr Flen", players[0].getNickname());
	}
}