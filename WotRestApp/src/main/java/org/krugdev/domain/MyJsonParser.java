package org.krugdev.domain;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class MyJsonParser {
	
	public Object getClassDataFromJson(JsonObject jsonObject, Class<?> cls) {
		Gson gson = new Gson();
		Object parsedDataObject = gson.fromJson(jsonObject, cls);
		return parsedDataObject;		
	}
	
	public JsonElement trimJsonFromRedundantData(String jsonFromWotApi, String id) throws ResourceNotFoundException {
		JsonParser parser = new JsonParser();
		JsonObject wotJsonObject = parser.parse(jsonFromWotApi).getAsJsonObject();
		if (checkIfJsonStatusIsOK(wotJsonObject)) {
			return getDataElementFromJson(wotJsonObject, id);		
		} else {
			throw new ResourceNotFoundException("json status" + wotJsonObject.get("status").getAsString());
		}
	}

	private JsonElement getDataElementFromJson(JsonObject playerProfileJson, String id) throws ResourceNotFoundException {
		JsonObject wotDataJsonObject = playerProfileJson.get("data").getAsJsonObject();
		if (!wotDataJsonObject.get(id).isJsonNull()) {
			return wotDataJsonObject.get(id);
		} else {
			throw new ResourceNotFoundException("no data for player id" + id);
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
