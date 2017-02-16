package org.krugdev.wn8;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
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
	@Column
	private LocalDateTime timestamp;
	@Transient
	private List<TankItem> tankItems;
	
	// default constructor required by persistence
	protected PlayerTanksTimestamp() {}

	public PlayerTanksTimestamp(int playerId, List<TankItem> tankItems) {
		this.playerId = playerId;
		this.tankItems = tankItems;
		this.timestamp = LocalDateTime.now();
	}
	
}
