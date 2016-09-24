package org.krugdev.domain.playerTanks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.krugdev.auxiliary.JSONParserUtils;
import org.krugdev.auxiliary.Platform;
import org.krugdev.auxiliary.RequestingServices;
import org.krugdev.auxiliary.Resource;
import org.krugdev.auxiliary.ResourceNotFoundException;
import org.krugdev.auxiliary.WotWebsiteRequest;
import org.krugdev.domain.playerTanks.JSONDataBeans.TankJSONBean;

import com.google.gson.JsonArray;

@XmlRootElement(name="tanks")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlayersTanks implements Resource {
	
	private Platform platform;
	private String playerId;
	
	@XmlElement(name="tank")
	private List<TankItem> tanks;
	
	public PlayersTanks getFromAPI(Platform platform, String query) 
			throws ResourceNotFoundException {
		this.platform = platform;
		this.playerId = query.replaceFirst("^0+(?!$)", "");
		this.populateWithData();
		return this;
	}

	private void populateWithData() 
			throws ResourceNotFoundException {
		
		String jsonTanksString = getJsonWithTanksFromWotAPI(playerId);
		List<TankJSONBean> wotAPITanks = getTanksFromJSONString(jsonTanksString);
		tanks = new ArrayList<>();
		for(TankJSONBean tankJSONBean : wotAPITanks) {
			TankItem tankItem = new TankItem();
			tankItem.populateWithDataFromJSONDataHolder(tankJSONBean);
			tanks.add(tankItem);
		}
	}

	private String getJsonWithTanksFromWotAPI(String playerId) {
		WotWebsiteRequest request = new WotWebsiteRequest(platform, RequestingServices.PLAYER_TANKS);
		return request.getJsonFromWotAPI(playerId);
	}
	
	private List<TankJSONBean> getTanksFromJSONString(String jsonTanksString) throws ResourceNotFoundException {
		JsonArray tanksJsonArray = 
				JSONParserUtils.trimJsonFromRedundantData(jsonTanksString, playerId).getAsJsonArray();
		TankJSONBean[] tanksWotApi = (TankJSONBean[]) JSONParserUtils.getList(tanksJsonArray, TankJSONBean[].class);
		return Arrays.asList(tanksWotApi);
	}

	public int getTanksCounter() {
		return tanks.size();
	}

	public List<TankItem> getTanks() {
		return tanks;
	}
}
