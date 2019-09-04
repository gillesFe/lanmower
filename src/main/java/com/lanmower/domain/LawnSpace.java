package com.lanmower.domain;

import com.lanmower.exceptions.InvalidLawnSpaceException;

public class LawnSpace {

	private static final String LAWN_SPACE_PATTERN = "\\d \\d";

	private final int abscissaAxisLimit;
	
	private final int  ordinateAxislimit;

	private LawnSpace(int abscissaAxisLimit, int ordinateAxisLimit) {
		this.abscissaAxisLimit = abscissaAxisLimit;
		this.ordinateAxislimit = ordinateAxisLimit;
	}

	public int getAbscissaAxisLimit() {
		return abscissaAxisLimit;
	}

	public int getOrdinateAxisLimit() {
		return ordinateAxislimit;
	}

	public static LawnSpace from(String lawnSpaceInstruction) {
		
		if (isInvalidLawnSpaceFrom(lawnSpaceInstruction)) {
			throw new InvalidLawnSpaceException("Lawn space instructions are invalid : " + lawnSpaceInstruction);
		}
		
		return new LawnSpace(getAbscissaAxisLimit(lawnSpaceInstruction), getOrdinateAxisLimit(lawnSpaceInstruction));
	}

	private static boolean isInvalidLawnSpaceFrom(String lawnSpaceInstruction) {
		return !lawnSpaceInstruction.matches(LAWN_SPACE_PATTERN);
	}

	private static int getAbscissaAxisLimit(String lawnSpaceInstruction) {
		return Integer.parseInt(lawnSpaceInstruction.substring(0, 1));
	}

	private static int getOrdinateAxisLimit(String lawnSpaceInstruction) {
		return Integer.parseInt(lawnSpaceInstruction.substring(2, 3));
	}

}
