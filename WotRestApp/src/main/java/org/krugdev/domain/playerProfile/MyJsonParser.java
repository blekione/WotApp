package org.krugdev.domain.playerProfile;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class MyJsonParser {
	
	public Object getClassDataFromJson(JsonObject playerProfileIDDataJson, Class<?> cls) {
		Gson gson = new Gson();
		Object playerProfileData = gson.fromJson(playerProfileIDDataJson, cls);
		return playerProfileData;		
	}
	
	public JsonObject trimJsonFromRedundantData(String jsonFromWotApi, String id) {
		JsonParser parser = new JsonParser();
		JsonObject playerProfileJson = parser.parse(jsonFromWotApi).getAsJsonObject();
		JsonObject playerProfileDataJson = playerProfileJson.get("data").getAsJsonObject();
		return playerProfileDataJson.get(id).getAsJsonObject();
	}

}
