package com.lanmower.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;
import org.junit.Test;

import com.lanmower.domain.LanmowerMovements;
import com.lanmower.domain.LanmowerPosition;
import com.lanmower.exceptions.InvalidLawnSpaceException;
import com.lanmower.exceptions.InvalidLawnmowerMovementException;
import com.lanmower.exceptions.InvalidLawnmowerPositionException;
import com.lanmower.services.LanmowersManager;

public class LanmowersManagerTest {
	
	@Test
	public void from_north_position_lanmower_should_turn_to_east() throws Exception {
		List<String> instructions = new ArrayList<>();
		instructions.add("5 5");
		instructions.add("0 0 N");
		instructions.add("R");
		
		List<LanmowerPosition> lanmowersFinalPositions = new LanmowersManager(instructions).getLanmowersFinalPositions();
		
		assertThat(lanmowersFinalPositions.size()).isEqualTo(1);
		assertThat(lanmowersFinalPositions.get(0).toString().toString()).isEqualTo("0 0 E");
	}
	
	@Test
	public void from_east_position_lanmower_should_turn_to_south() throws Exception {
		List<String> instructions = new ArrayList<>();
		instructions.add("5 5");
		instructions.add("0 0 E");
		instructions.add("R");
		
		List<LanmowerPosition> lanmowersFinalPositions = new LanmowersManager(instructions).getLanmowersFinalPositions();
		
		assertThat(lanmowersFinalPositions.size()).isEqualTo(1);
		assertThat(lanmowersFinalPositions.get(0).toString()).isEqualTo("0 0 S");
	}
	
	@Test
	public void from_south_position_lanmower_should_turn_to_west() throws Exception {
		List<String> instructions = new ArrayList<>();
		instructions.add("5 5");
		instructions.add("0 0 S");
		instructions.add("R");
		
		List<LanmowerPosition> lanmowersFinalPositions = new LanmowersManager(instructions).getLanmowersFinalPositions();
		
		assertThat(lanmowersFinalPositions.size()).isEqualTo(1);
		assertThat(lanmowersFinalPositions.get(0).toString()).isEqualTo("0 0 W");
	}
	
	@Test
	public void from_west_position_lanmower_should_turn_to_north() throws Exception {
		List<String> instructions = new ArrayList<>();
		instructions.add("5 5");
		instructions.add("0 0 W");
		instructions.add("R");
		
		List<LanmowerPosition> lanmowersFinalPositions = new LanmowersManager(instructions).getLanmowersFinalPositions();
		
		assertThat(lanmowersFinalPositions.size()).isEqualTo(1);
		assertThat(lanmowersFinalPositions.get(0).toString()).isEqualTo("0 0 N");
	}
	
	@Test
	public void from_north_position_lanmower_should_turn_to_west() throws Exception {
		List<String> instructions = new ArrayList<>();
		instructions.add("5 5");
		instructions.add("0 0 N");
		instructions.add("L");
		
		List<LanmowerPosition> lanmowersFinalPositions = new LanmowersManager(instructions).getLanmowersFinalPositions();
		
		assertThat(lanmowersFinalPositions.size()).isEqualTo(1);
		assertThat(lanmowersFinalPositions.get(0).toString()).isEqualTo("0 0 W");
	}
	
	@Test
	public void from_west_position_lanmower_should_turn_to_south() throws Exception {
		List<String> instructions = new ArrayList<>();
		instructions.add("5 5");
		instructions.add("0 0 W");
		instructions.add("L");
		
		List<LanmowerPosition> lanmowersFinalPositions = new LanmowersManager(instructions).getLanmowersFinalPositions();
		
		assertThat(lanmowersFinalPositions.size()).isEqualTo(1);
		assertThat(lanmowersFinalPositions.get(0).toString()).isEqualTo("0 0 S");
	}
	
	@Test
	public void from_south_position_lanmower_should_turn_to_east() throws Exception {
		List<String> instructions = new ArrayList<>();
		instructions.add("5 5");
		instructions.add("0 0 S");
		instructions.add("L");
		
		List<LanmowerPosition> lanmowersFinalPositions = new LanmowersManager(instructions).getLanmowersFinalPositions();
		
		assertThat(lanmowersFinalPositions.size()).isEqualTo(1);
		assertThat(lanmowersFinalPositions.get(0).toString()).isEqualTo("0 0 E");
	}
	
	@Test
	public void from_east_position_lanmower_should_turn_to_north() throws Exception {
		List<String> instructions = new ArrayList<>();
		instructions.add("5 5");
		instructions.add("0 0 E");
		instructions.add("L");
		
		List<LanmowerPosition> lanmowersFinalPositions = new LanmowersManager(instructions).getLanmowersFinalPositions();
		
		assertThat(lanmowersFinalPositions.size()).isEqualTo(1);
		assertThat(lanmowersFinalPositions.get(0).toString()).isEqualTo("0 0 N");
	}


	@Test
	public void lanmower_should_forward_one_step_to_the_north() throws Exception {
		List<String> instructions = new ArrayList<>();
		instructions.add("5 5");
		instructions.add("0 0 N");
		instructions.add("F");
		
		List<LanmowerPosition> lanmowersFinalPositions = new LanmowersManager(instructions).getLanmowersFinalPositions();
		
		assertThat(lanmowersFinalPositions.size()).isEqualTo(1);
		assertThat(lanmowersFinalPositions.get(0).toString()).isEqualTo("0 1 N");
	}
	
	@Test
	public void lanmower_should_forward_one_step_to_the_north_from_other_place() throws Exception {
		List<String> instructions = new ArrayList<>();
		instructions.add("5 5");
		instructions.add("2 3 N");
		instructions.add("F");
		
		List<LanmowerPosition> lanmowersFinalPositions = new LanmowersManager(instructions).getLanmowersFinalPositions();
		
		assertThat(lanmowersFinalPositions.size()).isEqualTo(1);
		assertThat(lanmowersFinalPositions.get(0).toString()).isEqualTo("2 4 N");
	}
	
	@Test
	public void lanmower_should_forward_one_step_to_the_east() throws Exception {
		List<String> instructions = new ArrayList<>();
		instructions.add("5 5");
		instructions.add("0 0 E");
		instructions.add("F");
		
		List<LanmowerPosition> lanmowersFinalPositions = new LanmowersManager(instructions).getLanmowersFinalPositions();
		
		assertThat(lanmowersFinalPositions.size()).isEqualTo(1);
		assertThat(lanmowersFinalPositions.get(0).toString()).isEqualTo("1 0 E");
	}
	
	@Test
	public void lanmower_should_forward_one_step_to_the_east_from_other_place() throws Exception {
		List<String> instructions = new ArrayList<>();
		instructions.add("5 5");
		instructions.add("2 3 E");
		instructions.add("F");
		
		List<LanmowerPosition> lanmowersFinalPositions = new LanmowersManager(instructions).getLanmowersFinalPositions();
		
		assertThat(lanmowersFinalPositions.size()).isEqualTo(1);
		assertThat(lanmowersFinalPositions.get(0).toString()).isEqualTo("3 3 E");
	}
	
	@Test
	public void lanmower_should_forward_one_step_to_the_south() throws Exception {
		List<String> instructions = new ArrayList<>();
		instructions.add("5 5");
		instructions.add("0 1 S");
		instructions.add("F");
		
		List<LanmowerPosition> lanmowersFinalPositions = new LanmowersManager(instructions).getLanmowersFinalPositions();
		
		assertThat(lanmowersFinalPositions.size()).isEqualTo(1);
		assertThat(lanmowersFinalPositions.get(0).toString()).isEqualTo("0 0 S");
	}
	
	@Test
	public void lanmower_should_forward_one_step_to_the_south_from_other_place() throws Exception {
		List<String> instructions = new ArrayList<>();
		instructions.add("5 5");
		instructions.add("2 3 S");
		instructions.add("F");
		
		List<LanmowerPosition> lanmowersFinalPositions = new LanmowersManager(instructions).getLanmowersFinalPositions();
		
		assertThat(lanmowersFinalPositions.size()).isEqualTo(1);
		assertThat(lanmowersFinalPositions.get(0).toString()).isEqualTo("2 2 S");
	}
	
	@Test
	public void lanmower_should_forward_one_step_to_the_west() throws Exception {
		List<String> instructions = new ArrayList<>();
		instructions.add("5 5");
		instructions.add("1 0 W");
		instructions.add("F");
		
		List<LanmowerPosition> lanmowersFinalPositions = new LanmowersManager(instructions).getLanmowersFinalPositions();
		
		assertThat(lanmowersFinalPositions.size()).isEqualTo(1);
		assertThat(lanmowersFinalPositions.get(0).toString()).isEqualTo("0 0 W");
	}
	
	@Test
	public void lanmower_should_forward_one_step_to_the_west_from_other_place() throws Exception {
		List<String> instructions = new ArrayList<>();
		instructions.add("5 5");
		instructions.add("2 3 W");
		instructions.add("F");
		
		List<LanmowerPosition> lanmowersFinalPositions = new LanmowersManager(instructions).getLanmowersFinalPositions();
		
		assertThat(lanmowersFinalPositions.size()).isEqualTo(1);
		assertThat(lanmowersFinalPositions.get(0).toString()).isEqualTo("1 3 W");
	}
	
	@Test
	public void lanmower_should_forward_two_steps_to_the_north() throws Exception {
		List<String> instructions = new ArrayList<>();
		instructions.add("5 5");
		instructions.add("0 0 N");
		instructions.add("FF");
		
		List<LanmowerPosition> lanmowersFinalPositions = new LanmowersManager(instructions).getLanmowersFinalPositions();
		
		assertThat(lanmowersFinalPositions.size()).isEqualTo(1);
		assertThat(lanmowersFinalPositions.get(0).toString()).isEqualTo("0 2 N");
	}
	
	@Test
	public void lanmower_should_keep_it_position_when_moving_outside_north_limit() throws Exception {
		List<String> instructions = new ArrayList<>();
		instructions.add("2 2");
		instructions.add("0 0 N");
		instructions.add("FFF");
		
		List<LanmowerPosition> lanmowersFinalPositions = new LanmowersManager(instructions).getLanmowersFinalPositions();
		
		assertThat(lanmowersFinalPositions.size()).isEqualTo(1);
		assertThat(lanmowersFinalPositions.get(0).toString()).isEqualTo("0 2 N");
	}
	
	@Test
	public void lanmower_should_keep_it_position_when_moving_outside_south_limit() throws Exception {
		List<String> instructions = new ArrayList<>();
		instructions.add("2 2");
		instructions.add("0 0 S");
		instructions.add("FFL");
		
		List<LanmowerPosition> lanmowersFinalPositions = new LanmowersManager(instructions).getLanmowersFinalPositions();
		
		assertThat(lanmowersFinalPositions.size()).isEqualTo(1);
		assertThat(lanmowersFinalPositions.get(0).toString()).isEqualTo("0 0 E");
	}
	
	
	@Test
	public void lanmower_should_forward_multiples_steps() throws Exception {
		List<String> instructions = new ArrayList<>();
		instructions.add("5 5");
		instructions.add("1 2 N");
		instructions.add("LFLFLFLFF");
		
		List<LanmowerPosition> lanmowersFinalPositions = new LanmowersManager(instructions).getLanmowersFinalPositions();
		
		assertThat(lanmowersFinalPositions.size()).isEqualTo(1);
		assertThat(lanmowersFinalPositions.get(0).toString()).isEqualTo("1 3 N");
	}
	
	@Test
	public void lanmower_should_forward_multiples_step_other_cases() throws Exception {
		List<String> instructions = new ArrayList<>();
		instructions.add("5 5");
		instructions.add("3 3 E");
		instructions.add("FFRFFRFRRF");
		
		List<LanmowerPosition> lanmowersFinalPositions = new LanmowersManager(instructions).getLanmowersFinalPositions();
		
		assertThat(lanmowersFinalPositions.size()).isEqualTo(1);
		assertThat(lanmowersFinalPositions.get(0).toString()).isEqualTo("5 1 E");
	}
	
	@Test
	public void should_deploy_sequentially_multiples_lanmowers() throws Exception {
		List<String> instructions = new ArrayList<>();
		instructions.add("5 5");
		instructions.add("0 0 N");
		instructions.add("F");
		instructions.add("2 3 N");
		instructions.add("F");
		
		List<LanmowerPosition> lanmowersFinalPositions = new LanmowersManager(instructions).getLanmowersFinalPositions();
		
		assertThat(lanmowersFinalPositions.size()).isEqualTo(2);
		assertThat(lanmowersFinalPositions.get(0).toString()).isEqualTo("0 1 N");
		assertThat(lanmowersFinalPositions.get(1).toString()).isEqualTo("2 4 N");
	}
	
	@Test(expected = InvalidLawnSpaceException.class)
	public void should_throw_invalid_lawn_space_exception_when_lawn_space_instructions_are_malformed() throws Exception {
		List<String> instructions = new ArrayList<>();
		instructions.add("22");
		instructions.add("0 0 S");
		instructions.add("FFL");
		
		new LanmowersManager(instructions).getLanmowersFinalPositions();
	}
	
	@Test(expected = InvalidLawnmowerPositionException.class)
	public void should_throw_invalid_lawnmower_position_exception_when_lawn_position_instructions_are_malformed() throws Exception {
		LanmowerPosition.from("11");
	}
	
	@Test(expected = InvalidLawnmowerMovementException.class)
	public void should_throw_invalid_lawnmower_movement_exception_when_lawn_position_instructions_are_malformed() throws Exception {
	    LanmowerMovements.from("FZFR");
	}
	
	@Test
	public void should_deploy_lanmowers_from_input_file() throws Exception {
		
		String lanmowersInstructionsFile = "src/test/resources/lanmowers_instructions.txt";
		List<LanmowerPosition> lanmowersFinalPositions = new LanmowersManager(readInstructionsFrom(lanmowersInstructionsFile)).getLanmowersFinalPositions();
		
		assertThat(lanmowersFinalPositions.size()).isEqualTo(2);
		assertThat(lanmowersFinalPositions.get(0).toString()).isEqualTo("1 3 N");
		assertThat(lanmowersFinalPositions.get(1).toString()).isEqualTo("5 1 E");
	}
	
	private List<String> readInstructionsFrom(String lanmowersInstructions) throws IOException {
		return Files.lines(Paths.get(lanmowersInstructions)).collect(Collectors.toList());
	}
	
}
