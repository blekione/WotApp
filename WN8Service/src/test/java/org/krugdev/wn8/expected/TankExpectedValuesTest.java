package org.krugdev.wn8.expected;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.junit.Test;

public class TankExpectedValuesTest {
	
	String tankExpValuesXML = "src/main/resources/expected_tank_values_29.xml";
	
	@Test
	public void readValuesFromXMLFile() throws IOException {
		TankExpectedValuesReader parser = new XMLTankExpectedValuesReader();
		Map<Integer, TankExpectedValues> tankExpValues = parser.getTankEx(tankExpValuesXML);
		assertEquals(556, tankExpValues.size());
	}
	
	@Test
	public void saveCollectionOfExpValInDB() {
		XMLTankExpectedValuesReader parser = new XMLTankExpectedValuesReader();
		parser.saveExpectedValuesInDB(tankExpValuesXML);
	}
	@Test
	public void readValuesFromDB() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exValues");
		EntityManager em = emf.createEntityManager();
		Session session = em.unwrap(Session.class);
		TankExpectedValuesReader parser = new DBTankExpectedValuesReader();
		Map<Integer, TankExpectedValues> tankExpValues = parser.getTankEx(session);
		assertEquals(556, tankExpValues.size());
	}
}