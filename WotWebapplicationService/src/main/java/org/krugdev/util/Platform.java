package org.krugdev.util;

public enum Platform {
	XBOX ("http://localhost:8080/WotRestApp/wotAPI/XBOX-plt/players/",
			"http://localhost:8080//WN8Service/wn8service/playerWN8/xbox-plt/"),
	PLAY_STATION ("http://localhost:8080/WotRestApp/wotAPI/PLAY_STATION-plt/players/",
			"http://localhost:8080/WN8Service/wn8service/playerWN8/playstation-plt/");
	
	private String wotAPIurl;
	private String wn8ServiceUrl;
	
	private Platform(String wotAPIurl, String wn8ServiceUrl) {
		this.wotAPIurl = wotAPIurl;
		this.wn8ServiceUrl = wn8ServiceUrl;
	}
	
	public static Platform setPlatform(String platform) {
		switch(platform) {
		case "PlayStation":
			return Platform.PLAY_STATION;
		case "Xbox":
		default:
			return Platform.XBOX;
		}	
	}
	
	public String getWotAPIUrl() {
		return this.wotAPIurl;
	}
	
	public String getWN8ServiceUrl() {
		return this.wn8ServiceUrl;
	}
}