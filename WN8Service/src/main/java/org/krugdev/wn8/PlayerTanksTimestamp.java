package org.krugdev.wn8;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.krugdev.wn8.PlayerTanks;

import lombok.Getter;

@Entity(name="tanks_timestamp")
@Getter
public class PlayerTanksTimestamp implements PlayerTanks {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int recordId;
	private int playerId;
	private double totalWN8;
	private LocalDateTime timestamp;
	@Transient
	private List<TankItem> tankItems;
	
	// default constructor required by persistence
	protected PlayerTanksTimestamp() {}

	public PlayerTanksTimestamp(int playerId, List<TankItem> tankItems, double totalWN8) {
		this.playerId = playerId;
		this.tankItems = tankItems;
		this.totalWN8 = totalWN8;
		this.timestamp = LocalDateTime.now();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + playerId;
		result = prime * result + recordId;
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		
		PlayerTanksTimestamp other = (PlayerTanksTimestamp) obj;
		if (playerId != other.playerId) return false;
		if (recordId != other.recordId) return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		return true;
	}
		
}
