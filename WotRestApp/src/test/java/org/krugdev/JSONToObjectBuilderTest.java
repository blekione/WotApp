package org.krugdev;

import static org.junit.Assert.*;

import org.junit.Test;
import org.krugdev.auxiliary.JSONToObjectBuilder;
import org.krugdev.auxiliary.ResourceNotFoundException;
import org.krugdev.domain.player.JSONDataBeans.PlayerJSONBean;
import org.krugdev.domain.searchPlayers.PlayerBasic;

public class JSONToObjectBuilderTest {

	@Test
	public void testIfObjectIsBuiltForJSONWithId() throws ResourceNotFoundException {
		String jsonString = "{\"status\":\"ok\",\"meta\":{\"count\":1},\"data\":{\"6479371\":{\"statistics\":{\"max_frags_tank_id\":9505,\"explosion_hits\":85,\"max_xp_tank_id\":513,\"damage_assisted_track\":371887,\"max_xp\":2756,\"piercings\":33754,\"company\":{\"spotted\":0,\"hits\":0,\"wins\":0,\"losses\":0,\"capture_points\":0,\"battles\":0,\"damage_dealt\":0,\"damage_received\":0,\"shots\":0,\"xp\":0,\"frags\":0,\"survived_battles\":0,\"dropped_capture_points\":0},\"trees_cut\":11709,\"all\":{\"spotted\":8160,\"hits\":47644,\"wins\":3232,\"losses\":2719,\"capture_points\":2756,\"battles\":6008,\"damage_dealt\":5805365,\"damage_received\":5209935,\"shots\":70201,\"xp\":3979952,\"frags\":6172,\"survived_battles\":1694,\"dropped_capture_points\":7560},\"piercings_received\":28241,\"no_damage_direct_hits_received\":15788,\"max_frags\":8,\"explosion_hits_received\":1409,\"max_damage_tank_id\":10785,\"frags\":null,\"direct_hits_received\":44029,\"max_damage\":8190,\"damage_assisted_radio\":1661846},\"account_id\":6479371,\"created_at\":1438330823,\"updated_at\":1475313003,\"private\":null,\"global_rating\":6831,\"last_battle_time\":1474275822,\"nickname\":\"Mr Flen\"}}}\r\n";
		String id = "6479371";
		JSONToObjectBuilder<PlayerJSONBean> builder = new JSONToObjectBuilder<>(new PlayerJSONBean());
		PlayerJSONBean playerJSONBean = builder.fromString(jsonString).withId(id).build();
		assertEquals(playerJSONBean.getNickname(), "Mr Flen");	
	}
	
	@Test
	public void testIfObjectIsBuiltForJsonWithoutId() throws ResourceNotFoundException {
		String jsonString = "{\"status\":\"ok\",\"meta\":{\"count\":2},\"data\":[{\"nickname\":\"Mr Flen\",\"account_id\":6479371},{\"nickname\":\"Mr Flena\",\"account_id\":7735512}]}\r\n";
		JSONToObjectBuilder<PlayerBasic[]> builder = new JSONToObjectBuilder<>(new PlayerBasic[1]);
		PlayerBasic[] players = builder.fromString(jsonString).build();
		assertEquals(players[0].getNickname(), "Mr Flen");
	}	
}
