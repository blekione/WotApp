package org.krugdev.wn8.expected;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.Getter;

@Getter
@Entity
public class TankDescription {

	@Lob
	String description;
	String short_name;
	int price_gold;
	@ElementCollection
	Map<Integer, Integer> next_tanks;
	String nation;
	boolean is_premium;
	@ElementCollection
	Map<String, String> images;
	String tag;
	@ElementCollection
	Map<Integer, Integer> prices_xp;
	int price_credit;
	int tier;
	@Id
	int tank_id;
	String type;
	String name;
	
	public TankDescription() {
	}

}
