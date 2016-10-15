package org.krugdev.auxiliary;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JSONParserUtils {

	public static JsonElement trimJsonFromRedundantData(String jsonFromWotApi, String id) throws ResourceNotFoundException {
		JsonParser parser = new JsonParser();
		JsonObject wotJsonObject = parser.parse(jsonFromWotApi).getAsJsonObject();
		if (checkIfJsonStatusIsOK(wotJsonObject)) {
			return getDataElementFromJson(wotJsonObject, id);
		} else {
			throw new ResourceNotFoundException("json status" + wotJsonObject.get("status").getAsString());
		}
	}

	private static JsonElement getDataElementFromJson(JsonObject jsonObject, String id) throws ResourceNotFoundException {
		JsonObject wotDataJsonObject = jsonObject.get("data").getAsJsonObject();
		if (!wotDataJsonObject.get(id).isJsonNull()) {
			return wotDataJsonObject.get(id);
		} else {
			throw new ResourceNotFoundException("no data for player id" + id);
		}
	}

	private static boolean checkIfJsonStatusIsOK(JsonObject jsonObject) {
		if(jsonObject.get("status").getAsString().equals("ok")) {
			return true;
		} else {
			return false;
		}
	}
	
	public static <T> T getElement(JsonElement json, T reference) {
		T objects = new Gson().fromJson(json, reference.getClass());		
		return objects;
	}
}
 