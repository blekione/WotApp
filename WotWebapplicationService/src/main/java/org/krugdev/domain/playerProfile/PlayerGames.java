package org.krugdev.domain.playerProfile;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;

@Getter
@XmlRootElement(name="playerGames")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlayerGames {

	private int battlesCount;
	private int battlesWins;
	private int battlesLosses;
	private int battlesDraws;
	private int battlesSurvived;
}
