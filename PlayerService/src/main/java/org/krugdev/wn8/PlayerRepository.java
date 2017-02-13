package org.krugdev.wn8;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.krugdev.reader.Reader;
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
		List<TankItem> tankItems = readPlayerTanks();
		return wn8Formula(tankItems);
	}
	
	public double calculateIndividualTankWN8(int tankId) {
		List<TankItem> tankItems =  readPlayerTanks();
		for (TankItem tankItem: tankItems) {
			if(tankItem.getTankId() == tankId) {
				return wn8Formula(Arrays.asList(tankItem));
			}
		}
		throw new IllegalArgumentException("Could not find tank with ID: " + tankId + " for player: " + PLAYER_ID);
	}
	
	private List<TankItem> readPlayerTanks() {
		if (tankItems == null) {
			return tankItems = READER.getPlayerTanks(PLAYER_ID);
		} else {
			return tankItems;
		}
	}
	
	private double wn8Formula(List<TankItem> tankItems) {
		
		double playerTamageTotal = 0.0;
		int playerFragsTotal = 0;
		int playerSpottedTotal = 0;
		int playerDefTotal = 0;
		int playerWinBattlesTotal = 0;
		
		double expectedDamageTotal = 0.0;
		int expectedFragsTotal = 0;
		int expectedSpottedTotal = 0;
		int expectedDefTotal = 0;
		double expectedWinBattlesTotal = 0;
		
		for (TankItem tankItem : tankItems) {
			int gameTank = tankItem.getGamesCount();
			
			playerTamageTotal += tankItem.getDamageDealt();
			playerFragsTotal += tankItem.getFrags();
			playerSpottedTotal += tankItem.getSpottedTanks();
			playerDefTotal += tankItem.getDefencePoints();
			playerWinBattlesTotal += tankItem.getWinRatio() * gameTank;
			
			TankExpectedValues tankEV = tanksExpectedVal.get(tankItem.getTankId());
			
			expectedDamageTotal += tankEV.getExpDamage() * gameTank;
			expectedFragsTotal += tankEV.getExpFrag() * gameTank;
			expectedSpottedTotal += tankEV.getExpSpot() * gameTank;
			expectedDefTotal += tankEV.getExpDef() * gameTank; 
			expectedWinBattlesTotal += tankEV.getExpWinRate() * gameTank;
		}
		
		double rDamage = playerTamageTotal/expectedDamageTotal;
		double rSpot = ((double)playerSpottedTotal/expectedSpottedTotal);
		double rFrag = ((double)playerFragsTotal/expectedFragsTotal);
		double rDef = ((double) playerDefTotal/expectedDefTotal);
		double rWin = playerWinBattlesTotal/expectedWinBattlesTotal;
		
		double rDamageC = Math.max(0, (rDamage - 0.22) / (1 - 0.22));
		double rSpotC = Math.max(0, Math.min(rDamageC + 0.1, (rSpot - 0.38) / (1 - 0.38)));
		double rFragC = Math.max(0, Math.min(rDamageC + 0.2, (rFrag - 0.12) / (1 - 0.12)));
		double rDefC = Math.max(0, Math.min(rDamageC + 0.1, (rDef - 0.10) / (1 - 0.10)));
		double rWinC = Math.max(0, (rWin - 0.71) / (1 - 0.71));
		
		double WN8 = 980 * rDamageC 
					+ 210 * rDamageC * rFragC
					+ 155 * rFragC * rSpotC
					+ 75 * rDefC * rFragC
					+ 145 * Math.min(1.8, rWinC);
		
		return WN8;
	}
	
}
