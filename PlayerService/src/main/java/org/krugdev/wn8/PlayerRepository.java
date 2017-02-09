package org.krugdev.wn8;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.krugdev.reader.Reader;

public class PlayerRepository {

	private final Reader reader;
	private final int playerId;
	private Map<Integer, TankExpectedValues> tanksExpectedVal;

	public PlayerRepository(Reader reader, int playerId, Map<Integer, TankExpectedValues> tanksExpectedVal) {
		this.reader = reader;
		this.playerId = playerId;
		this.tanksExpectedVal = tanksExpectedVal;
	}

	public double calculateWN8() {
		List<TankItem> playerTanks = reader.getPlayerTanks(playerId);
		int gameCounter = 0;
		double playerWN8 = 0.0;
		// player WN8 calculated from formula 
		// tank1_WN8 * tank1_games / player_games + ... + tankN_WN8 * tankN_games / player_games
		List<Double> dividentsForWN8 = new ArrayList<>(); // to holds values tankI_WN8 * tankI_games;
		for (TankItem tankItem : playerTanks) {
			double tankWN8 = TankUtils.calculateWN8(tankItem, tanksExpectedVal.get(tankItem.getTankId()));
			int tankGames = tankItem.getGamesCount();
			dividentsForWN8.add(tankWN8*tankGames);
			gameCounter += tankGames;
		}
		
		for (Double divident : dividentsForWN8) {
			playerWN8 += divident / gameCounter;
		}
		
		return playerWN8;
	}	
	
	
}
