package org.krugdev.rservices;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

import javax.ws.rs.core.StreamingOutput;

import org.krugdev.domain.PlayerBasicStatistics;
import org.krugdev.domain.XMLMarshaller;
import org.jboss.resteasy.spi.NotFoundException;

public class SearchResource implements SearchResourceRestAnnotations{
	
	public StreamingOutput query(String qry) {
		List<PlayerBasicStatistics> players = PlayerBasicStatistics.searchPlayers(qry);			
		return outputStream -> 
			outputPlayersList(outputStream, players);
	}
	
		private void outputPlayersList(OutputStream out, List<PlayerBasicStatistics> players) {
		PrintStream writer = new PrintStream(out);
		outputPlayersListAsXML(writer, players);	
	}

	private void outputPlayersListAsXML(PrintStream writer, List<PlayerBasicStatistics> players) {
			if (playersExist(players)) {
				XMLMarshaller.marshallListToXML(players, "players", writer);
				writer.flush();
			}
			else {
				throw new NotFoundException("no players");
			}
	}

	private boolean playersExist( List<PlayerBasicStatistics> players) {
		if (players != null && players.size() > 0) {
			return true;
		} 
		return false;
	}
}
