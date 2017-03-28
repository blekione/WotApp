package org.krugdev;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.krugdev.domain.PlayerWN8;
import org.krugdev.domain.TankWN8;
import org.krugdev.util.Platform;

import lombok.Getter;

public class WN8ServiceReader {

	public PlayerWN8 getPlayerWN8(String playerId, String platformString) {
		Platform platform = Platform.setPlatform(platformString);
		Client client = ClientBuilder.newClient();
		Response clientResponse = client.target(platform.getWN8ServiceUrl() + playerId)
				.request(MediaType.APPLICATION_XML).get();
		return clientResponse.readEntity(new GenericType<PlayerWN8>() {});	
		}

	public List<TankWN8> getTanksWN8(String playerId, String platformString) {
		Platform platform = Platform.setPlatform(platformString);
		Client client = ClientBuilder.newClient();
		Response clientResponse = client.target(platform.getWN8ServiceUrl() + playerId + "/tanks")
				.request(MediaType.APPLICATION_XML).get();
		return clientResponse.readEntity(new GenericType<List<TankWN8>>() {});
	}
}
