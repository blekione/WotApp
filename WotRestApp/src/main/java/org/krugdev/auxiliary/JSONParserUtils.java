package org.krugdev.auxiliary;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JSONParserUtils {
	
	public static Object getClassDataFromJson(JsonObject jsonObject, Class<?> cls) {
		Gson gson = new Gson();
		Object parsedDataObject = gson.fromJson(jsonObject, cls);
		return parsedDataObject;
	}
	
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
	
	public static Object[] getList(JsonArray jsonArray, Class<?> cls) {
		Object[] objects = (Object[])new Gson().fromJson(jsonArray, cls);
		return objects;
	}

	private static boolean checkIfJsonStatusIsOK(JsonObject jsonObject) {
		if(jsonObject.get("status").getAsString().equals("ok")) {
			return true;
		} else {
			return false;
		}
	}
}
