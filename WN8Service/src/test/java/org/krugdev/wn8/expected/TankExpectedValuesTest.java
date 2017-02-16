package org.krugdev.wn8.expected;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Map;

import org.junit.Test;

public class TankExpectedValuesTest {
	
	String tankExpValuesXML = "src/main/resources/expected_tank_values_29.xml";
	
	@Test
	public void test() throws IOException {
		Parser parser = new XmlParser();
		Map<Integer, TankExpectedValues> tankExpValues = parser.getTankEx(tankExpValuesXML);
		assertEquals(505, tankExpValues.size());
	}
}
