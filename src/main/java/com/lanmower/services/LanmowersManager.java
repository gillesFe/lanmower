package com.lanmower.services;

import java.util.ArrayList;
import java.util.List;

import com.lanmower.domain.Lanmower;
import com.lanmower.domain.LanmowerMovements;
import com.lanmower.domain.LanmowerPosition;
import com.lanmower.domain.LawnSpace;
import com.lanmower.domain.Movement;
import com.lanmower.domain.Orientation;

public class LanmowersManager {
	
	private List<LanmowerPosition> lanmowersFinalPositions = new ArrayList<>();
	
	private final LawnSpace lawnSpace;

	public LanmowersManager(List<String> instructions) {
		
		this.lawnSpace = buildLawnSpaceFrom(instructions);
		
		buildLanmowersFrom(instructions).stream().forEach(lanmower -> 
			lanmowersFinalPositions.add(getLastLanmowerPositionFrom(getMovementsOf(lanmower)))
		);
	}
	
	public List<LanmowerPosition> getLanmowersFinalPositions() {
		return lanmowersFinalPositions;
	}

	private List<LanmowerPosition> getMovementsOf(Lanmower lanmower) {
		List<LanmowerPosition> lanmowerPositions = new ArrayList<>();
		lanmowerPositions.add(lanmower.getInitialPosition());
		
		lanmower.getMovementInstructions().getMovements().stream().forEach(currentInstruction -> {
			LanmowerPosition lastLanmowerPosition = getLastLanmowerPositionFrom(lanmowerPositions);
			
			String nextOrientation = buildNextOrientationFromLastPosition(currentInstruction, lastLanmowerPosition);
			int nextOrdinateAxis = buildNextOrdinateAxisFromLastPosition(currentInstruction, lastLanmowerPosition);
			int nextAbscissaAxis = buildNextAbscissaAxisFromLastPosition(currentInstruction, lastLanmowerPosition);
			
			lanmowerPositions.add(LanmowerPosition.from(buildNextPosition(nextOrientation, nextOrdinateAxis, nextAbscissaAxis)));
		});
		
		return lanmowerPositions;
	}

	private LawnSpace buildLawnSpaceFrom(List<String> instructions) {
		return LawnSpace.from(instructions.get(0));
	}

	private List<Lanmower> buildLanmowersFrom(List<String> instructions) {
		List<Lanmower> lanmowers = new ArrayList<>();
		for (int i = 1; i < instructions.size(); i+=2) {
			Lanmower currentLanmower = new Lanmower(LanmowerPosition.from(instructions.get(i)), LanmowerMovements.from(instructions.get(i+1)));
			lanmowers.add(currentLanmower);
		}
		return lanmowers;
	}

	private int buildNextAbscissaAxisFromLastPosition(Movement movement,
			LanmowerPosition lastLanmowerPosition) {
		return getNextAbscissaAxis(lastLanmowerPosition.getOrientation().getAcronym(),
				lastLanmowerPosition.getAbscissaAxis(), movement.getAcronym());
	}

	private int buildNextOrdinateAxisFromLastPosition(Movement movement,
			LanmowerPosition lastLanmowerPosition) {
		return getNextOrdinateAxis(lastLanmowerPosition.getOrientation().getAcronym(),
				lastLanmowerPosition.getOrdinateAxis(), movement.getAcronym());
	}

	private String buildNextOrientationFromLastPosition(Movement movement,
			LanmowerPosition lastLanmowerPosition) {
		return getNextOrientation(lastLanmowerPosition.getOrientation().getAcronym(),
				movement.getAcronym());
	}

	private LanmowerPosition getLastLanmowerPositionFrom(List<LanmowerPosition> lanmowerPositions) {
		return lanmowerPositions.get(lanmowerPositions.size() -1);
	}

	private String buildNextPosition(String nextOrientation, int nextOrdinateAxis, int nextAbscissaAxis) {
		return nextAbscissaAxis + " " + nextOrdinateAxis + " " + nextOrientation;
	}

	private int getNextAbscissaAxis(String direction, int abscissaAxis, String movementInstruction) {
		int nextAbscissaAxis = abscissaAxis;
		if (lanmowerGoToWest(movementInstruction, direction)) {
			return --nextAbscissaAxis;
		} 
		if (lanmowerGoToEast(movementInstruction, direction)) {
			return ++nextAbscissaAxis;
		}
		return nextAbscissaAxis;
	}

	private int getNextOrdinateAxis(String direction, int ordinateAxis, String movementInstruction) {
		int nextOrdinateAxis = ordinateAxis;
		if (lanmowerGoToNorth(movementInstruction, direction) && notExceedLawnSpaceUpperOf(ordinateAxis)) {
			return ++nextOrdinateAxis;
		}  
		if (lanmowerGoToSouth(movementInstruction, direction) && notExceedLawnSpaceBottomOf(ordinateAxis)) {
			return --nextOrdinateAxis;
		}
		return nextOrdinateAxis;
	}

	private boolean notExceedLawnSpaceUpperOf(int ordinateAxis) {
		return ordinateAxis + 1 <= lawnSpace.getOrdinateAxisLimit();
	}
	
	private boolean notExceedLawnSpaceBottomOf(int ordinateAxis) {
		return ordinateAxis - 1 >= 0;
	}

	private String getNextOrientation(String direction, String currentInstruction) {
		if (turnFromEastToNorth(direction, currentInstruction) || turnFromWestToNorth(direction, currentInstruction)) {
			return Orientation.NORTH.getAcronym();
		}
		if (turnFromNorthToEast(direction, currentInstruction) || turnFromSouthToEast(direction, currentInstruction)) {
			return Orientation.EAST.getAcronym();
		} 
		if (turnFromEastToSouth(direction, currentInstruction) || turnFromWestToSouth(direction, currentInstruction)) {
			return Orientation.SOUTH.getAcronym();
		} 
		if (turnFromSouthToWest(direction, currentInstruction) || turnFromNorthToWest(direction, currentInstruction)) {
			return Orientation.WEST.getAcronym();
		} 
		return direction;
	}

	private boolean turnFromEastToNorth(String direction, String currentInstruction) {
		return isTurningLeft(currentInstruction) && isLanmowerOrientedToEast(direction);
	}

	private boolean turnFromSouthToEast(String direction, String currentInstruction) {
		return isTurningLeft(currentInstruction) && isLanmowerOrientedToSouth(direction);
	}

	private boolean turnFromWestToSouth(String direction, String currentInstruction) {
		return isTurningLeft(currentInstruction) && isLanmowerOrientedToWest(direction);
	}

	private boolean turnFromNorthToWest(String direction, String currentInstruction) {
		return isTurningLeft(currentInstruction) && isLanmowerOrientedToNorth(direction);
	}

	private boolean turnFromWestToNorth(String direction, String currentInstruction) {
		return isTurningRight(currentInstruction) && isLanmowerOrientedToWest(direction);
	}

	private boolean turnFromSouthToWest(String direction, String currentInstruction) {
		return isTurningRight(currentInstruction) && isLanmowerOrientedToSouth(direction);
	}


	private boolean turnFromNorthToEast(String direction, String currentInstruction) {
		return isTurningRight(currentInstruction) && isLanmowerOrientedToNorth(direction);
	}

	
	private boolean turnFromEastToSouth(String direction, String currentInstruction) {
		return isTurningRight(currentInstruction) && isLanmowerOrientedToEast(direction);
	}
	
	private boolean isLanmowerOrientedToWest(String direction) {
		return (Orientation.WEST.getAcronym()).equals(direction);
	}
	
	private boolean isLanmowerOrientedToSouth(String direction) {
		return (Orientation.SOUTH.getAcronym()).equals(direction);
	}
	
	private boolean isLanmowerOrientedToNorth(String direction) {
		return (Orientation.NORTH.getAcronym()).equals(direction);
	}

	private boolean isLanmowerOrientedToEast(String direction) {
		return (Orientation.EAST.getAcronym()).equals(direction);
	}
	
	private boolean isTurningLeft(String movementInstruction) {
		return Movement.LEFT.getAcronym().equals(movementInstruction);
	}
	
	private boolean isTurningRight(String movementInstruction) {
		return Movement.RIGHT.getAcronym().equals(movementInstruction);
	}

	private boolean lanmowerGoToSouth(String movementInstruction, String direction) {
		return Movement.FORWARD.getAcronym().equals(movementInstruction) && Orientation.SOUTH.getAcronym().equals(direction);
	}
	
	private boolean lanmowerGoToWest(String movementInstruction, String direction) {
		return Movement.FORWARD.getAcronym().equals(movementInstruction) && Orientation.WEST.getAcronym().equals(direction);
	}

	private boolean lanmowerGoToEast(String movementInstruction, String direction) {
		return Movement.FORWARD.getAcronym().equals(movementInstruction) && Orientation.EAST.getAcronym().equals(direction);
	}

	private boolean lanmowerGoToNorth(String movementInstruction, String direction) {
		return Movement.FORWARD.getAcronym().equals(movementInstruction) && Orientation.NORTH.getAcronym().equals(direction);
	}

}
