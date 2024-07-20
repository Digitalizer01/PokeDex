package com.pokedex.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
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

import com.pokedex.dto.PokemonTypeRelationDTO;
import com.pokedex.enums.EffectivenessPercentageEnum;
import com.pokedex.exception.ResourceNotFoundException;
import com.pokedex.model.PokemonType;
import com.pokedex.model.PokemonTypeRelation;
import com.pokedex.repository.PokemonTypeRelationRepository;
import com.pokedex.service.impl.PokemonTypeRelationServiceImpl;

@ExtendWith(MockitoExtension.class)
public class PokemonTypeRelationServiceTests {

	@Mock
	private PokemonTypeRelationRepository pokemonTypeRelationRepository;

	@Mock
	private PokemonTypeService pokemonTypeService;

	@InjectMocks
	private PokemonTypeRelationServiceImpl pokemonTypeRelationService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testAddPokemonTypeRelation() {
		PokemonType pokemonType = new PokemonType("Fire");
		PokemonType relatedPokemonType = new PokemonType("Water");
		PokemonTypeRelationDTO pokemonTypeRelationDTO = new PokemonTypeRelationDTO("Fire", "Water",
				EffectivenessPercentageEnum.SUPER_EFFECTIVE.getValue());
		PokemonTypeRelation pokemonTypeRelation = new PokemonTypeRelation(pokemonType, relatedPokemonType,
				EffectivenessPercentageEnum.SUPER_EFFECTIVE.getValue());

		when(pokemonTypeService.getPokemonTypeByName("Fire")).thenReturn(pokemonType);
		when(pokemonTypeService.getPokemonTypeByName("Water")).thenReturn(relatedPokemonType);
		when(pokemonTypeRelationRepository.saveAndFlush(any(PokemonTypeRelation.class)))
				.thenReturn(pokemonTypeRelation);

		PokemonTypeRelation createdPokemonTypeRelation = pokemonTypeRelationService
				.addPokemonTypeRelation(pokemonTypeRelationDTO);

		assertNotNull(createdPokemonTypeRelation);
		assertEquals(pokemonType, createdPokemonTypeRelation.getPokemonType());
		assertEquals(relatedPokemonType, createdPokemonTypeRelation.getRelatedPokemonType());
		assertEquals(EffectivenessPercentageEnum.SUPER_EFFECTIVE.getValue(),
				createdPokemonTypeRelation.getEffectivenessPercentage());
	}

	@Test
	public void testGetPokemonTypeRelationById() {
		PokemonTypeRelation pokemonTypeRelation = new PokemonTypeRelation(new PokemonType("Fire"),
				new PokemonType("Water"), EffectivenessPercentageEnum.SUPER_EFFECTIVE.getValue());

		when(pokemonTypeRelationRepository.findById(1)).thenReturn(Optional.of(pokemonTypeRelation));

		PokemonTypeRelation foundPokemonTypeRelation = pokemonTypeRelationService.getPokemonTypeRelationById(1);

		assertNotNull(foundPokemonTypeRelation);
		assertEquals(pokemonTypeRelation, foundPokemonTypeRelation);
	}

	@Test
	public void testGetPokemonTypeRelationById_NotFound() {
		when(pokemonTypeRelationRepository.findById(1)).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> {
			pokemonTypeRelationService.getPokemonTypeRelationById(1);
		});
	}

	@Test
	public void testGetEffectivenessPercentage() {
		when(pokemonTypeRelationRepository
				.findPokemonTypeRelationEffectiveness_percentageByPokemonTypeNameAndRelatedPokemonTypeName("Fire",
						"Water"))
				.thenReturn(EffectivenessPercentageEnum.SUPER_EFFECTIVE.getValue());

		int effectivenessPercentage = pokemonTypeRelationService.getEffectivenessPercentage("Fire", "Water");

		assertEquals(EffectivenessPercentageEnum.SUPER_EFFECTIVE.getValue(), effectivenessPercentage);
	}

	@Test
	public void testGetPokemonTypeRelationByEffectivenessPercentage() {
		PokemonTypeRelation pokemonTypeRelation1 = new PokemonTypeRelation(new PokemonType("Fire"),
				new PokemonType("Grass"), EffectivenessPercentageEnum.SUPER_EFFECTIVE.getValue());
		PokemonTypeRelation pokemonTypeRelation2 = new PokemonTypeRelation(new PokemonType("Fire"),
				new PokemonType("Bug"), EffectivenessPercentageEnum.SUPER_EFFECTIVE.getValue());

		List<PokemonTypeRelation> pokemonTypeRelations = Arrays.asList(pokemonTypeRelation1, pokemonTypeRelation2);

		when(pokemonTypeRelationRepository.findAllByPokemonTypeNameAndEffectivenessPercentage("Fire",
				EffectivenessPercentageEnum.SUPER_EFFECTIVE.getValue())).thenReturn(pokemonTypeRelations);

		List<PokemonTypeRelation> foundPokemonTypeRelations = pokemonTypeRelationService
				.getPokemonTypeRelationByEffectivenessPercentage("Fire",
						EffectivenessPercentageEnum.SUPER_EFFECTIVE.getValue());

		assertNotNull(foundPokemonTypeRelations);
		assertEquals(2, foundPokemonTypeRelations.size());
	}

	@Test
	public void testGetAllPokemonTypeRelations() {
		List<PokemonTypeRelation> pokemonTypeRelations = Arrays.asList(
				new PokemonTypeRelation(new PokemonType("Fire"), new PokemonType("Grass"),
						EffectivenessPercentageEnum.SUPER_EFFECTIVE.getValue()),
				new PokemonTypeRelation(new PokemonType("Water"), new PokemonType("Fire"),
						EffectivenessPercentageEnum.SUPER_EFFECTIVE.getValue()));

		when(pokemonTypeRelationRepository.findAll()).thenReturn(pokemonTypeRelations);

		List<PokemonTypeRelation> allPokemonTypeRelations = pokemonTypeRelationService.getAllPokemonTypeRelations();

		assertNotNull(allPokemonTypeRelations);
		assertEquals(2, allPokemonTypeRelations.size());
	}

	@Test
	public void testUpdatePokemonTypeRelationById() {
		PokemonType existingPokemonType = new PokemonType("Fire");
		PokemonType relatedPokemonType = new PokemonType("Water");
		PokemonTypeRelation existingRelation = new PokemonTypeRelation(existingPokemonType, relatedPokemonType,
				EffectivenessPercentageEnum.SUPER_EFFECTIVE.getValue());

		when(pokemonTypeRelationRepository.findById(1)).thenReturn(Optional.of(existingRelation));
		when(pokemonTypeService.getPokemonTypeByName("Electric")).thenReturn(relatedPokemonType);

		PokemonTypeRelationDTO newPokemonTypeRelationDTO = new PokemonTypeRelationDTO("Fire", "Electric", 150);

		ResponseEntity<PokemonTypeRelation> response = pokemonTypeRelationService.updatePokemonTypeRelationById(1,
				newPokemonTypeRelationDTO);

		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());

		PokemonTypeRelation updatedPokemonTypeRelation = response.getBody();
		assertNotNull(updatedPokemonTypeRelation);
		assertEquals("Fire", updatedPokemonTypeRelation.getPokemonType().getName());
		assertEquals("Electric", updatedPokemonTypeRelation.getRelatedPokemonType().getName());
		assertEquals(150, updatedPokemonTypeRelation.getEffectivenessPercentage());
	}

	@Test
	public void testUpdatePokemonTypeRelationById_NotFound() {
		PokemonTypeRelationDTO newPokemonTypeRelationDTO = new PokemonTypeRelationDTO("Fire", "Electric",
				EffectivenessPercentageEnum.NORMAL.getValue());

		when(pokemonTypeRelationRepository.findById(1)).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> {
			pokemonTypeRelationService.updatePokemonTypeRelationById(1, newPokemonTypeRelationDTO);
		});
	}

	@Test
	public void testDeletePokemonTypeRelationById() {
		PokemonTypeRelation pokemonTypeRelation = new PokemonTypeRelation(new PokemonType("Fire"),
				new PokemonType("Water"), EffectivenessPercentageEnum.SUPER_EFFECTIVE.getValue());

		when(pokemonTypeRelationRepository.findById(1)).thenReturn(Optional.of(pokemonTypeRelation));
		doNothing().when(pokemonTypeRelationRepository).deleteById(1);

		ResponseEntity<PokemonTypeRelation> response = pokemonTypeRelationService.deletePokemonTypeRelationById(1);

		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(pokemonTypeRelation, response.getBody());
	}

	@Test
	public void testDeletePokemonTypeRelationById_NotFound() {
		when(pokemonTypeRelationRepository.findById(1)).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> {
			pokemonTypeRelationService.deletePokemonTypeRelationById(1);
		});
	}
}
