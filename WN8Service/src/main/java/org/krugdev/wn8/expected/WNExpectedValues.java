package org.krugdev.wn8.expected;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;

@XmlRootElement(name= "WNExpectedValues")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
public class WNExpectedValues {
	
	@XmlElement(name="WN8")
	WN8 wn8;
	
	public WNExpectedValues() {
	}
}
