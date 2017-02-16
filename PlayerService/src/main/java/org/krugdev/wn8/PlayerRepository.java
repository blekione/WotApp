package org.krugdev.wn8;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.krugdev.io.Reader;
import org.krugdev.wn8.expected.TankExpectedValues;

public class PlayerRepository {

	private final Reader READER;
	private final int PLAYER_ID;
	private Map<Integer, TankExpectedValues> tanksExpectedVal;
	private List<TankItem> tankItems;
	
	public PlayerRepository(Reader reader, int playerId, Map<Integer, TankExpectedValues> tanksExpectedVal) {
		this.READER = reader;
		this.PLAYER_ID = playerId;
		this.tanksExpectedVal = tanksExpectedVal;
	}
	
	public double calculatePlayersWN8() {
		List<TankItem> tankItems = readPlayersTanks();
		return WN8Formula.calculate(tankItems, tanksExpectedVal);
	}
	
	public double calculatePlayersIndividualTankWN8(int tankId) {
		List<TankItem> tankItems =  readPlayersTanks();
		for (TankItem tankItem: tankItems) {
			if(tankItem.getTankId() == tankId) {
				return WN8Formula.calculate(Arrays.asList(tankItem), tanksExpectedVal);
			}
		}
		throw new IllegalArgumentException("Could not find tank with ID: " + tankId + " for player: " + PLAYER_ID);
	}
	
	private List<TankItem> readPlayersTanks() {
		if (tankItems == null) {
			return tankItems = READER.getPlayerTanks(PLAYER_ID);
		} else {
			return tankItems;
		}
	}
	
}