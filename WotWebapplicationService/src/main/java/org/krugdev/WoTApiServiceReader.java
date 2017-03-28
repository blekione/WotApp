package org.krugdev;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.krugdev.domain.SearchPlayerResult;
import org.krugdev.domain.playerProfile.PlayerProfile;
import org.krugdev.util.Platform;

public class WoTApiServiceReader {

	
	public List<SearchPlayerResult> searchForPlayers(String platformString, String query) {
		Platform platform = Platform.setPlatform(platformString);
		Client client = ClientBuilder.newClient();
		Response clientResponse = client.target(platform.getWotAPIUrl() + "search/" + query)
		.request(MediaType.APPLICATION_XML).get();
		if (clientResponse.getStatus() == 404) {
			return Collections.emptyList();
		}
		return clientResponse.readEntity(new GenericType<List<SearchPlayerResult>>() {});
	}

	public Optional<PlayerProfile> getPlayerProfile(String playerId, String platformString) {
		Platform platform = Platform.setPlatform(platformString);
		Client client = ClientBuilder.newClient();	
		Response clientResponse = client.target(platform.getWotAPIUrl() + playerId)
				.request(MediaType.APPLICATION_XML).get();
		if (clientResponse.getStatus() == 400) {
			return Optional.empty();
		}	
		return Optional.of(clientResponse.readEntity(new GenericType<PlayerProfile>() {}));
	}
}
