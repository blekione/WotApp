package org.krugdev.domain.playerTanks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.krugdev.auxiliary.JSONParserUtils;
import org.krugdev.auxiliary.Platform;
import org.krugdev.auxiliary.RequestingServices;
import org.krugdev.auxiliary.ResourceNotFoundException;
import org.krugdev.auxiliary.WotWebsiteRequest;
import org.krugdev.domain.playerTanks.JSONDataBeans.TankJSONBean;

import com.google.gson.JsonArray;

public class TankItemsProcessor{

	public static List<TankItem> getFromAPI(Platform platform, int playerId) 
			throws ResourceNotFoundException {
		List<TankItem> tankItems = populateWithData(platform, playerId);
		return tankItems;
	}

	private static List<TankItem> populateWithData(Platform platform, int playerId) 
			throws ResourceNotFoundException {
		
		String jsonTanksString = getJsonWithTanksFromWotAPI(platform, playerId);
		List<TankJSONBean> wotAPITanks = getTanksFromJSONString(jsonTanksString, playerId);
		List<TankItem> tankItems = new ArrayList<>();
		for(TankJSONBean tankJSONBean : wotAPITanks) {
			TankItem tankItem = new TankItem();
			tankItem.populateWithDataFromJSONDataHolder(tankJSONBean);
			tankItems.add(tankItem);
		}
		return tankItems;
	}

	private static String getJsonWithTanksFromWotAPI(Platform platform, int playerId) {
		WotWebsiteRequest request = new WotWebsiteRequest(platform, RequestingServices.PLAYER_TANKS);
		return request.getJsonFromWotAPI(Integer.toString(playerId));
	}
	
	private static List<TankJSONBean> getTanksFromJSONString(String jsonTanksString, int playerId) throws ResourceNotFoundException {
		JsonArray tanksJsonArray = 
				JSONParserUtils.trimJsonFromRedundantData(jsonTanksString, Integer.toString(playerId)).getAsJsonArray();
		TankJSONBean[] tanksWotApi = JSONParserUtils.getElement(tanksJsonArray, new TankJSONBean[1]);
		return Arrays.asList(tanksWotApi);
	}
}
