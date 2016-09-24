package org.krugdev.auxiliary;

import java.io.OutputStream;
import java.io.PrintStream;

public class OutputWritter {
	
	public void toXml(Resource resource, OutputStream outputStream){
		PrintStream writer = new PrintStream(outputStream);
		XMLMarshaller.marshallObjectToXML(resource, writer);
		writer.flush();
	}

}
