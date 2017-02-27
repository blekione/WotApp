package org.krugdev.wn8.db;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.krugdev.wn8.PlayerTanks;
import org.krugdev.wn8.XML.TankItem;

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
	@OneToMany(mappedBy="player", cascade={CascadeType.PERSIST, CascadeType.REMOVE})
	private List<DBTankItem> dbTankItems;
	
	// default constructor required by persistence
	protected PlayerTanksTimestamp() {}

	public PlayerTanksTimestamp(int playerId, List<DBTankItem> tankItems, double totalWN8) {
		this.playerId = playerId;
		this.dbTankItems = tankItems;
		this.totalWN8 = totalWN8;
		this.timestamp = LocalDateTime.now();
	}
	
	public List<TankItem> getTankItems() {
		List<TankItem> tankItems = new ArrayList<>();
		dbTankItems.forEach(v -> {
			tankItems.add(v.convertToTankItem());
		});
		return tankItems;
	}
	
	public void setPlayerTanks(List<TankItem> tankItems) {
		tankItems.forEach(v -> {
			dbTankItems.add(DBTankItem.instanceOf(v));
		});
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
