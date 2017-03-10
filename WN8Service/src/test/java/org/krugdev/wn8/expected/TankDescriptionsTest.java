package org.krugdev.wn8.expected;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import com.google.gson.stream.JsonReader;

public class TankDescriptionsTest {

	String tankExpValuesXML = "src/main/resources/tanks.json";
	
	@Test
	public void test() throws FileNotFoundException {
		JsonReader reader = new JsonReader(new FileReader(new File(tankExpValuesXML)));
		JsonParser parser = new JsonParser();
		JsonObject jsonObject = parser.parse(reader).getAsJsonObject();
		JsonElement data = jsonObject.get("data");
		Type mapType = new TypeToken<Map<String, TankDescription>>(){}.getType();
		Map<Integer, TankDescription> tankDescriptions = new Gson().fromJson(data, mapType);
		System.out.println(tankDescriptions.size());
		List<TankDescription> descriptionList = new ArrayList<TankDescription>(tankDescriptions.values());
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("testwn8");
		EntityManager em = emf.createEntityManager();
		Session session = em.unwrap(Session.class);
		Transaction tx = session.beginTransaction();
		tankDescriptions.entrySet().iterator().forEachRemaining(v -> session.save(v.getValue()));
		tx.commit();
		session.close();
		
		assertFalse(jsonObject.isJsonNull());
		
	}

}