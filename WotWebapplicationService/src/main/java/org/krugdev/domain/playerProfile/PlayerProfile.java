package org.krugdev.domain.playerProfile;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;

@XmlRootElement(name="player")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
public class PlayerProfile {
	@XmlAttribute
	private int playerId;
	@XmlAttribute
	private String platform;
	private PlayerMisc playerMisc;
	private PlayerDamage playerDamage;
	private PlayerExperience playerExperience;
	private PlayerGames playerGames;
	private PlayerFrags playerFrags;
	private PlayerClan playerClan;
	
	public double getWinRatio() {
		return (playerGames.getBattlesCount() != 0) ?
		 ((double) playerGames.getBattlesWins() / playerGames.getBattlesCount()) * 100
		: 0;
	}
	
	public int getGamesPlayed() {
		return playerGames.getBattlesCount();
	}
	
	public long getAverageDamage() {
		return (playerGames.getBattlesCount() != 0) ?
				playerDamage.getDamageDealt() / playerGames.getBattlesCount()
				: 0;
	}
	
	public double getKillToDeathRatio() {
		return (playerGames.getBattlesCount() != 0 && playerFrags.getDeaths() != 0) ?
				(double) playerFrags.getKills() / playerFrags.getDeaths()
				: 0;
	}

	public double getKillsPerGameRatio() {
		
		return (playerGames.getBattlesCount() != 0) ?
				(double) playerFrags.getKills() / playerGames.getBattlesCount()
				: 0;
	}
	
	public long getAverageExperience() {
		return (playerGames.getBattlesCount() != 0) ?
				 (playerExperience.getTotalExperience() / playerGames.getBattlesCount())
				: 0;
	}
	
	public LocalDate getInGameSinceDate() {
		return LocalDate.now().minusDays(playerMisc.getDaysInGame());
	}
}
