package org.krugdev;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.krugdev.auxiliary.JSONParserUtils;
import org.krugdev.auxiliary.ResourceNotFoundException;
import org.krugdev.domain.player.JSONDataBeans.PlayerJSONBean;
import org.krugdev.domain.searchPlayers.PlayerBasic;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
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
	
	@Test
	public void testIfGetObjectReturnsObjectOfGenericType() {
		JsonParser parser = new JsonParser();
		String stringJsonObject = "{\"statistics\":{\"max_frags_tank_id\":9505,\"explosion_hits\":85,\"max_xp_tank_id\":513,\"damage_assisted_track\":371887,\"max_xp\":2756,\"piercings\":33754,\"company\":{\"spotted\":0,\"hits\":0,\"wins\":0,\"losses\":0,\"capture_points\":0,\"battles\":0,\"damage_dealt\":0,\"damage_received\":0,\"shots\":0,\"xp\":0,\"frags\":0,\"survived_battles\":0,\"dropped_capture_points\":0},\"trees_cut\":11709,\"all\":{\"spotted\":8160,\"hits\":47644,\"wins\":3232,\"losses\":2719,\"capture_points\":2756,\"battles\":6008,\"damage_dealt\":5805365,\"damage_received\":5209935,\"shots\":70201,\"xp\":3979952,\"frags\":6172,\"survived_battles\":1694,\"dropped_capture_points\":7560},\"piercings_received\":28241,\"no_damage_direct_hits_received\":15788,\"max_frags\":8,\"explosion_hits_received\":1409,\"max_damage_tank_id\":10785,\"frags\":null,\"direct_hits_received\":44029,\"max_damage\":8190,\"damage_assisted_radio\":1661846},\"account_id\":6479371,\"created_at\":1438330823,\"updated_at\":1475313003,\"private\":null,\"global_rating\":6831,\"last_battle_time\":1474275822,\"nickname\":\"Mr Flen\"}";
		JsonObject jsonObject = parser.parse(stringJsonObject).getAsJsonObject();
		PlayerJSONBean playerJsonBean = JSONParserUtils.getObject(jsonObject, new PlayerJSONBean());
		Assert.assertEquals("Mr Flen", playerJsonBean.getNickname());
	}
}