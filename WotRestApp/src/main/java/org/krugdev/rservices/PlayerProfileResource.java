package org.krugdev.rservices;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

import javax.ws.rs.core.StreamingOutput;

import org.jboss.resteasy.spi.NotFoundException;
import org.krugdev.domain.Platforms;
import org.krugdev.domain.XMLMarshaller;
import org.krugdev.domain.search.PlayerProfileBasic;

public class PlayerProfileResource implements PlayerProfileResourceRestAnnotations {

	@Override
	public StreamingOutput getPlayer(int playerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StreamingOutput getPlayerTanks(int playerId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public StreamingOutput search(String platform, String qry) {
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
				throw new NotFoundException("no players found");
			}
	}

	
}
