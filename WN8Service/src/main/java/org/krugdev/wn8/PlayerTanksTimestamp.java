package org.krugdev.wn8;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	
	// default constructor required by persistence
	protected PlayerTanksTimestamp() {}

	public PlayerTanksTimestamp(int playerId) {
		this.playerId = playerId;
		this.timestamp = LocalDateTime.now();
	}
}
