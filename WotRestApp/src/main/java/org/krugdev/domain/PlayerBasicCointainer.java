package org.krugdev.domain;

import java.util.List;

public class PlayerBasicCointainer {
	private List<PlayerBasic> items;
	
	public PlayerBasicCointainer(List<PlayerBasic> items) {
		super();
		this.items = items;
	}
	
	public List<PlayerBasic> getItems() {
		return items;
	}
	public void setItems(List<PlayerBasic> playersBasic) {
		this.items = playersBasic;
	}
}
