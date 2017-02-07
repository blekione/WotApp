package org.krugdev.auxiliary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.apache.http.HttpRequest;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.omg.CORBA.TIMEOUT;

public class WotWebsiteRequest {

	private Platform platform;
	private RequestingServices requestingService;
	private final static String APP_ID_URL_PARAMETER = "?application_id=9d54f44c84a927987630b25b62efdd2c";
	private static final int TIMEOUT = 100;

	public WotWebsiteRequest(Platform platform, RequestingServices requestingService) {
		this.platform = platform;
		this.requestingService = requestingService;
	}
	
	public String getJsonFromWotAPI(String query) {		
		try {
			URL requestURLToWotAPI = buildUrlToQuery(query).get();
			InputStreamReader inputStreamFromWotAPI = getDataInput(requestURLToWotAPI);
			BufferedReader inputReaderFromWotAPI = new BufferedReader(inputStreamFromWotAPI);
			StringBuilder jsonStringFromWotAPI = new StringBuilder();
			String inputLine;
			while ((inputLine = inputReaderFromWotAPI.readLine()) != null) {
				jsonStringFromWotAPI.append(inputLine);
			}
			return jsonStringFromWotAPI.toString();
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
		case PLAYER_TANKS:
			return new String("tanks/stats/"+ APP_ID_URL_PARAMETER + 
					"&account_id=" + query);
		case CLAN:
			return new String("clans/info/" + APP_ID_URL_PARAMETER + 
					"&clan_id=" + query);
		case PLAYER_CLAN:
			return new String("clans/accountinfo/" + APP_ID_URL_PARAMETER
					+ "&account_id=" + query);
		case PLAYER_PROFILE:
			return new String("account/info/" + APP_ID_URL_PARAMETER
					+ "&account_id=+" + query);
		case SEARCH:
		default:	
			return new String ("account/list/" + APP_ID_URL_PARAMETER
					+ "&search=" + query);
		}
	}

	public void setRequestingService(RequestingServices requestingService) {
		this.requestingService = requestingService;
	}
}
