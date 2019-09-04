package com.lanmower.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum Orientation {
	
	NORTH("N"), EAST("E"), SOUTH("S"), WEST("W");
	
	private final String acronym;
	
	private static Map<String, Orientation> orientationValues = new HashMap<>();
	
	static {
		Arrays.asList(Orientation.values()).stream()
				.forEach(orientation -> orientationValues.put(orientation.acronym, orientation));
	}
	
	Orientation(String acronym) {
		this.acronym = acronym;
	}

	public String getAcronym() {
		return acronym;
	}
	
	public static Orientation from(String orientation) {
		return orientationValues.get(orientation);
	}

}
