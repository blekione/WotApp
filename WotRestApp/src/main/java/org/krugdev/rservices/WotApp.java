package org.krugdev.rservices;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/wotAPI")
public class WotApp extends Application {
	
	public Set<Class<?>> getClasses() {
		HashSet<Class<?>> set = new HashSet<>();
		set.add(PlatformResource.class);
		return set;
	}
}
