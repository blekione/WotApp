package org.krugdev.io;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.engines.ApacheHttpClient4Engine;
import org.krugdev.wn8.XML.TankItem;

public class WotAPIServiceReader implements Reader {

	private static final int CONNECTION_TIMEOUT = 2000;
	private static final int REQUEST_TIMEOUT = 200;
	private final String serviceUriLocation;
	private Client client;
	

	public WotAPIServiceReader(String serviceUriLocation) {
		this.serviceUriLocation = serviceUriLocation;
		 client = setClient();
	}

	@Override
	public List<TankItem> getPlayerTanks(int playerId) {
		List<TankItem> tankItems = client.target(serviceUriLocation + playerId + "/tanks" )
				.request(MediaType.APPLICATION_XML)
				.get(new GenericType<List<TankItem>>() {});
		return tankItems;
	}
		
	private Client setClient() {
//		RequestConfig reqConfig = RequestConfig.custom()
//				.setConnectTimeout(CONNECTION_TIMEOUT)
//				.setSocketTimeout(CONNECTION_TIMEOUT)
//				.setConnectionRequestTimeout(REQUEST_TIMEOUT)
//				.build();
//		CloseableHttpClient httpClient = HttpClientBuilder.create()
//				.setDefaultRequestConfig(reqConfig)
//				.build();
//		return new ResteasyClientBuilder().httpEngine(new ApacheHttpClient4Engine(httpClient, true)).build();
		return ClientBuilder.newClient();
	}
}
