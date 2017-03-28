package org.krugdev.domain;

import lombok.Getter;

@Getter
public class WN8 {
	
	private boolean ranksSet = false;
	private String wn8Color;
	private String wn8Rank; 

	public String getWN8Color(double wn8) {
		if (ranksSet) {
			return wn8Color;
		} else {
			setWN8Ranks(wn8);
			ranksSet = true;
			return wn8Color;
		}
	}
	
	public String getWN8Rank(double wn8) {
		if (ranksSet) {
			return wn8Rank;
		} else {
			setWN8Ranks(wn8);
			ranksSet = true;
			return wn8Rank;
		}
	}
	
	public boolean setWN8Ranks(double wn8) {
		if (wn8 >= 0 && wn8 < 300) {
			wn8Color =  "#930D0D";
			wn8Rank = "very bad";
		} else if (wn8 >= 300 && wn8 < 450) {
			wn8Color = "#CD3333";
			wn8Rank = "bad";
		} else if (wn8 >= 450 && wn8 < 650) {
			wn8Color = "#CC7A00";
			wn8Rank = "below average";
		} else if (wn8 >= 650 && wn8 < 900) {
			wn8Color = "#CCB800";
			wn8Rank = "average";
		} else if (wn8 >= 900 && wn8 < 1200) {
			wn8Color = "#849B24";
			wn8Rank = "above average";
		} else if (wn8 >= 1200 && wn8 < 1600) {
			wn8Color = "#4D7326";
			wn8Rank = "good";
		} else if (wn8 >= 1600 && wn8 < 2000) {
			wn8Color = "#4099BF";
			wn8Rank = "very good";
		} else if (wn8 >= 2000 && wn8 < 2500) {
			wn8Color = "#3972C6";
			wn8Rank = "great";
		} else if (wn8 >= 2500 && wn8 < 2900) {
			wn8Color = "#793DB6";
			wn8Rank = "unicum";
		} else if (wn8 >= 2900) {
			wn8Color = "#401070";
			wn8Rank = "super unicum";
		}
		return true;
	}
}
