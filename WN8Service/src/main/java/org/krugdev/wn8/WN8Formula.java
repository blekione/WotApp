package org.krugdev.wn8;

import java.util.List;
import java.util.Map;

import org.krugdev.wn8.XML.TankItem;
import org.krugdev.wn8.expected.TankExpectedValues;

public class WN8Formula {

public static double calculate(List<TankItem> tankItems, Map<Integer, TankExpectedValues> tanksExpectedVal) {
		
		double playerTamageTotal = 0.0, playerWinBattlesTotal = 0;
		int playerFragsTotal = 0, playerSpottedTotal = 0, playerDefTotal = 0;
		
		double expectedDamageTotal = 0.0, expectedWinBattlesTotal = 0;
		int expectedFragsTotal = 0, expectedSpottedTotal = 0, expectedDefTotal = 0;
		
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
