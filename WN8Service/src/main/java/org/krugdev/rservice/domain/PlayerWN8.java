package org.krugdev.rservice.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;

@Getter
@XmlRootElement(name="wn8")
public class PlayerWN8 {
	
	@XmlAttribute
	private double wn8;
	@XmlAttribute
	private String playerId;
	
	public PlayerWN8() {
	}

	public PlayerWN8(double d, String playerId) {
		this.wn8 = d;
		this.playerId = playerId;
	}
}
