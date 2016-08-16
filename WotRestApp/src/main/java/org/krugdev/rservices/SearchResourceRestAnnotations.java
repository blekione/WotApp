package org.krugdev.rservices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.StreamingOutput;

@Path("/search")
public interface SearchResourceRestAnnotations {

	@GET
	@Path("{platform}/{qry}")
	public StreamingOutput query(@PathParam("platform") String platform ,@PathParam("qry") String qry);
}
