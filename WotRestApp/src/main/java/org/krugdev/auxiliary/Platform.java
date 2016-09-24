package org.krugdev.auxiliary;

public enum Platform {
	XBOX,
	PLAY_STATION;
	
	public static Platform setPlatform(String platform) {
		switch(platform) {
		case "playstation":
			return Platform.PLAY_STATION;
		case "xbox":
		default:
			return Platform.XBOX;
		}	
	}
}
