package org.krugdev.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAnyElement;

public class MarshallerListWrapper<T> {
	private List<T> items;
	
	public MarshallerListWrapper() {
		items = new ArrayList<>();
	}
	public MarshallerListWrapper(List<T> items) {
		this.items = items;
	}
	
	@XmlAnyElement(lax=true)
	public List<T> getItems() {
		return items;
	}
}
