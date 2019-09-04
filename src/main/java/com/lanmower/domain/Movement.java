package com.lanmower.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum Movement {
	
	FORWARD("F"), LEFT("L"), RIGHT("R");
	
	private final String acronym;
	
	private static Map<String, Movement> movementValues = new HashMap<>();
	
	static {
		Arrays.asList(Movement.values()).stream()
				.forEach(movement -> movementValues.put(movement.acronym, movement));
	}
	
	Movement(String acronym) {
		this.acronym = acronym;
	}

	public String getAcronym() {
		return acronym;
	}
	
	public static Movement from(String movement) {
		return movementValues.get(movement);
	}

}
