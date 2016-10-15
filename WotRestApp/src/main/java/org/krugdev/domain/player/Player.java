package org.krugdev.domain.player;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.krugdev.auxiliary.Platform;
import org.krugdev.domain.player.JSONDataBeans.PlayerClanJSONBean;
import org.krugdev.domain.player.JSONDataBeans.PlayerJSONBean;
import org.krugdev.domain.player.statistics.PlayerDamage;
import org.krugdev.domain.player.statistics.PlayerExperience;
import org.krugdev.domain.player.statistics.PlayerGamesCounters;
import org.krugdev.domain.player.statistics.PlayerKillsDeaths;
import org.krugdev.domain.player.statistics.PlayerMisc;
import org.krugdev.domain.player.statistics.PlayerStatistics;

@XmlRootElement(name="player")
@XmlAccessorType(XmlAccessType.NONE)
public class Player {
	
	@XmlAttribute
	private String playerId;
	@XmlAttribute	
	private Platform platform;
	
	@XmlElement
	private PlayerMisc playerMisc;
	@XmlElement
	private PlayerDamage playerDamage;
	@XmlElement
	private PlayerExperience playerExperience;
	@XmlElement
	private PlayerGamesCounters playerGames;
	@XmlElement
	private PlayerKillsDeaths playerFrags;
	@XmlElement
	private PlayerClanJSONBean playerClan;
	
	//default constructor required by XML parser
	public Player() {
	}

	public Player(Platform platform, String playerId) {
		this.platform = platform;
		this.playerId = playerId;
	}	
	
	public void populateWithData(PlayerJSONBean playerJSONBean) {
		
		List<PlayerStatistics> statistics= new ArrayList<>();
		statistics.add(playerMisc = new PlayerMisc());
		statistics.add( playerGames = new PlayerGamesCounters());
		statistics.add(playerFrags = new PlayerKillsDeaths());
		statistics.add(playerDamage = new PlayerDamage());
		statistics.add(playerExperience = new PlayerExperience());
		statistics.forEach((v) -> v.populateWithDataFromJsonDataHolder(playerJSONBean));
	}

	public void setPlayerClan(PlayerClanJSONBean playerClanJSONBean) {
		this.playerClan = playerClanJSONBean;
	}

	public String getNickname() {
		return playerMisc.getNickname();
	}

	public int getDaysInGame() {
		return playerMisc.getDaysInGame();
	} 

	public int getGamesPlayedCounter() {
		return playerGames.getBattlesCount();
	}

	public Long getKills() {
		return playerFrags.getKills();
	}
	
	public Long getDamageDealt() {
		return playerDamage.getDamageDealt();
	}

	public int getHighestExperience() {
		return playerExperience.getHighestExperience();
	}
	
	public String getPlayerClanId() {
		return playerClan.getClanId();
	}

	public Platform getPlatform() {
		return platform;
	}

	public String getPlayerId() {
		return playerId;
	}
}
