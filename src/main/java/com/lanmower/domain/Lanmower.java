package com.lanmower.domain;

public class Lanmower {

	private final LanmowerPosition initialPosition;
	
	private final LanmowerMovements lanmowerMovements;

	public Lanmower(LanmowerPosition initialPosition, LanmowerMovements lanmowerMovements) {
		this.initialPosition = initialPosition;
		this.lanmowerMovements = lanmowerMovements;
	}

	public LanmowerPosition getInitialPosition() {
		return initialPosition;
	}

	public LanmowerMovements getMovementInstructions() {
		return lanmowerMovements;
	}
}
