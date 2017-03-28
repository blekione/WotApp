package org.krugdev.domain.playerProfile;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;

@Getter
@XmlRootElement(name="playerMisc")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlayerMisc {
	private String nickname;
	private Long personalRating;
	private int daysInGame;
	private Long vehiclesSpotted;
	private Long baseCapturePoints;
	private Long baseDefensePoints;
}
