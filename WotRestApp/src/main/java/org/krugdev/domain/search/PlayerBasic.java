package org.krugdev.domain.search;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.krugdev.domain.Platforms;

import com.google.gson.annotations.SerializedName;

@XmlRootElement(name="player")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlayerBasic {
	
	@SerializedName("account_id")
	private int accountId;
	@SerializedName("nickname")
	private String name;
	private Platforms platform;
	
	public PlayerBasic() {
	}

	public PlayerBasic(int accountId, String name) {
		this.accountId = accountId;
		this.name = name;
	}

	public void setPlatform(Platforms platform) {
		this.platform = platform;
	}
	public Platforms getPlatform() {
		return this.platform;
	}

}
