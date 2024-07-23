package com.pokedex.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.pokedex.dto.GenerationDTO;
import com.pokedex.exception.ResourceNotFoundException;
import com.pokedex.model.Generation;
import com.pokedex.repository.GenerationRepository;
import com.pokedex.service.impl.GenerationServiceImpl;

@ExtendWith(MockitoExtension.class)
public class GenerationServiceTests {

	@Mock
	private GenerationRepository generationRepository;

	@InjectMocks
	private GenerationServiceImpl generationService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testAddGeneration() {
		GenerationDTO generationDTO = new GenerationDTO(1, "Kanto", 1996);
		Generation generation = new Generation(1, "Kanto", 1996);

		when(generationRepository.saveAndFlush(any(Generation.class))).thenReturn(generation);

		Generation createdGeneration = generationService.addGeneration(generationDTO);

		assertNotNull(createdGeneration);
		assertEquals(generationDTO.getNumber(), createdGeneration.getNumber());
		assertEquals(generationDTO.getRegion(), createdGeneration.getRegion());
		assertEquals(generationDTO.getYear(), createdGeneration.getYear());
	}

	@Test
	public void testGetGenerationById() {
		Generation generation = new Generation(1, "Kanto", 1996);

		when(generationRepository.findById(1)).thenReturn(Optional.of(generation));

		Generation foundGeneration = generationService.getGenerationById(1);

		assertNotNull(foundGeneration);
		assertEquals(generation.getId(), foundGeneration.getId());
	}

	@Test
	public void testGetGenerationById_NotFound() {
		when(generationRepository.findById(1)).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> {
			generationService.getGenerationById(1);
		});
	}

	@Test
	public void testGetAllGenerations() {
		List<Generation> generations = Arrays.asList(new Generation(1, "Kanto", 1996),
				new Generation(2, "Johto", 1999));

		when(generationRepository.findAll()).thenReturn(generations);

		List<Generation> allGenerations = generationService.getAllGenerations();

		assertNotNull(allGenerations);
		assertEquals(2, allGenerations.size());
	}

	@Test
	public void testGetAllGenerationRegions() {
		List<String> regions = Arrays.asList("Kanto", "Johto");

		when(generationRepository.findDistinctRegion()).thenReturn(regions);

		List<String> allRegions = generationService.getAllGenerationRegions();

		assertNotNull(allRegions);
		assertEquals(2, allRegions.size());
	}

	@Test
	public void testGetGenerationsByYear() {
		Generation generation = new Generation(1, "Kanto", 1996);

		when(generationRepository.findGenerationByYear(1996)).thenReturn(generation);

		Generation foundGeneration = generationService.getGenerationByYear(1996);

		assertNotNull(foundGeneration);
		assertEquals(1996, foundGeneration.getYear());
	}

	@Test
	public void testUpdateGeneration() {
		Generation existingGeneration = new Generation(1, "Kanto", 1996);
		GenerationDTO newGenerationDTO = new GenerationDTO(2, "Johto", 1999);

		when(generationRepository.findById(1)).thenReturn(Optional.of(existingGeneration));
		when(generationRepository.saveAndFlush(any(Generation.class))).thenReturn(existingGeneration);

		ResponseEntity<Generation> response = generationService.updateGeneration(1, newGenerationDTO);

		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());

		Generation updatedGeneration = response.getBody();
		assertNotNull(updatedGeneration);
		assertEquals(newGenerationDTO.getNumber(), updatedGeneration.getNumber());
		assertEquals(newGenerationDTO.getRegion(), updatedGeneration.getRegion());
		assertEquals(newGenerationDTO.getYear(), updatedGeneration.getYear());
	}

	@Test
	public void testUpdateGeneration_NotFound() {
		GenerationDTO newGenerationDTO = new GenerationDTO(2, "Johto", 1999);

		when(generationRepository.findById(1)).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> {
			generationService.updateGeneration(1, newGenerationDTO);
		});
	}

	@Test
	public void testDeleteGeneration() {
		Generation generation = new Generation(1, "Kanto", 1996);

		when(generationRepository.findById(1)).thenReturn(Optional.of(generation));
		doNothing().when(generationRepository).deleteById(1);

		ResponseEntity<Generation> response = generationService.deleteGeneration(1);

		assertNotNull(response);
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

		verify(generationRepository, times(1)).deleteById(1);
	}

	@Test
	public void testDeleteGeneration_NotFound() {
		when(generationRepository.findById(1)).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> {
			generationService.deleteGeneration(1);
		});
	}
}
