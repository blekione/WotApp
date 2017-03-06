package org.krugdev.io;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.krugdev.wn8.PlayerTanks;
import org.krugdev.wn8.XML.TankItem;
import org.krugdev.wn8.db.PlayerTimestamp;

public class WN8DBService implements Reader, Writer {
	
	private Session session;

	public WN8DBService(Session session) {
		this.session = session;
	}

	@Override
	public List<TankItem> getPlayerTanks(int playerId) {
		return findLatestPlayerTanksTimestamp(playerId).get().getTankItems();
	}
	
	@Override
	public void savePlayerTanks(PlayerTanks player) {
		Transaction tx = getSessionTransaction();
		session.save(player);
		tx.commit();
	}

	public Optional<PlayerTimestamp> findLatestPlayerTanksTimestamp(int playerId) {
		// TODO try to find out if query might be optimised
		Query query = session.createQuery("select p from tanks_timestamp p where p.timestamp ="
				+ "(select max(pp.timestamp) from tanks_timestamp pp where pp.playerId=:playerId)");
		query.setParameter("playerId", playerId);
		PlayerTimestamp playerTanksTimestamp = (PlayerTimestamp) query.uniqueResult();
		if (playerTanksTimestamp == null) {
			return Optional.empty();
		}
		return Optional.of(playerTanksTimestamp);
	}

	public void removeLatestPlayerTimestamp(int playerId) {
		Transaction tx = getSessionTransaction();
		PlayerTimestamp latestPlayerTanks = findLatestPlayerTanksTimestamp(playerId).get();
		session.delete(latestPlayerTanks);
		tx.commit();
	}

	public List<PlayerTimestamp> findPlayerTanksTimestamps(int playerId) {
		Query query = session.createQuery("select p from tanks_timestamp p where p.playerId=:playerId");
		query.setParameter("playerId", playerId);
		@SuppressWarnings("unchecked")
		List<PlayerTimestamp> playerTanksList = query.list();
		if (playerTanksList == null || playerTanksList.isEmpty()) {
			return Collections.emptyList();
		} else {
			return playerTanksList;
		}
	}
	
	public List<PlayerTimestamp> findTwoLastPlayerTanksTimestamps(int playerId) {
		Query query = session.createQuery("select p from tanks_timestamp p where p.playerId =:playerId "
				+ "order by p.timestamp desc");
		query.setParameter("playerId", playerId);
		@SuppressWarnings("unchecked")
		List<PlayerTimestamp> queryResult = query.setMaxResults(2).list();
		return queryResult;
	}
	
	private Transaction getSessionTransaction() {
		if (session.getTransaction().getStatus() == TransactionStatus.ACTIVE) {
			return session.getTransaction();	
		}
		return session.beginTransaction();
	}

	public void replaceLastPlayerTimestamp(int playerId,
			PlayerTimestamp newTimestampFromWotAPI) {
		removeLatestPlayerTimestamp(playerId);
		savePlayerTanks(newTimestampFromWotAPI);
	}
}
