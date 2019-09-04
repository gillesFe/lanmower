package com.lanmower.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.lanmower.domain.LanmowerPosition;
import com.lanmower.domain.Orientation;
import com.lanmower.exceptions.InvalidLawnmowerPositionException;

public class LanmowerPositionTest {
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	@Test
	public void should_build_correct_lanmower_position() throws Exception {
		LanmowerPosition lanmowerPosition = LanmowerPosition.from("1 2 N");
		
		assertThat(lanmowerPosition.getAbscissaAxis()).isEqualTo(1);
		assertThat(lanmowerPosition.getOrdinateAxis()).isEqualTo(2);
		assertThat(lanmowerPosition.getOrientation()).isEqualTo(Orientation.NORTH);
		assertThat(lanmowerPosition.toString()).isEqualTo("1 2 N");
		
	}
	
	@Test
	public void should_throw_invalid_lawnmower_position_exception_when_lawn_position_instructions_are_malformed() throws Exception {
		expectedEx.expect(InvalidLawnmowerPositionException.class);
	    expectedEx.expectMessage("Invalid lawnmower position : 1 2 Z");
		
		LanmowerPosition.from("1 2 Z");
	}

}
