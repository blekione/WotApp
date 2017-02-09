package org.krugdev.wn8;

public class TankUtils {

	/**
	 * Calculates WN8 rating for specific tank based on the formula taken from http://http://wiki.wnefficiency.net/pages/WN8
	 * @param tankItem
	 * @param tankExpVal
	 * @return
	 */
	public static double calculateWN8(TankItem tankItem, TankExpectedValues tankExpVal) {
		
		
		// calculates the ratio of tankValues to expectedValues
		// tankTotalValue / tankTotalGames / tankExpectedValue
		double rDamage = tankItem.getDamageDealt()/tankItem.getGamesCount()/tankExpVal.getExpDamage();
		double rSpot = ((double)tankItem.getSpottedTanks()/tankItem.getGamesCount())/tankExpVal.getExpSpot();
		double rFrag = ((double)tankItem.getFrags()/tankItem.getGamesCount())/tankExpVal.getExpFrag();
		double rDef = ((double)tankItem.getDefencePoints()/tankItem.getGamesCount())/tankExpVal.getExpDef();
		double rWin = tankItem.getWinRatio()/tankExpVal.getExpWinRate();
		
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
