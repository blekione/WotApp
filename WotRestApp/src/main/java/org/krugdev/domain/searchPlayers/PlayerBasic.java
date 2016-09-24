package org.krugdev.domain.searchPlayers;


import javax.xml.bind.annotation.XmlElement;

import org.krugdev.auxiliary.Platform;

import com.google.gson.annotations.SerializedName;

public class PlayerBasic {
	
	@SerializedName("account_id")
	@XmlElement
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
