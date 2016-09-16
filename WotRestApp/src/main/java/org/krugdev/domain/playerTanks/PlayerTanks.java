package org.krugdev.domain.playerTanks;

import java.util.List;

import org.krugdev.domain.MyJsonParser;
import org.krugdev.domain.Platforms;
import org.krugdev.domain.PlayerNotFoundException;
import org.krugdev.domain.RequestingServices;
import org.krugdev.domain.WotWebsiteRequest;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class PlayerTanks {
	
	private static final MyJsonParser PARSER = new MyJsonParser();
	
	private Platforms platform;
	private String playerId;
	
	private int tanksCounter;
	private List<TankItem> tanks;
	
	public PlayerTanks() {
	}
	
	public PlayerTanks(Platforms platform, String playerId) {
		this.platform = platform;
		this.playerId = playerId.replaceFirst("^0+(?!$)", ""); // trims leading zeros
	}
	
	public static PlayerTanks getTanks(Platforms platform, String playerId) {
		PlayerTanks playerTanks = new PlayerTanks(platform, playerId);
		playerTanks.populateWithData();

		return playerTanks;
	}

	private void populateWithData() {
		WotWebsiteRequest request = new WotWebsiteRequest(platform, RequestingServices.PLAYER_TANKS);
		String playerTanksJsonAsString = request.getJsonFromWotAPI(playerId);
		
			try {
				JsonArray tankJson = PARSER.trimJsonFromRedundantData(playerTanksJsonAsString, playerId).getAsJsonArray();
				System.out.println(tankJson.toString());
			} catch (PlayerNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}

	public int getTanksCounter() {
		return 30;
	}

}
