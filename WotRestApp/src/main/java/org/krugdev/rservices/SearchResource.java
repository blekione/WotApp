package org.krugdev.rservices;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.ws.rs.core.StreamingOutput;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.JavaScriptPage;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;


public class SearchResource implements SearchResourceI{

	
	public StreamingOutput query(String qry) {
		URL url;
		Document doc = null;
		
		try(final WebClient webClient = new WebClient(BrowserVersion.CHROME)) {
			url = new URL("https://console.worldoftanks.com/stats/players/search/?search="
					+ qry 
					+ "&page=0&order_by=name");
			WebRequest requestSetting = new WebRequest(url);
			requestSetting.setAdditionalHeader("X-Requested-With", "XMLHttpRequest");
			webClient.getOptions().setJavaScriptEnabled(true);
			final JavaScriptPage page = webClient.getPage(requestSetting);
			String pageCont = page.getContent();
//			final String pageAsText = page.asXml();
//			doc = Jsoup.parse(pageAsText);
//			Elements players = doc.select("a.player-link");
			
			return outputStream -> outputAnswer(outputStream, pageCont, 0);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			System.out.println("url not found");
		} 
		
		return null;
	}

	private void outputAnswer(OutputStream out, String players, int size) {
		PrintStream writer = new PrintStream(out);
		writer.println(players);
		
	}
}
