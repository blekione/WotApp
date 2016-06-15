package org.krugdev.rservices;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.ws.rs.core.StreamingOutput;

import org.krugdev.domain.PlayerBasic;
import org.krugdev.domain.PlayerBasicCointainer;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.JavaScriptPage;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class SearchResource implements SearchResourceI{

	
	public StreamingOutput query(String qry) {
		URL url;
		JavaScriptPage page = null;
		
		try(final WebClient webClient = new WebClient(BrowserVersion.CHROME)) {
			url = new URL("https://console.worldoftanks.com/stats/players/search/?search="
					+ qry 
					+ "&page=0&order_by=name");
			WebRequest requestSetting = new WebRequest(url);
			requestSetting.setAdditionalHeader("X-Requested-With", "XMLHttpRequest");
			webClient.getOptions().setJavaScriptEnabled(true);
			page = webClient.getPage(requestSetting);			
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			System.out.println("url not found");
		} 
		String output = page.toString();
		return outputStream -> 
			outputAnswer(outputStream, parsePageContent(output));
	}

	private List<PlayerBasic> parsePageContent(String input) {
		JsonParser parser = new JsonParser();
		JsonElement jsonTree = parser.parse(input);
		JsonObject element = jsonTree.getAsJsonObject();
		JsonObject t = element.get("data").getAsJsonObject();
		String string = t.toString();
		Gson gson = new Gson();
		PlayerBasicCointainer container = 
				gson.fromJson(string, PlayerBasicCointainer.class);
		List<PlayerBasic> players = container.getItems();
		return players;
	}

	private void outputAnswer(OutputStream out, List<PlayerBasic> players) {
		PrintStream writer = new PrintStream(out);
		StringBuilder playersString = new StringBuilder();
		if (players != null && players.size() > 0) {
			for (PlayerBasic player: players){
				playersString.append("<p>" + player.toString() + "</p>");
			}
		}
		
		writer.println(playersString);	
	}
}
