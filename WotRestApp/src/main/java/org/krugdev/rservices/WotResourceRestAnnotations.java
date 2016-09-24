package org.krugdev.rservices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.krugdev.auxiliary.Resource;

@Path("/resource")
public interface WotResourceRestAnnotations {
	
	@GET
	@Path("{platform}/{resource}/{query}")
	@Produces("application/xml")
	public Resource getResource(@PathParam("platform") String platformString, 
			@PathParam("resource") String resourceName, @PathParam("query") String query);

}
