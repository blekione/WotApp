package org.krugdev.io;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.krugdev.wn8.PlayerTanks;
import org.krugdev.wn8.TankItem;

public class WN8DOARepository implements Reader, Writer {
	
	private final EntityManager em;

	public WN8DOARepository(EntityManager em) {
		this.em = em;
	}

	@Override
	public List<TankItem> getPlayerTanks(int playerId) {
		// TODO Auto-generated method stub
		return null;
	}

	public void persist(Object... objects) {
		Session session = em.unwrap(Session.class);
		Transaction tx = session.beginTransaction();
		for (Object object : objects) {
			session.save(object);
		}
		tx.commit();
		/*
		em.getTransaction().begin();
		for(Object object : objects) {
		em.persist(object);
		}
		em.getTransaction().commit();
		em.close();
		*/
	}

	@Override
	public void savePlayerTanks(PlayerTanks player) {
		Session session = em.unwrap(Session.class);
		Transaction tx = session.beginTransaction();
		session.save(player);
		tx.commit();
		
	}

}
