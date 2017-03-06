package org.krugdev.wn8.expected;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;

public class DBTankExpectedValuesReader implements TankExpectedValuesReader {

	@Override
	public Map<Integer, TankExpectedValues> getTankEx(Object resource) {
		Session session;
		if (resource instanceof Session) {
			session = (Session) resource;
		} else {
			throw new IllegalArgumentException("mothod needs org.hibernate.Session object as parameter");
		}
		
		Query query = session.createQuery("from TankExpectedValues");
		@SuppressWarnings("unchecked")
		List<TankExpectedValues> tanks = query.list();
		Map<Integer, TankExpectedValues> tanksExpectedValMap = new HashMap<>();
		tanks.forEach(v -> {
			tanksExpectedValMap.put(v.getIdNum(), v);
		});
		return tanksExpectedValMap;
	}
}
