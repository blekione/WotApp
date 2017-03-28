package org.krugdev.rservice.domain;

import java.util.ArrayList;
import java.util.List;

public class TankDescriptionReposiotory {

	public static List<TankDescriptionXML> convertToTankDescriptionXML(List<TankDescription> tankDescriptions) {
		List<TankDescriptionXML> tankDescriptionsXML = new ArrayList<>();
		tankDescriptions.forEach(v -> {
			TankDescriptionXML tankDescriptionXML = new TankDescriptionXML();
			tankDescriptionXML.setDescription(v.getDescription());
			tankDescriptionXML.setShort_name(v.getShort_name());
			tankDescriptionXML.setPrice_gold(v.getPrice_gold());
			tankDescriptionXML.setNext_tanks(v.getNext_tanks());
			tankDescriptionXML.setNation(v.getNation());
			tankDescriptionXML.set_premium(v.is_premium());
			tankDescriptionXML.setImages(v.getImages());
			tankDescriptionXML.setTag(v.getTag());
			tankDescriptionXML.setPrices_xp(v.getPrices_xp());
			tankDescriptionXML.setPrice_credit(v.getPrice_credit());
			tankDescriptionXML.setTier(v.getTier());
			tankDescriptionXML.setTank_id(v.getTank_id());
			tankDescriptionXML.setType(v.getType());
			tankDescriptionXML.setName(v.getName());
			tankDescriptionsXML.add(tankDescriptionXML);
		});
		return tankDescriptionsXML;
	}
}
