package org.krugdev.wn8.expected;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class XMLTankExpectedValuesReader implements TankExpectedValuesReader {

	@Override
	public Map<Integer, TankExpectedValues> getTankEx(Object path) {
			Map<Integer, TankExpectedValues> tanksExpectedValMap = new HashMap<>();
			
			parseXMLFileWithExpValues((String)path).forEach(v -> {
			tanksExpectedValMap.put(v.getIdNum(), v);
			});
			return tanksExpectedValMap;
		
	}
	
	public void saveExpectedValuesInDB(String path) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exValues");
		EntityManager em = emf.createEntityManager();
		Session session = em.unwrap(Session.class);
		Transaction tx = session.beginTransaction();
		parseXMLFileWithExpValues(path).forEach(v -> {
			session.save(v);
			});
		tx.commit();
		session.close();
	}

	private List<TankExpectedValues> parseXMLFileWithExpValues(String path) {
		try {
			JAXBContext jc = JAXBContext.newInstance(WNExpectedValues.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			WNExpectedValues wnExpectedVal = (WNExpectedValues) unmarshaller.unmarshal(new File(path));
			return wnExpectedVal.getWn8().getTankExpectedvalues();
		} catch (JAXBException e) {
			e.printStackTrace();
			throw new RuntimeException("Problem with parsing XML file: " + path);
		}
	}
}
