package org.krugdev.domain.player;

import org.krugdev.auxiliary.JSONParserUtils;
import org.krugdev.auxiliary.Platform;
import org.krugdev.auxiliary.RequestingServices;
import org.krugdev.auxiliary.ResourceNotFoundException;
import org.krugdev.auxiliary.WotWebsiteRequest;
import org.krugdev.domain.player.JSONDataBeans.PlayerClanJSONBean;
import org.krugdev.domain.player.JSONDataBeans.PlayerJSONBean;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class PlayerProcessor {
	
	public static Player getFromAPI(Platform platform, String playerId) 
			throws ResourceNotFoundException {
		playerId = trimLeadingZeros(playerId);
		Player player = new Player(platform, playerId);
		player.populateWithData(getPlayerDataFromWotApi(platform, playerId));
		player.setPlayerClan(getPlayerClanDataFromWotAPI(platform, playerId));
		return player;
	}
	
	private static String trimLeadingZeros(String s) {
		return s.replaceFirst("^0+(?!$)", "");
	}
	
	private static PlayerJSONBean getPlayerDataFromWotApi(Platform platform, String playerId) throws ResourceNotFoundException {		
		JsonObject playerJson = 
				getJsonFromWot(RequestingServices.PLAYER_PROFILE, platform, playerId).getAsJsonObject();
		if (playerJson.isJsonNull()) {
		}
		PlayerJSONBean playerJsonBean = JSONParserUtils.getElement(playerJson, new PlayerJSONBean());
		return playerJsonBean;
	}
	
	private static PlayerClanJSONBean getPlayerClanDataFromWotAPI(Platform platform, String playerId) throws ResourceNotFoundException {
		JsonObject playerClanJSON = 
				getJsonFromWot(RequestingServices.PLAYER_CLAN, platform, playerId).getAsJsonObject();
		PlayerClanJSONBean playerClanJSONBean = 
				JSONParserUtils.getElement(playerClanJSON, new PlayerClanJSONBean());
		return playerClanJSONBean;
	}

	private static JsonElement getJsonFromWot(RequestingServices service, Platform platform, String id) 
			throws ResourceNotFoundException {
		WotWebsiteRequest request = new WotWebsiteRequest(platform, service);
		String playerProfileJsonAsString = request.getJsonFromWotAPI(id);
		return JSONParserUtils.trimJsonFromRedundantData(playerProfileJsonAsString, id);
	}
}
