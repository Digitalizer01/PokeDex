package com.pokedex.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.pokedex.dto.StatsDTO;
import com.pokedex.exception.ResourceNotFoundException;
import com.pokedex.model.Stats;
import com.pokedex.repository.StatsRepository;
import com.pokedex.service.impl.StatsServiceImpl;

@ExtendWith(MockitoExtension.class)
public class StatsServiceTests {

	@Mock
	private StatsRepository statsRepository;

	@InjectMocks
	private StatsServiceImpl statsService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testAddStats() {
		StatsDTO statsDTO = new StatsDTO(100, 50, 75, 60, 70, 80);
		Stats stats = new Stats(100, 50, 75, 60, 70, 80);

		when(statsRepository.saveAndFlush(any(Stats.class))).thenReturn(stats);

		Stats createdStats = statsService.addStats(statsDTO);

		assertNotNull(createdStats);
		assertEquals(statsDTO.getHp(), createdStats.getHp());
		assertEquals(statsDTO.getAttack(), createdStats.getAttack());
		assertEquals(statsDTO.getDefense(), createdStats.getDefense());
		assertEquals(statsDTO.getSpecialAttack(), createdStats.getSpecialAttack());
		assertEquals(statsDTO.getSpecialDefense(), createdStats.getSpecialDefense());
		assertEquals(statsDTO.getSpeed(), createdStats.getSpeed());
	}

	@Test
	public void testGetStatsById() {
		Stats stats = new Stats(100, 50, 75, 60, 70, 80);

		when(statsRepository.findById(1)).thenReturn(java.util.Optional.of(stats));

		Stats foundStats = statsService.getStatsById(1);

		assertNotNull(foundStats);
		assertEquals(stats.getId(), foundStats.getId());
	}

	@Test
	public void testGetStatsById_NotFound() {
		when(statsRepository.findById(1)).thenReturn(java.util.Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> {
			statsService.getStatsById(1);
		});
	}

	@Test
	public void testUpdateStats() {
		Stats existingStats = new Stats(100, 50, 75, 60, 70, 80);
		StatsDTO newStatsDTO = new StatsDTO(120, 55, 80, 65, 75, 90);

		when(statsRepository.findById(1)).thenReturn(java.util.Optional.of(existingStats));
		when(statsRepository.saveAndFlush(any(Stats.class))).thenReturn(existingStats);

		ResponseEntity<Stats> response = statsService.updateStats(1, newStatsDTO);

		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());

		Stats updatedStats = response.getBody();
		assertNotNull(updatedStats);
		assertEquals(newStatsDTO.getHp(), updatedStats.getHp());
		assertEquals(newStatsDTO.getAttack(), updatedStats.getAttack());
		assertEquals(newStatsDTO.getDefense(), updatedStats.getDefense());
		assertEquals(newStatsDTO.getSpecialAttack(), updatedStats.getSpecialAttack());
		assertEquals(newStatsDTO.getSpecialDefense(), updatedStats.getSpecialDefense());
		assertEquals(newStatsDTO.getSpeed(), updatedStats.getSpeed());
	}

	@Test
	public void testUpdateStats_NotFound() {
		StatsDTO newStatsDTO = new StatsDTO(120, 55, 80, 65, 75, 90);

		when(statsRepository.findById(1)).thenReturn(java.util.Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> {
			statsService.updateStats(1, newStatsDTO);
		});
	}

	@Test
	public void testDeleteStats() {
		Stats stats = new Stats(100, 50, 75, 60, 70, 80);

		when(statsRepository.findById(1)).thenReturn(java.util.Optional.of(stats));
		doNothing().when(statsRepository).deleteById(1);

		ResponseEntity<Stats> response = statsService.deleteStats(1);

		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(stats, response.getBody());
	}

	@Test
	public void testDeleteStats_NotFound() {
		when(statsRepository.findById(1)).thenReturn(java.util.Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> {
			statsService.deleteStats(1);
		});
	}
}
