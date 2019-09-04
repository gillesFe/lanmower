package com.lanmower.domain;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.lanmower.domain.LanmowerMovements;
import com.lanmower.domain.Movement;
import com.lanmower.exceptions.InvalidLawnmowerMovementException;

public class LanmowerMovementsTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	public void should_build_lanmower_movements_from_instructions() throws Exception {
		List<Movement> movements = LanmowerMovements.from("FLFR").getMovements();
		
		Assertions.assertThat(movements).containsExactlyElementsOf(Arrays.asList(Movement.FORWARD, Movement.LEFT, Movement.FORWARD, Movement.RIGHT));
	}
	
	@Test
	public void should_throw_invalid_lawnmower_movement_exception_when_lawn_position_instructions_are_malformed() throws Exception {
		expectedException.expect(InvalidLawnmowerMovementException.class);
	    expectedException.expectMessage("invalid lanmower movement with instructions : FZFR");
	    
	    LanmowerMovements.from("FZFR");
	}
}
