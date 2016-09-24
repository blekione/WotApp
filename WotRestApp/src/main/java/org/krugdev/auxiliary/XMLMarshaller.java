package org.krugdev.auxiliary;

import java.io.PrintStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;


public class XMLMarshaller {

	public static void marshallObjectToXML(Object objectToMarshal, PrintStream writer) {
		JAXBContext ctx;
		try {
			ctx = JAXBContext.newInstance(objectToMarshal.getClass());
			Marshaller marshaller = ctx.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(objectToMarshal, writer);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}

