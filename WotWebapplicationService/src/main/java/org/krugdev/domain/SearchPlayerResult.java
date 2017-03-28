package org.krugdev.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;

@XmlRootElement(name="player")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
public class SearchPlayerResult {
	@XmlAttribute
	private int accountId;
	private String nickname;
	private String platform;
	
	public SearchPlayerResult() {
	}
	
	public String getPlatform() {
		if (platform.equals("XBOX")) {
			return "Xbox";
		} else {
			return "PlayStation";
		}
		
	}
}
