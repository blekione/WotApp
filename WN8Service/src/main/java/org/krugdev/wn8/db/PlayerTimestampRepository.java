package org.krugdev.wn8.db;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.krugdev.io.WN8DBService;
import org.krugdev.io.WotAPIServiceReader;
import org.krugdev.wn8.WN8Repository;
import org.krugdev.wn8.XML.TankItem;
import org.krugdev.wn8.expected.DBTankExpectedValuesReader;
import org.krugdev.wn8.expected.TankExpectedValues;
import org.krugdev.wn8.expected.TankExpectedValuesReader;

import lombok.Setter;

public class PlayerTimestampRepository {

	private final Session SESSION;
	@Setter
	WotAPIServiceReader apiReader;
	private final WN8DBService dbReader;

	public PlayerTimestampRepository(WN8DBService dbReader, Session session) {
		this.dbReader = dbReader;
		this.SESSION = session;
	}

	public PlayerTimestamp getUpToDatePlayerTanksItems(Integer playerId, WotAPIServiceReader apiReader) {
		setApiReader(apiReader);
		List<PlayerTimestamp> dbLastTwoPlayerTanksTimestamps = 
				dbReader.findTwoLastPlayerTanksTimestamps(playerId);
		if (lastPlayerTimestampIsYoungerThan30min(dbLastTwoPlayerTanksTimestamps)) {
			PlayerTimestamp newTimestampFromWotAPI = getNewPlayerTimestampFromWotAPI(SESSION, playerId);
			if(timeDifferenceBetweenLastTwoPlayerTimestapsLessThanAWeek(dbLastTwoPlayerTanksTimestamps)) {
				dbReader.replaceLastPlayerTimestamp(playerId, newTimestampFromWotAPI);
			} else {
				dbReader.savePlayerTanks(newTimestampFromWotAPI);
			}
		return newTimestampFromWotAPI;
		}
		return dbLastTwoPlayerTanksTimestamps.get(0);
	}
	
	private boolean timeDifferenceBetweenLastTwoPlayerTimestapsLessThanAWeek(List<PlayerTimestamp> lastTwoPlayerTanksTimestamps) {
		if (lastTwoPlayerTanksTimestamps.size() > 1 &&
				lastTwoPlayerTanksTimestamps.get(0).getTimestamp()			
					.compareTo(lastTwoPlayerTanksTimestamps.get(1).getTimestamp().plusWeeks(1)) < 0) {
			return true;
		}
		return false;
	}

	private  PlayerTimestamp getNewPlayerTimestampFromWotAPI(Session session, int playerId) {
		List<TankItem> playerTanks = apiReader.getPlayerTanks(playerId);
		List<DBTankItem> dbTanksList = new ArrayList<>();
		TankExpectedValuesReader parser = new DBTankExpectedValuesReader();		
		Map<Integer, TankExpectedValues> tanksExpectedValues = parser.getTankEx(session);
		WN8Repository wn8Repository = new WN8Repository(apiReader, playerId, tanksExpectedValues);
		PlayerTimestamp newPlayerTimestamp = new PlayerTimestamp();
		for (TankItem item : playerTanks) {
			double wn8 = wn8Repository.calculateForIndividualTank(item.getTankId());
			DBTankItem bdTankItem = DBTankItem.instanceOf(item, wn8);
			bdTankItem.setPlayer(newPlayerTimestamp);
			dbTanksList.add(bdTankItem);	
		}		
		double wn8 = wn8Repository.calculateForPlayers();
		newPlayerTimestamp.setPlayerDBTanks(dbTanksList);
		newPlayerTimestamp.setPlayerId(playerId);
		newPlayerTimestamp.setTotalWN8(wn8);
		return newPlayerTimestamp;
	}

	private boolean lastPlayerTimestampIsYoungerThan30min(List<PlayerTimestamp> dbLastPlayerTanksTimestamps) {
		if(dbLastPlayerTanksTimestamps.isEmpty()						
				|| dbLastPlayerTanksTimestamps.get(0).getTimestamp().compareTo(LocalDateTime.now().minusMinutes(30)) < 0
				) {
			return true;
		}
		return false;
	}
}
