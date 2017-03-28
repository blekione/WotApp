package org.krugdev.rservice.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlRootElement(name="tankDescription")
@XmlAccessorType(XmlAccessType.FIELD)
public class TankDescriptionXML {

	
	private String description;
	@XmlAttribute
	private String short_name;
	private int price_gold;
	@XmlTransient
	private Map<Integer, Integer> next_tanks;
	private String nation;
	private boolean is_premium;
//	@XmlTransient
	private List<String> images;
	private String tag;
	@XmlTransient
	private Map<Integer, Integer> prices_xp;
	private int price_credit;
	private int tier;
	@XmlAttribute
	private int tank_id;
	private String type;
	private String name;
	
	public TankDescriptionXML() {
	}

	public void setImages(Map<String, String> imagesIn) {
		images = new ArrayList<>();
		Iterator<String> iterator = imagesIn.keySet().iterator();
		while(iterator.hasNext()) {
			images.add(imagesIn.get(iterator.next()));
		}
	}
}
