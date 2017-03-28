package org.krugdev.domain.playerProfile;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;

@Getter
@XmlRootElement(name="playerClan")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlayerClan {
	private long joinedAt;
	private String clanRole;
	private int clanId;
	private long inClanCooldownTill;
}
