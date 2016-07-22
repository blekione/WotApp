package org.krugdev.domain;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.JavaScriptPage;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;

public class WotWebsiteRequest {

	private final static String REQUEST_URL = "https://console.worldoftanks.com/stats/players/search/?search=";
	
	public static JavaScriptPage requestPage(String query) {
		WebClient webClient = new WebClient(BrowserVersion.CHROME);
		WebRequest request = setupRequest(webClient, query);
		return sendRequest(webClient, request);
	}
	
	public static WebRequest setupRequest(WebClient webClient, String qry) {
		webClient.getOptions().setJavaScriptEnabled(true);
		URL urlToQuery = addQueryToUrl(qry);
		WebRequest request = new WebRequest(urlToQuery);
		request.setAdditionalHeader("X-Requested-With", "XMLHttpRequest");
		return request;
	}

	private static URL addQueryToUrl(String qry) {
		try {
			return new URL(REQUEST_URL	+ qry);
		} catch (MalformedURLException e) {
			System.out.println("wrong url format");
			return null;
		}
	}

	private static JavaScriptPage sendRequest(WebClient webClient, WebRequest request) {
		try {
			return webClient.getPage(request);
		} catch (IOException e) {
			System.out.println("page request exception");
			return null;
		}
	}
}
