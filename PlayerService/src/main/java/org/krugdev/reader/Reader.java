package org.krugdev.reader;

import java.util.List;

import org.krugdev.wn8.TankItem;

public interface Reader {

	public List<TankItem> getPlayerTanks(int playerId);

}
