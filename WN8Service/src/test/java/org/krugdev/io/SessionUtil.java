package org.krugdev.io;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;

public class SessionUtil {

	private static final EntityManagerFactory DEFAULT_EMF = Persistence.createEntityManagerFactory("testwn8");
	private final static SessionUtil instance = new SessionUtil();
	private EntityManagerFactory emf;
	
	private SessionUtil() {
		this.emf = DEFAULT_EMF;
	}
	
	public static SessionUtil getInstance() {
		return instance;
	}
	
	public static Session getSession() {
		return getInstance().emf.createEntityManager().unwrap(Session.class);
	}
	
	public static Session getSession(EntityManagerFactory emf) {
		getInstance().setEmf(emf);
		return getSession();
	}

	public static Session getSession(EntityManager em) {
		return em.unwrap(Session.class);
	}
	
	private void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}
}
