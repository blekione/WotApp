package org.krugdev.domain.playerProfile;

public class PlayerDamage {

	private int damageDealt;
	private int damageAfterTrack;
	private int damageAfterSpot;
	private int damageReceived;
	private int damageBounced;
	private int damageBlocked;
	
	public PlayerDamage() {
	}

	public PlayerDamage(int damageDealt, int damageAfterTrack, int damageAfterSpot, int damageReceived,
			int damageBounced, int damageBlocked) {
		super();
		this.damageDealt = damageDealt;
		this.damageAfterTrack = damageAfterTrack;
		this.damageAfterSpot = damageAfterSpot;
		this.damageReceived = damageReceived;
		this.damageBounced = damageBounced;
		this.damageBlocked = damageBlocked;
	}

}
