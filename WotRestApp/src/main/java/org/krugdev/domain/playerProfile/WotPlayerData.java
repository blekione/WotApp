package org.krugdev.domain.playerProfile;

import org.krugdev.domain.playerProfile.JSONDataBeans.ClanJSONBean;
import org.krugdev.domain.playerProfile.JSONDataBeans.PlayerJSONBean;
import org.krugdev.domain.playerProfile.JSONDataBeans.PlayerClanJSONBean;

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
}
