package org.krugdev.rservices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.StreamingOutput;

@Path("/search")
public interface SearchResourceRestAnnotations {

	@GET
	@Path("{qry}")
	public StreamingOutput query(@PathParam("qry") String qry);
}
