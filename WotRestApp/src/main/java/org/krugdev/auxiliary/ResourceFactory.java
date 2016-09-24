package org.krugdev.auxiliary;

import org.krugdev.domain.player.Player;
import org.krugdev.domain.playerTanks.PlayersTanks;
import org.krugdev.domain.searchPlayers.SearchPlayersResult;

public class ResourceFactory {

	public Resource createResource(String resourceName) {
		switch (resourceName) {
		case "players":
			return new SearchPlayersResult();
		case "player":
			return new Player();
		case "playersTanks":
			return new PlayersTanks();
		}
		return null;
	}

}
