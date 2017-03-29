package org.krugdev;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.krugdev.domain.PlayerWN8;
import org.krugdev.domain.SessionWN8;
import org.krugdev.domain.TankWN8;
import org.krugdev.util.Platform;


public class WN8ServiceReader {

	private static WN8ServiceReader readerInstance;
	
	private static Client client;
	private Platform platform;
	
	public static WN8ServiceReader getInstance(String platformString) {	
		if (readerInstance == null) {
			readerInstance =  new WN8ServiceReader();
		}	
		readerInstance.setPlatform(platformString);
		return readerInstance;
	}
	
	public WN8ServiceReader() {
		client = ClientBuilder.newClient();
	}
	
	public PlayerWN8 getPlayerWN8(String playerId) {
		Response clientResponse = client.target(platform.getWN8ServiceUrl() + playerId)
				.request(MediaType.APPLICATION_XML).get();
		PlayerWN8 playerWN8 = clientResponse.readEntity(new GenericType<PlayerWN8>() {});
		clientResponse.close();
		return playerWN8;
		}

	public List<TankWN8> getTanksWN8(String playerId) {
		Response clientResponse = client.target(platform.getWN8ServiceUrl() + playerId + "/tanks")
				.request(MediaType.APPLICATION_XML).get();
		List<TankWN8> tanksWN8s = clientResponse.readEntity(new GenericType<List<TankWN8>>() {});
		clientResponse.close();
		return tanksWN8s;
	}
	
	public SessionWN8 startSessionWN8(String playerId, String sessionId) {
		Response clientResponse = client.target(platform.getWN8ServiceUrl() + playerId + "/" + sessionId + "-session").request().put(null);
		clientResponse.close();
		return new SessionWN8();
	}

	public SessionWN8 getRunningSessionWN8(String playerId, String sessionId) {
		Response clientResponse = client.target(platform.getWN8ServiceUrl() + playerId + "/" + sessionId + "-session")
				.request(MediaType.APPLICATION_XML).get();
		SessionWN8 sessionWN8 = clientResponse.readEntity(SessionWN8.class);
		clientResponse.close();
		return sessionWN8;
	}

	public SessionWN8 closeRunningSessionWN8(String playerId, String sessionId) {
		Response clientResponse = client.target(platform.getWN8ServiceUrl() + playerId + "/" + sessionId + "-session")
				.request(MediaType.APPLICATION_XML).delete();
		SessionWN8 sessionWN8 = clientResponse.readEntity(SessionWN8.class);
		clientResponse.close();
		return sessionWN8;
	}	
	
	protected void setPlatform(String platformString) {
		this.platform = Platform.setPlatform(platformString);
	}
}
