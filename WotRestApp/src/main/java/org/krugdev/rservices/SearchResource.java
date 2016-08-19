package org.krugdev.rservices;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

import javax.ws.rs.core.StreamingOutput;

import org.krugdev.domain.Platforms;
import org.krugdev.domain.XMLMarshaller;
import org.krugdev.domain.search.PlayerProfileBasic;
import org.jboss.resteasy.spi.NotFoundException;

public class SearchResource implements SearchResourceRestAnnotations{
	
	public StreamingOutput query(String platform, String qry) {
		switch(platform) {
		case "playstation":
			return outputStream -> 
			outputPlayersList(outputStream, PlayerProfileBasic.getPlayers(Platforms.PLAY_STATION, qry));
		case "xbox": 
		default:
			return outputStream -> 
			outputPlayersList(outputStream, PlayerProfileBasic.getPlayers(Platforms.XBOX, qry));
		}
	}
	
		private void outputPlayersList(OutputStream out, List<PlayerProfileBasic> players) {
		PrintStream writer = new PrintStream(out);
		outputPlayersListAsXML(writer, players);	
	}

	private void outputPlayersListAsXML(PrintStream writer, List<PlayerProfileBasic> players) {
			if (!players.isEmpty()) {
				XMLMarshaller.marshallListToXML(players, "players", writer);
				writer.flush();
			}
			else {
				throw new NotFoundException("no players");
			}
	}
}
