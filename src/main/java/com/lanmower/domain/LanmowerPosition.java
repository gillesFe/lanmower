package com.lanmower.domain;

import com.lanmower.exceptions.InvalidLawnmowerPositionException;

public class LanmowerPosition {
	
	private static final String LAWNMOWER_POSITION_PATTERN = "\\d \\d [N|E|S|W]";

	private final int abscissaAxis;
	
	private final int ordinateAxis;
	
	private final Orientation orientation;

	private LanmowerPosition(int abscissaAxis, int ordinateAxis, Orientation orientation) {
		this.abscissaAxis = abscissaAxis;
		this.ordinateAxis = ordinateAxis;
		this.orientation = orientation;
	}

	public int getAbscissaAxis() {
		return abscissaAxis;
	}

	public int getOrdinateAxis() {
		return ordinateAxis;
	}

	public Orientation getOrientation() {
		return orientation;
	}
	
	@Override
	public String toString() {
		return this.abscissaAxis + " " + this.ordinateAxis + " " + orientation.getAcronym();
	}

	public static LanmowerPosition from(String position) {
		
		if (isInvalidLawnmowerPosition(position)) {
			throw new InvalidLawnmowerPositionException("Invalid lawnmower position : " + position);
		}
		
		return new LanmowerPosition(buildAbscissaAxisFrom(position), buildOrdinateAxisFrom(position), buildOrientationFrom(position));
	}

	private static boolean isInvalidLawnmowerPosition(String position) {
		return !position.matches(LAWNMOWER_POSITION_PATTERN);
	}

	private static Orientation buildOrientationFrom(String position) {
		return Orientation.from(position.substring(4, 5));
	}

	private static int buildOrdinateAxisFrom(String position) {
		return Integer.parseInt(position.substring(2, 3));
	}

	private static int buildAbscissaAxisFrom(String position) {
		return Integer.parseInt(position.substring(0, 1));
	}

}
