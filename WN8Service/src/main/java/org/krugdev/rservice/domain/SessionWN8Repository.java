package org.krugdev.rservice.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.krugdev.io.Reader;
import org.krugdev.wn8.WN8Repository;
import org.krugdev.wn8.XML.TankItem;
import org.krugdev.wn8.XML.TankItemBuilder;
import org.krugdev.wn8.db.DBTankItem;
import org.krugdev.wn8.expected.TankExpectedValues;

public class SessionWN8Repository {

	private static Map<Integer, TankExpectedValues> tankExpectedValues;
	private static int playerId;

	public SessionWN8Repository(Map<Integer, TankExpectedValues> tankExpectedValues, int playerId) {
		SessionWN8Repository.tankExpectedValues = tankExpectedValues;
		SessionWN8Repository.playerId = playerId;
	}

	public SessionWN8 getSessionWN8(Map<Integer, TankItem> sessionStartTankItems, List<TankItem> latestTankItems) {
		SessionWN8 sessionWN8 = new SessionWN8();
		List<TankItem> playedTanks = getPlayedTanks(sessionStartTankItems, latestTankItems);
		List<DBTankItem> playedTanksWithWN8 = getTanksWN8(playedTanks);
		double sessionWN8value = getSessionWN8Value(playedTanks);
		sessionWN8.setTanksPlayed(playedTanksWithWN8);
		sessionWN8.setWn8Value(sessionWN8value);
		return sessionWN8;
	}
	
	private double getSessionWN8Value(List<TankItem> playedTanks) {
		WN8Repository wn8Repository = getWN8Repository(playedTanks);
		
		return wn8Repository.calculateForPlayers();
	}

	private List<DBTankItem> getTanksWN8(List<TankItem> playedTanks) {
		WN8Repository wn8Repository = getWN8Repository(playedTanks);
		List<DBTankItem> tanksWithWN8 = new ArrayList<>();
		for(TankItem tankItem : playedTanks) {
			tanksWithWN8.add(DBTankItem.instanceOf(
					tankItem, 
					wn8Repository.calculateForIndividualTank(tankItem.getTankId()
							)));
		}
		return tanksWithWN8;
	}

	private static WN8Repository getWN8Repository(List<TankItem> playedTanks) {
		return new WN8Repository(new Reader() {			
			@Override
			public List<TankItem> getPlayerTanks(int playerId) {
				return playedTanks;
			}
		}, playerId, tankExpectedValues);
	}

	private static List<TankItem> getPlayedTanks(Map<Integer, TankItem> sessionStartTankItems, List<TankItem> latestTankItems) {
		List<TankItem> playedTanks = new ArrayList<>();
		latestTankItems.forEach(v -> {
			TankItem startTankItem = sessionStartTankItems.get(v.getTankId());
			if (startTankItem != null && (v.getGamesCount() - startTankItem.getGamesCount()) > 0) {
				TankItem sessionTankItem = new TankItemBuilder(v.getPlayer(), v.getTankId())
						.damageDealt(v.getDamageDealt() - startTankItem.getDamageDealt())
						.defencePoints(v.getDefencePoints() - startTankItem.getDefencePoints())
						.gamesCount(v.getGamesCount() - startTankItem.getGamesCount())
						.spottedTanks(v.getSpottedTanks() - startTankItem.getSpottedTanks())
						.winRatio((v.getWinRatio() + startTankItem.getWinRatio()) / 2)
						.frags(v.getFrags() - startTankItem.getFrags())
						.build();
				playedTanks.add(sessionTankItem);	
			} else if (startTankItem == null) {
				playedTanks.add(v);
			}
		});
		
		return playedTanks;
	}
}
