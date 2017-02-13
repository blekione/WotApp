package org.krugdev.wn8.expected;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Getter;

@XmlAccessorType(XmlAccessType.FIELD)
@Getter
public class WN8 {

	
	@XmlElement(name="tank")
	List<TankExpectedValues> tankExpectedvalues;
	
	public WN8() {
	}
}
