package org.krugdev.io;

import java.util.List;

import org.krugdev.wn8.XML.XMLTankItem;

public interface Reader {

	public List<XMLTankItem> getPlayerTanks(int playerId);

}
