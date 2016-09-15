package org.krugdev.rservices;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

import javax.ws.rs.core.StreamingOutput;

import org.jboss.resteasy.spi.NotFoundException;
import org.krugdev.domain.Platforms;
import org.krugdev.domain.PlayerNotFoundException;
import org.krugdev.domain.XMLMarshaller;
import org.krugdev.domain.playerProfile.PlayerProfile;
import org.krugdev.domain.search.PlayerProfileBasic;

public class PlayerProfileResource implements PlayerProfileResourceRestAnnotations {

	Platforms platform;
	
	@Override
	public StreamingOutput getPlayer(String platformString, String playerId) {
		setPlatform(platformString);
		try {
			PlayerProfile playerProfile = PlayerProfile.getPlayerProfile(platform, playerId);
			return outputStream -> 
				outputPlayerProfileAsXML(outputStream, playerProfile);
		} catch (PlayerNotFoundException e) {
			throw new NotFoundException("no data for player with id " + playerId + " and platform " + platform);
		}
	}

	private void outputPlayerProfileAsXML(OutputStream outputStream, PlayerProfile playerProfile) {
		PrintStream writer = new PrintStream(outputStream);
		XMLMarshaller.marshallObjectToXML(playerProfile, writer);
		writer.flush(); 	
	}

	@Override
	public StreamingOutput getPlayerTanks(String platform, String playerId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public StreamingOutput search(String platformString, String qry) {
		setPlatform(platformString);	
			return outputStream -> 
			outputPlayersListAsXML(outputStream, PlayerProfileBasic.getPlayers(platform, qry));
	}	
	
	private void outputPlayersListAsXML(OutputStream outputStream, List<PlayerProfileBasic> players) {
		PrintStream writer = new PrintStream(outputStream);
		if (!players.isEmpty()) {
				XMLMarshaller.marshallListToXML(players, "players", writer);
				writer.flush();
			}
			else {
				throw new NotFoundException("no players found");
			}
	}

	private void setPlatform(String platform) {
		switch(platform) {
		case "playstation":
			this.platform = Platforms.PLAY_STATION;
			break;
		case "xbox":
		default:
			this.platform = Platforms.XBOX;
		}		
	}

}
