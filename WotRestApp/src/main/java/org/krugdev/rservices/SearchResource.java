package org.krugdev.rservices;

import java.io.OutputStream;
import java.io.PrintStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.StreamingOutput;


public class SearchResource implements SearchResourceI{

	
	public StreamingOutput getAnswer() {
		return outputStream -> outputAnswer(outputStream); 	
	}

	private void outputAnswer(OutputStream out) {
		PrintStream writer = new PrintStream(out);
		writer.println("<tag>answer</tag>");
	}
}
