package org.krugdev.domain.playerProfile;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;

@Getter
@XmlRootElement(name="playerExperience")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlayerExperience {
	private Long totalExperience;
	private int highestExperience;
	private int highestExpTankId; 
}
