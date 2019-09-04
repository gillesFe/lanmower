package com.lanmower.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.lanmower.exceptions.InvalidLawnmowerMovementException;

public class LanmowerMovements {

	private final List<Movement> movements;

	private LanmowerMovements(List<Movement> movements) {
		this.movements = movements;
	}

	public List<Movement> getMovements() {
		return movements;
	}

	public static LanmowerMovements from(String movementsInstructions) {
		List<Movement> movements = buildMovementsAcronyms(movementsInstructions).stream().map(Movement::from).collect(Collectors.toList());
		
		if (containsInvalidMovements(movements)) {
			throw new InvalidLawnmowerMovementException("invalid lanmower movement with instructions : " + movementsInstructions);
		}
		
		return new LanmowerMovements(movements);
	}

	private static List<String> buildMovementsAcronyms(String movementsInstructions) {
		return Arrays.asList(movementsInstructions.split(""));
	}

	private static boolean containsInvalidMovements(List<Movement> movements) {
		return movements.contains(null);
	}
	
}
