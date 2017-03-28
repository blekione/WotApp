package org.krugdev.util;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.hibernate.Query;
import org.hibernate.Session;
import org.krugdev.domain.TankDescription;

public class TankDescriptionRepository {
	

	private Session session;
	private EntityManager em;

	public TankDescriptionRepository(EntityManager em) {
		
		this.em = em;
		session = em.unwrap(Session.class);
	}
	
	public TankDescription getTankDescription(int tankId) {
		Query query = session.createQuery("select p from TankDescription p where p.tank_id =:tankId");
		query.setParameter("tankId", tankId);
		return (TankDescription) query.uniqueResult();
	}

	public Map<Integer, TankDescription> getTankDescriptions(List<Integer> tankIds) {
		javax.persistence.Query query = em.createQuery("select p from TankDescription p where p.tank_id in :ids");
		query.setParameter("ids", tankIds);
		
		@SuppressWarnings("unchecked")
		List<TankDescription> tankDescriptions = query.getResultList();
		Map<Integer, TankDescription> result = 
				tankDescriptions.stream().collect(Collectors.toMap(TankDescription::getTank_id, Function.identity()));
		return result;
	}
}
