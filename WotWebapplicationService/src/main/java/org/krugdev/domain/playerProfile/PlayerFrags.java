package org.krugdev.domain.playerProfile;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;

@Getter
@XmlRootElement(name="playerFrags")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlayerFrags {
	
	private long kills;
	private long deaths;
}
