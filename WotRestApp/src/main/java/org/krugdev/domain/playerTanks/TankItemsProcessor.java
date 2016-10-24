package org.krugdev.domain.playerTanks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.krugdev.auxiliary.JSONToObjectBuilder;
import org.krugdev.auxiliary.Platform;
import org.krugdev.auxiliary.RequestingServices;
import org.krugdev.auxiliary.ResourceNotFoundException;
import org.krugdev.auxiliary.WotWebsiteRequest;
import org.krugdev.domain.playerTanks.JSONDataBeans.TankJSONBean;

public class TankItemsProcessor{

	public static List<TankItem> getFromAPI(Platform platform, int playerId) 
			throws ResourceNotFoundException {
		List<TankItem> tankItems = populateWithData(platform, playerId);
		return tankItems;
	}

	private static List<TankItem> populateWithData(Platform platform, int playerId) 
			throws ResourceNotFoundException {
		String playerIdStr = Integer.toString(playerId);
		String jsonTanksString = getJsonWithTanksFromWotAPI(platform, playerIdStr);
		List<TankJSONBean> wotAPITanks = getTanksFromJSONString(jsonTanksString, playerIdStr);
		List<TankItem> tankItems = new ArrayList<>();
		for(TankJSONBean tankJSONBean : wotAPITanks) {
			TankItem tankItem = new TankItem();
			tankItem.populateWithDataFromJSONDataHolder(tankJSONBean);
			tankItems.add(tankItem);
		}
		return tankItems;
	}

	private static String getJsonWithTanksFromWotAPI(Platform platform, String playerId) {
		WotWebsiteRequest request = new WotWebsiteRequest(platform, RequestingServices.PLAYER_TANKS);
		return request.getJsonFromWotAPI(playerId);
	}
	
	private static List<TankJSONBean> getTanksFromJSONString(String jsonTanksString, String playerId) throws ResourceNotFoundException {
		JSONToObjectBuilder<TankJSONBean[]> builder = new JSONToObjectBuilder<>(new TankJSONBean[1]);
		TankJSONBean[] tanksWotApi = builder.fromString(jsonTanksString).withId(playerId).build();
		return Arrays.asList(tanksWotApi);
	}
}
