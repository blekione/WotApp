package org.krugdev.domain;

public enum RequestURL {
	
	XBOX_API_URL ("https://api-xbox-console.worldoftanks.com/wotx/account/"),
	PS_API_URL ("https://api-ps4-console.worldoftanks.com/wotx/account/");

	private final String URL;
	
	private RequestURL(String URL) {
		this.URL = URL;
	}
	
	public String toString() {
		return URL;
	}
}
