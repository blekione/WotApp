package org.krugdev.domain;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

@Getter
@XmlRootElement(name="sessionwn8")
public class SessionWN8 {
	
	@Setter
	double wn8Value = 0;
	@XmlAttribute
	int gamesCount = 0;
	@XmlAttribute
	double totalDamage = 0;
	@XmlAttribute
	int totalFrags = 0;
	@XmlAttribute
	int totalDefencePoints = 0;
/*	@XmlTransient
	List<DBTankItem> tanksPlayed;
	
	public void setTanksPlayed(List<DBTankItem> tanksPlayed) {
		this.tanksPlayed = tanksPlayed;
		setSessionValues();
	}

	public void setSessionValues() {
		for(DBTankItem tankItem : tanksPlayed) {
			gamesCount += tankItem.getGamesCount();
			totalDamage += tankItem.getDamageDealt();
			totalFrags += tankItem.getFrags();
			totalDefencePoints += tankItem.getDefencePoints();
 		}
	}
	*/
}
