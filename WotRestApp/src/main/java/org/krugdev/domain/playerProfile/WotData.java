package org.krugdev.domain.playerProfile;

import org.krugdev.domain.RequestingServices;
import org.krugdev.domain.playerProfile.JsonDataBeans.Clan;
import org.krugdev.domain.playerProfile.JsonDataBeans.Player;
import org.krugdev.domain.playerProfile.JsonDataBeans.PlayerClan;

public class WotData {

	Player player;
	PlayerClan playerClan;
	Clan clan;
	
	public WotData() {
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public PlayerClan getPlayerClan() {
		return playerClan;
	}

	public void setPlayerClan(PlayerClan playerClan) {
		this.playerClan = playerClan;
	}

	public Clan getClan() {
		return clan;
	}

	public void setClan(Clan clan) {
		this.clan = clan;
	}
}
