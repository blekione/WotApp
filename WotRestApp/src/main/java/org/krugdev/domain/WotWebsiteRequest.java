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
	private final static String APPLICATION_ID = "9d54f44c84a927987630b25b62efdd2c";

	public WotWebsiteRequest(Platforms platform, RequestingServices requestingService) {
		this.platform = platform;
		this.requestingService = requestingService;
	}
	
	public String getJsonWithPLayers(String query) {
		
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

	private Optional<URL> buildUrlToQuery(String qry) {
		try {
			switch (requestingService){
			case SEARCH:
			default:
				URL url = new URL(getPlatformAPIDomain() 
						+ "list/?application_id=" + APPLICATION_ID
						+ "&search=" + qry);
				return Optional.of(url);
			}
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
}
