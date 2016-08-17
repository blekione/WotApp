package org.krugdev.rservices;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/root")
public class WotApp extends Application {
	
	public Set<Class<?>> getClasses() {
		HashSet<Class<?>> set = new HashSet<>();
		set.add(SearchResource.class);
		set.add(PlayerProfileResource.class);
		return set;
	}
}
