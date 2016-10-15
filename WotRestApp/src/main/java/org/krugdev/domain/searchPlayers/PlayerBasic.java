package org.krugdev.domain.searchPlayers;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.krugdev.auxiliary.Platform;

import com.google.gson.annotations.SerializedName;

@XmlRootElement(name = "player")
@XmlAccessorType(XmlAccessType.NONE)
public class PlayerBasic {
	
	@SerializedName("account_id")
	@XmlAttribute
	private String accountId;
	@XmlElement
	private String nickname;
	@XmlElement
	private Platform platform;
	
	public PlayerBasic() {
	}
	
	public void setPlatform(Platform platform) {
		this.platform = platform;
	}
	public Platform getPlatform() {
		return this.platform;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
}
