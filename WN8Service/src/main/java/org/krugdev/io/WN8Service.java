package org.krugdev.io;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.krugdev.wn8.PlayerTanks;
import org.krugdev.wn8.PlayerTanksTimestamp;
import org.krugdev.wn8.TankItem;

public class WN8Service implements Reader, Writer {
	
	private Session session;

	public WN8Service(Session session) {
		this.session = session;
	}

	@Override
	public List<TankItem> getPlayerTanks(int playerId) {
		return findLatestPlayerTanks(playerId).getTankItems();
	}
	
	@Override
	public void savePlayerTanks(PlayerTanks player) {
		Transaction tx = session.beginTransaction();
		session.save(player);
		tx.commit();
		
	}
	
	public PlayerTanksTimestamp findLatestPlayerTanks(int playerId) {
		// TODO try to find out if query might be optimised
		Query query = session.createQuery("select p from tanks_timestamp p where p.timestamp ="
				+ "(select max(pp.timestamp) from tanks_timestamp pp where pp.playerId=:playerId)");
		query.setParameter("playerId", playerId);
		PlayerTanksTimestamp playerTanksTimestamp = (PlayerTanksTimestamp) query.uniqueResult();
		if (playerTanksTimestamp == null) {
			throw new NoResultException("there is no records for playerId = " + playerId + " in database");
		}
		return playerTanksTimestamp;
	}

	public void removeLatestPlayerTanks(int playerId) {
		Transaction tx = session.beginTransaction();
		PlayerTanksTimestamp latestPlayerTanks = findLatestPlayerTanks(playerId);
		session.delete(latestPlayerTanks);
		tx.commit();
	}
}
