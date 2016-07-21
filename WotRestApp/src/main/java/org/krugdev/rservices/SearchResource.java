package org.krugdev.rservices;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.ws.rs.core.StreamingOutput;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import org.krugdev.domain.PlayerBasicStatistics;
import org.krugdev.domain.PlayerBasicCointainer;
import org.krugdev.domain.Wrapper;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.JavaScriptPage;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class SearchResource implements SearchResourceRestAnnotations{

	private final String REQUEST_URL = "https://console.worldoftanks.com/stats/players/search/?search=";
	
	public StreamingOutput query(String qry) {
		JavaScriptPage jsonPage = queryPage(qry);			
		return outputStream -> 
			outputPlayersList(outputStream, getPlayersListFromJson(jsonPage.getContent()));
	}
	
	private JavaScriptPage queryPage(String qry) {
		WebClient webClient = new WebClient(BrowserVersion.CHROME);
		WebRequest request = setupRequest(webClient, qry);
		return sendRequestToWebClient(webClient, request);
	}

	private WebRequest setupRequest(WebClient webClient, String qry) {
		WebRequest request = null;
		try {
			webClient.getOptions().setJavaScriptEnabled(true);
			URL urlToQuery = addQueryToUrl(qry);
			request = new WebRequest(urlToQuery);
			request.setAdditionalHeader("X-Requested-With", "XMLHttpRequest");
		} catch (Exception e) {
			System.out.println("url not found");
		}
		return request;
	}

	private URL addQueryToUrl(String qry) {
		URL url = null;
		try {
			url = new URL(REQUEST_URL	+ qry);
		} catch (MalformedURLException e) {
			System.out.println("wrong url format");
		}
		return url;
	}
	
	private JavaScriptPage sendRequestToWebClient(WebClient webClient, WebRequest request) {
		JavaScriptPage jsonPage = null;
		try {
		jsonPage = webClient.getPage(request);
		} catch (IOException e) {
			System.out.println("page request exception");
		}
		return jsonPage;
	}

	private List<PlayerBasicStatistics> getPlayersListFromJson(String jsonAsString) {
		JsonObject dataElement = extractDataElementFromJson(jsonAsString);		
		String dataElementAsString = dataElement.toString();
		return getPlayersList(dataElementAsString);
	}

	private JsonObject extractDataElementFromJson(String jsonInput) {
		JsonParser parser = new JsonParser();
		JsonElement jsonTree = parser.parse(jsonInput);
		JsonObject rootElement = jsonTree.getAsJsonObject();
		return rootElement.get("data").getAsJsonObject();
	}

	private List<PlayerBasicStatistics> getPlayersList(String jsonInput) {
		Gson gson = new Gson(); 
		return gson.fromJson(jsonInput, PlayerBasicCointainer.class).getItems();
	}

	private void outputPlayersList(OutputStream out, List<PlayerBasicStatistics> players) {
		PrintStream writer = new PrintStream(out);
		outputPlayersListAsXML(writer, players);	
	}

	private void outputPlayersListAsXML(PrintStream writer, List<PlayerBasicStatistics> players) {
			if (playersExist(players)) {
				marshallToXML(players, "players", writer);
			}		
			writer.flush();
	}

	private boolean playersExist( List<PlayerBasicStatistics> players) {
		if (players != null && players.size() > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	private static void marshallToXML(List<?> list, String rootElementName, PrintStream writer) {
		JAXBContext ctx;
		QName rootElement = new QName(rootElementName);
		Wrapper<?> wrapper = new Wrapper<>(list);
        @SuppressWarnings("rawtypes")
		JAXBElement<Wrapper> jaxbElement = new JAXBElement<Wrapper>(rootElement,
                Wrapper.class, wrapper);
		try{
			ctx = JAXBContext.newInstance(PlayerBasicStatistics.class, Wrapper.class);
			Marshaller marshaller = ctx.createMarshaller();
	        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	        marshaller.marshal(jaxbElement, writer);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
