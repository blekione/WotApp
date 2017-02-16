package org.krugdev.io;

import java.util.List;

import org.krugdev.wn8.TankItem;

public interface Reader {

	public List<TankItem> getPlayerTanks(int playerId);

}
