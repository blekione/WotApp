package org.krugdev.domain.player.statistics;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import org.krugdev.domain.player.JSONDataBeans.PlayerJSONBean;

@XmlRootElement
public class PlayerMisc extends PlayerStatistics { 
	
	private String nickname;
	private Long personalRating;
	private int daysInGame;
	private Long vehiclesSpotted;
	private Long baseCapturePoints;
	private Long baseDefensePoints;

	public void populateWithDataFromJsonDataHolder(PlayerJSONBean playerJSONBean) {
		this.nickname = playerJSONBean.getNickname();
		this.personalRating = playerJSONBean.getGlobalRating();
		this.daysInGame = changeTimeToDays(playerJSONBean.getCreatedAt());
		this.vehiclesSpotted = playerJSONBean.getStatistics().getAll().getSpotted();
		this.baseDefensePoints = playerJSONBean.getStatistics().getAll().getDroppedCapturePoints();
		this.baseCapturePoints = playerJSONBean.getStatistics().getAll().getCapturePoints();
	}

	private static int changeTimeToDays(long timeInMs) {
		Date date = new Date();
		return (int)((date.getTime() / 1000) - timeInMs) / 86400;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Long getPersonalRating() {
		return personalRating;
	}

	public void setPersonalRating(Long personalRating) {
		this.personalRating = personalRating;
	}

	public int getDaysInGame() {
		return daysInGame;
	}

	public void setDaysInGame(int daysInGame) {
		this.daysInGame = daysInGame;
	}

	public Long getVehiclesSpotted() {
		return vehiclesSpotted;
	}

	public void setVehiclesSpotted(Long vehiclesSpotted) {
		this.vehiclesSpotted = vehiclesSpotted;
	}

	public Long getBaseCapturePoints() {
		return baseCapturePoints;
	}

	public void setBaseCapturePoints(Long baseCapturePoints) {
		this.baseCapturePoints = baseCapturePoints;
	}

	public Long getBaseDefensePoints() {
		return baseDefensePoints;
	}

	public void setBaseDefensePoints(Long baseDefensePoints) {
		this.baseDefensePoints = baseDefensePoints;
	}	

}
