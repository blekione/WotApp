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
		
		try(final WebClient webClient = new WebClient(BrowserVersion.CHROME)) {
			url = new URL("https://console.worldoftanks.com/stats/players/search/?search="
					+ qry 
					+ "&page=0&order_by=name");
			WebRequest requestSetting = new WebRequest(url);
			requestSetting.setAdditionalHeader("X-Requested-With", "XMLHttpRequest");
			webClient.getOptions().setJavaScriptEnabled(true);
			final JavaScriptPage page = webClient.getPage(requestSetting);
			String pageCont = page.getContent();			
			return outputStream -> outputAnswer(outputStream, pageCont, 0);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			System.out.println("url not found");
		} 
		
		return null;
	}

	private void outputAnswer(OutputStream out, String input, int size) {
		PrintStream writer = new PrintStream(out);
		JsonParser parser = new JsonParser();
		JsonElement jsonTree = parser.parse(input);
		JsonObject element = jsonTree.getAsJsonObject();
		JsonObject t = element.get("data").getAsJsonObject();
		String string = t.toString();
		Gson gson = new Gson();
		PlayerBasicCointainer container = gson.fromJson(string, PlayerBasicCointainer.class);
		List<PlayerBasic> players = container.getItems();
		if (players != null && players.size() > 0) {
			writer.println(players.get(0));
		}	
	}
}
