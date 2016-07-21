package org.krugdev.domain;

import java.util.List;

public class PlayerBasicCointainer {
	private List<PlayerBasicStatistics> items;
	
	public PlayerBasicCointainer(List<PlayerBasicStatistics> items) {
		super();
		this.items = items;
	}
	
	public List<PlayerBasicStatistics> getItems() {
		return items;
	}
	public void setItems(List<PlayerBasicStatistics> playersBasic) {
		this.items = playersBasic;
	}
}
