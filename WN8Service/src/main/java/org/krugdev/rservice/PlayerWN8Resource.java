package org.krugdev.rservice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.krugdev.io.WN8DBService;
import org.krugdev.io.utility.SessionUtil;
import org.krugdev.rservice.domain.PlayerWN8;
import org.krugdev.util.Platform;
import org.krugdev.wn8.PlayerTanks;
import org.krugdev.wn8.db.DBTankItem;
import org.krugdev.wn8.db.PlayerTanksTimestamp;

@Stateless
public class PlayerWN8Resource implements PlayerWN8ResourceRESTAnnotations {

	Platform platform;
	WN8DBService service;
	List<DBTankItem> tankItems;
	
	@PersistenceContext(unitName="servicewn8")
	private EntityManager em;
	
	public PlayerWN8Resource() {
	}
	
	public PlayerWN8Resource(Platform platform) {
		this.platform = platform;
	}

	@Override
	public PlayerWN8 getPlayerWN8(String playerIdString) {
		Session session = em.unwrap(Session.class);
		service = new WN8DBService(session);
		addTanksTimeStampsInDB(1, 12345);
		System.out.println("Im working");
		System.out.println(em == null);
		return new PlayerWN8(234, playerIdString);
	}
	
	private List<PlayerTanks> addTanksTimeStampsInDB(int amount, int... playerIds) {
		List<PlayerTanks> playerTanksList = new ArrayList<>();
		for (int i = 0; i < amount; i++) {
			for (int j = 0; j < playerIds.length; j++) {
				DBTankItem tankItem1 = new DBTankItem();
				DBTankItem tankItem2 = new DBTankItem();
				tankItems = Arrays.asList(tankItem1, tankItem2);
				PlayerTanks playerTanks = new PlayerTanksTimestamp(playerIds[j], tankItems, 1000);
				tankItem1.setPlayer((PlayerTanksTimestamp) playerTanks);
				tankItem2.setPlayer((PlayerTanksTimestamp) playerTanks);				
				service.savePlayerTanks(playerTanks);
				playerTanksList.add(playerTanks);
				try {
					Thread.sleep(1000); // to make timestamps with different time
				} catch (InterruptedException e) {
					System.out.println("thread has been interrupted when sleeping for 1s");
				}
			}
		}		
		return playerTanksList;
	}	

}
