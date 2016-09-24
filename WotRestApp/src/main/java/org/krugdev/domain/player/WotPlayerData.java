package org.krugdev.domain.player;

import org.krugdev.domain.player.JSONDataBeans.ClanJSONBean;
import org.krugdev.domain.player.JSONDataBeans.PlayerClanJSONBean;
import org.krugdev.domain.player.JSONDataBeans.PlayerJSONBean;

public class WotPlayerData {

	PlayerJSONBean player;
	PlayerClanJSONBean playerClan;
	ClanJSONBean clan;
	
	public WotPlayerData() {
	}

	public PlayerJSONBean getPlayer() {
		return player;
	}

	public void setPlayer(PlayerJSONBean player) {
		this.player = player;
	}

	public PlayerClanJSONBean getPlayerClan() {
		return playerClan;
	}

	public void setPlayerClan(PlayerClanJSONBean playerClan) {
		this.playerClan = playerClan;
	}

	public ClanJSONBean getClan() {
		return clan;
	}

	public void setClan(ClanJSONBean clan) {
		this.clan = clan;
	}

	@Override
	public String toString() {
		return "WotPlayerData [player=" + player + ", playerClan=" + playerClan + ", clan=" + clan + "]";
	}
	
}
