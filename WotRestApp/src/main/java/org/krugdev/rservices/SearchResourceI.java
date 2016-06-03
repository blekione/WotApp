package org.krugdev.rservices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.StreamingOutput;

@Path("/search")
public interface SearchResourceI {

	@GET
	public StreamingOutput getAnswer();
}
