package org.krugdev.domain;

public class ResourceFactory {

	public Resource createResource(String resourceName) {
		switch (resourceName) {
		case "players":
			return new PlayersList();
		}
		return null;
	}

}
