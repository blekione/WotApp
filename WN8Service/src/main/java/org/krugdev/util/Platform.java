package org.krugdev.util;

public enum Platform {
	XBOX("http://localhost:8080/WotRestApp/wotAPI/xbox-plt/players/"),
	PLAY_STATION("http://localhost:8080/WotRestApp/wotAPI/playstation-plt/players/");
	
	private String url;
	
	private Platform(String url) {
		this.url = url;
	}
	
	public static Platform setPlatform(String platform) {
		switch(platform) {
		case "playstation":
			return Platform.PLAY_STATION;
		case "xbox":
		default:
			return Platform.XBOX;
		}	
	}
	
	public String getUrl() {
		return this.url;
	}
}