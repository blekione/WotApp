package org.krugdev.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.Optional;

public class WotWebsiteRequest {

	private Platforms platform;
	private RequestingServices requestingService;
	private final static String APP_ID_URL_PARAMETER = "?application_id=9d54f44c84a927987630b25b62efdd2c";

	public WotWebsiteRequest(Platforms platform, RequestingServices requestingService) {
		this.platform = platform;
		this.requestingService = requestingService;
	}
	
	public String getJsonFromWotAPI(String query) {		
		try {
			URL requestURL = buildUrlToQuery(query).get();
			InputStreamReader inputStream = getDataInput(requestURL);
			BufferedReader in = new BufferedReader(inputStream);
			StringBuffer response = new StringBuffer();
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			return response.toString();
		} catch (NoSuchElementException e) {
			System.out.println("problem with url builder");
		} catch (ProtocolException e) {
			System.out.println("problem with request");
		} catch (IOException e) {
			System.out.println("IOException - not connected to WoT server");
		}
		return "{}";
	}
	
	private InputStreamReader getDataInput(URL requestURL) {
		HttpURLConnection connection = null;
		try {
			connection = (HttpURLConnection) requestURL.openConnection();
			connection.setRequestMethod("GET");
			return new InputStreamReader(connection.getInputStream());
		} catch (IOException e) {
			System.out.println("IOException - not connected to WoT server");
			return new InputStreamReader(connection.getErrorStream());
		}
	}

	private Optional<URL> buildUrlToQuery(String query) {
		try {
			URL url = new URL(getPlatformAPIDomain() + buildPathToRequestedResource(query));
			return Optional.of(url);
		} catch (MalformedURLException e) {
			return Optional.empty();		}
	}

	private String getPlatformAPIDomain() {
		switch (platform) {
		case XBOX: return RequestURL.XBOX_API_URL.toString();
		case PLAY_STATION:
		default:
			return RequestURL.PS_API_URL.toString();
		}
	}
	
	private String buildPathToRequestedResource(String query) {
		switch (requestingService){
		case PLAYER_PROFILE:
			return new String("info/" + APP_ID_URL_PARAMETER
					+ "&account_id=+" + query);
		case SEARCH:
		default:	
			return new String ("list/" + APP_ID_URL_PARAMETER
					+ "&search=" + query);
		}
	}
}
