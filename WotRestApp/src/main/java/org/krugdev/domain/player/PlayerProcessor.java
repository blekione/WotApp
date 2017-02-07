package org.krugdev.domain.player;

import org.krugdev.auxiliary.JSONToObjectBuilder;
import org.krugdev.auxiliary.Platform;
import org.krugdev.auxiliary.RequestingServices;
import org.krugdev.auxiliary.ResourceNotFoundException;
import org.krugdev.auxiliary.WotWebsiteRequest;
import org.krugdev.domain.player.JSONDataBeans.PlayerClanJSONBean;
import org.krugdev.domain.player.JSONDataBeans.PlayerJSONBean;

public class PlayerProcessor {
	
	public static Player getFromAPI(Platform platform, int playerId) 
			throws ResourceNotFoundException {
		String playerIdStr = Integer.toString(playerId);
		Player player = new Player(platform, playerId);
		player.populateWithData(getPlayerDataFromWotApi(platform, playerIdStr));
		player.setPlayerClan(getPlayerClanDataFromWotAPI(platform, playerIdStr));
		return player;
	}
	
	private static PlayerJSONBean getPlayerDataFromWotApi(Platform platform, String playerId) 
			throws ResourceNotFoundException {		
		String jsonString = getJsonString(platform, RequestingServices.PLAYER_PROFILE, playerId);
		if (jsonString.equals("{}")) {
			System.out.println("connection with server problem, may be timeout");
			throw new ResourceNotFoundException("connection with server problem, may be timeout");
		}
		JSONToObjectBuilder<PlayerJSONBean> builder = new JSONToObjectBuilder<>(new PlayerJSONBean());
		PlayerJSONBean playerJsonBean = builder.fromString(jsonString).withId(playerId).build();
		return playerJsonBean;
	}
	
	private static PlayerClanJSONBean getPlayerClanDataFromWotAPI(Platform platform, String playerId) 
			throws ResourceNotFoundException {
		String jsonString = getJsonString(platform, RequestingServices.PLAYER_CLAN, playerId);
		JSONToObjectBuilder<PlayerClanJSONBean> builder = new JSONToObjectBuilder<>(new PlayerClanJSONBean());
		PlayerClanJSONBean playerClanJSONBean = builder.fromString(jsonString).withId(playerId).build();
		return playerClanJSONBean;
	}
	
	private static String getJsonString(Platform platform, RequestingServices service, String id) {
		WotWebsiteRequest request = new WotWebsiteRequest(platform, service);
		return request.getJsonFromWotAPI(id);
	}
}
