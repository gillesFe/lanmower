package com.lanmower.domain;

import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.lanmower.domain.LawnSpace;
import com.lanmower.exceptions.InvalidLawnSpaceException;

public class LawnSpaceTest {

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	@Test
	public void should_build_correct_lawn_space() throws Exception {
		LawnSpace lawnSpace = LawnSpace.from("5 6");
		
		Assertions.assertThat(lawnSpace.getAbscissaAxisLimit()).isEqualTo(5);
		Assertions.assertThat(lawnSpace.getOrdinateAxisLimit()).isEqualTo(6);
	}
	
	@Test
	public void should_throw_invalid_lawn_space_exception_when_lawn_space_instructions_are_malformed() throws Exception {
		expectedEx.expect(InvalidLawnSpaceException.class);
	    expectedEx.expectMessage("Lawn space instructions are invalid : 56");
		
		LawnSpace.from("56");
	}
}
