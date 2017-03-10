package org.krugdev.rservice.domain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;

@Getter
@XmlRootElement(name="wn8tanks")
public class PlayerTankWN8 {

	@XmlAttribute
	int tankId;
	@XmlAttribute
	double tankWN8;
	
	public PlayerTankWN8() {
	}

	public PlayerTankWN8(int tankId, double tankWN8) {
		this.tankId = tankId;
		this.tankWN8 = tankWN8;
	}
}
