package org.krugdev.domain.searchPlayers;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.krugdev.auxiliary.Platform;

import com.google.gson.annotations.SerializedName;

@XmlRootElement(name="players")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlayerBasic {
	
	@SerializedName("account_id")
	private int accountId;
	@XmlElement
	private String nickname;
	private Platform platform;
	
	public void setPlatform(Platform platform) {
		this.platform = platform;
	}
	public Platform getPlatform() {
		return this.platform;
	}
}
