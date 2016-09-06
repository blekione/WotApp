package org.krugdev.domain.playerProfile;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.krugdev.domain.MyJsonParser;
import org.krugdev.domain.playerProfile.JSONDataBeans.ClanJSONBean;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class MyJsonParserTest {

	@Test
	public void testIfMapWithClanMembersIsParsed() {
		MyJsonParser myParser = new MyJsonParser();
		JsonParser parser = new JsonParser();
		String inputString = "{\"creator_id\":217111,\"members_count\":58,\"description\":\"<>\",\"creator_name\":\"Pabllo88PL\",\"color\":\"#FFFFFF\",\"clan_id\":1713,\"created_at\":1446929830,\"updated_at\":1472623274,\"leader_name\":\"Shadokus\",\"members_ids\":[169796,217111,217743,217753,251105,292613,297185,548072,774797,1182389,1202551,1290955,1382747,1426504,1909215,2010822,2768996,2812786,2966277,3566570,3757544,4078706,4099484,4671287,5988559,6089396,6090872,6095235,6098960,6124057,6137277,6184638,6222608,6287818,6434088,6479371,6484952,6487133,6757409,6812945,6879562,7087445,7102234,7318046,7364260,7377093,7454960,7518316,7896122,8055527,8126047,8155309,8164175,8358219,8553383,8659785,9316842,9622883],\"recruiting_policy\":\"restricted\",\"tag\":\"1PAD\",\"emblem_set_id\":1,\"is_clan_disbanded\":false,\"old_name\":null,\"joining_options\":{\"wins_ratio\":0.0,\"battles_per_day\":0.0,\"damage_per_battle\":0.0,\"battles_survived\":0,\"hits_ratio\":0.0,\"battles\":0,\"xp_per_battle\":0.0},\"leader_id\":169796,\"motto\":\"<>\",\"renamed_at\":null,\"old_tag\":null,\"name\":\"1st Polish Armored Division\"}";
		JsonObject json = parser.parse(inputString).getAsJsonObject();
		ClanJSONBean clan = (ClanJSONBean)myParser.getClassDataFromJson(json, ClanJSONBean.class);
		List<Integer> members = clan.getMembersIds();
		assertEquals(Integer.valueOf(169796), members.get(0));
	}

}
