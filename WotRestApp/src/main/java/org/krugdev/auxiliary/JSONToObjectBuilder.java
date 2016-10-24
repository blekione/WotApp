package org.krugdev.auxiliary;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JSONToObjectBuilder<T> {

	String jsonString;
	boolean jsonIsWithId;
	String id;
	T object;
	T ref;
	
	public JSONToObjectBuilder(T ref) {
		this.ref = ref;
	}
	
	public JSONToObjectBuilder<T> fromString(String jsonString) {
		this.jsonString = jsonString;
		return this;
	}
	
	public JSONToObjectBuilder<T> withId(String id) {
		this.id = id;
		this.jsonIsWithId = true;
		return this;
	}
	
	public T build() throws ResourceNotFoundException {
		JsonElement jsonElement;
		if (jsonIsWithId) {
			jsonElement = trimJsonFromRedundantData(jsonString, id);
		} else {
			jsonElement = trimJsonFromRedundantData(jsonString);
		}
		getElement(jsonElement);
		return object;
	}

	private JsonElement trimJsonFromRedundantData(String jsonString, String id) throws ResourceNotFoundException {
		JsonObject jsonObject = getDataJSONObject(jsonString).getAsJsonObject();
		return getIdElementFromJson(jsonObject, id);
	}
	
	private JsonElement trimJsonFromRedundantData(String jsonString) throws ResourceNotFoundException {
		return getDataJSONObject(jsonString);		
	}
	
	private JsonElement getDataJSONObject(String jsonString) throws ResourceNotFoundException {
		JsonParser parser = new JsonParser();
		JsonObject jsonObject = parser.parse(jsonString).getAsJsonObject();
		throwExceptionIfJsonStatusIsNotOK(jsonObject);
		return jsonObject.get("data");
	}
	
	private void throwExceptionIfJsonStatusIsNotOK(JsonObject jsonObject) throws ResourceNotFoundException {
		if(!jsonObject.get("status").getAsString().equals("ok")) {
			throw new ResourceNotFoundException("json status" + jsonObject.get("status").getAsString());
		}
	}
	
	private JsonElement getIdElementFromJson(JsonObject jsonObject, String id) throws ResourceNotFoundException {
		if (!jsonObject.get(id).isJsonNull()) {
			return jsonObject.get(id);
		} else {
			throw new ResourceNotFoundException("no data for player id" + id);
		}
	}
	
	@SuppressWarnings("unchecked")
	private void getElement(JsonElement json) {
		object = (T)new Gson().fromJson(json, ref.getClass());		
	}
}
