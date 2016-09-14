package org.krugdev.domain;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class MyJsonParser {
	
	public Object getClassDataFromJson(JsonObject playerProfileIDDataJson, Class<?> cls) {
		Gson gson = new Gson();
		Object playerProfileData = gson.fromJson(playerProfileIDDataJson, cls);
		return playerProfileData;		
	}
	
	public JsonObject trimJsonFromRedundantData(String jsonFromWotApi, String id) throws PlayerNotFoundException {
		JsonParser parser = new JsonParser();
		JsonObject playerProfileJson = parser.parse(jsonFromWotApi).getAsJsonObject();
		if (checkIfJsonStatusIsOK(playerProfileJson)) {
			return getDataElementFromJson(playerProfileJson, id);		
		} else {
			throw new PlayerNotFoundException("json status" + playerProfileJson.get("status").getAsString());
		}
	}

	private JsonObject getDataElementFromJson(JsonObject playerProfileJson, String id) throws PlayerNotFoundException {
		JsonObject playerProfileDataJson = playerProfileJson.get("data").getAsJsonObject();
		if (!playerProfileDataJson.get(id).isJsonNull()) {
			return playerProfileDataJson.get(id).getAsJsonObject();
		} else {
			throw new PlayerNotFoundException("no data for player id" + id);
		}		
	}

	private boolean checkIfJsonStatusIsOK(JsonObject playerProfileJson) {
		if(playerProfileJson.get("status").getAsString().equals("ok")) {
			return true;
		} else {
			return false;
		}
	}
	
	
}
