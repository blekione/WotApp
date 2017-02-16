package org.krugdev.wn8.expected;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class XmlParser implements Parser {

	@Override
	public Map<Integer, TankExpectedValues> getTankEx(String path) {
		try {
			JAXBContext jc = JAXBContext.newInstance(WNExpectedValues.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			WNExpectedValues wnExpectedVal = (WNExpectedValues) unmarshaller.unmarshal(new File(path));
			List<TankExpectedValues> tanks = wnExpectedVal.getWn8().getTankExpectedvalues();
			Map<Integer, TankExpectedValues> tanksExpectedValMap = new HashMap<>();
			tanks.forEach(v -> tanksExpectedValMap.put(v.getIDNum(), v));
			return tanksExpectedValMap;
		} catch (JAXBException e) {
			e.printStackTrace();
			throw new RuntimeException("Problem with parsing XML file: " + path);
		}
	}

}
