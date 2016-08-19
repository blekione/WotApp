package org.krugdev.domain;

import java.io.PrintStream;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import org.krugdev.domain.search.PlayerProfileBasic;

public class XMLMarshaller {

	public static void marshallListToXML(List<?> list, String rootElementName, PrintStream writer) {
		JAXBContext ctx;
		QName rootElement = new QName(rootElementName);
		MarshallerListWrapper<?> wrapper = new MarshallerListWrapper<>(list);
        @SuppressWarnings("rawtypes")
		JAXBElement<MarshallerListWrapper> jaxbElement = new JAXBElement<MarshallerListWrapper>(rootElement,
                MarshallerListWrapper.class, wrapper);
		try{
			ctx = JAXBContext.newInstance(PlayerProfileBasic.class, MarshallerListWrapper.class);
			Marshaller marshaller = ctx.createMarshaller();
	        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	        marshaller.marshal(jaxbElement, writer);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
