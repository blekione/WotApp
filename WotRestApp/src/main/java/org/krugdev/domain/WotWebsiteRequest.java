package org.krugdev.domain;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;

public class WotWebsiteRequest {

	private String requestingService;
	private final static String SEARCH_URL = "https://console.worldoftanks.com/stats/players/search/?search=";
	private final static String PLAYER_URL = "https://console.worldoftanks.com/stats/players/";
	
	public Object requestPage(String requestService, String query) {
		requestingService = requestService;
		try(WebClient webClient = setupWebClient()) {
		WebRequest request = setupRequest(webClient, query);
		return sendRequest(webClient, request);
		} catch (IOException e) {
			System.out.println("problem with request");
		} catch (FailingHttpStatusCodeException e) {
			System.out.println("problem with request");
		}
		return null;
	}
	
	private WebClient setupWebClient() {
		WebClient webClient = new WebClient(BrowserVersion.CHROME);
		switch (requestingService) {
		case "search":
			webClient.getOptions().setJavaScriptEnabled(true);
			break;
		case "playerProfile":
			webClient.getOptions().setCssEnabled(false);
			webClient.getOptions().setAppletEnabled(false);
			webClient.getOptions().setJavaScriptEnabled(false);
			break;
		}
		return webClient;
	}

	private WebRequest setupRequest(WebClient webClient, String qry) {
		URL urlToQuery = addQueryToUrl(qry);
		WebRequest request = new WebRequest(urlToQuery);
		if (requestingService.equals("search")) {
			request.setAdditionalHeader("X-Requested-With", "XMLHttpRequest");
		}
		return request;
	}

	private URL addQueryToUrl(String qry) {
		try {
			return new URL(getServiceUrl() + qry); 
		} catch (MalformedURLException e) {
			throw new IllegalStateException(e);
		} 
	}

	private String getServiceUrl() {
		switch (requestingService) {
		case "search": return SEARCH_URL;
		case "playerProfile": return PLAYER_URL;
		default: 
			throw new IllegalStateException("Unexpected requestingService value= " + requestingService);
		}
	}
	private Object sendRequest(WebClient webClient, WebRequest request) 
			throws FailingHttpStatusCodeException, IOException {
			return (Object)webClient.getPage(request);
	}
}
