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

import com.pokedex.dto.PokemonTypeDTO;
import com.pokedex.exception.ResourceNotFoundException;
import com.pokedex.model.PokemonType;
import com.pokedex.repository.PokemonTypeRepository;
import com.pokedex.service.impl.PokemonTypeServiceImpl;

@ExtendWith(MockitoExtension.class)
public class PokemonTypeServiceTests {

	@Mock
	private PokemonTypeRepository pokemonTypeRepository;

	@InjectMocks
	private PokemonTypeServiceImpl pokemonTypeService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testAddPokemonType() {
		PokemonTypeDTO pokemonTypeDTO = new PokemonTypeDTO("Fire");
		PokemonType pokemonType = new PokemonType("Fire");

		when(pokemonTypeRepository.saveAndFlush(any(PokemonType.class))).thenReturn(pokemonType);

		PokemonType createdPokemonType = pokemonTypeService.addPokemonType(pokemonTypeDTO);

		assertNotNull(createdPokemonType);
		assertEquals(pokemonTypeDTO.getName(), createdPokemonType.getName());
	}

	@Test
	public void testGetPokemonTypeById() {
		PokemonType pokemonType = new PokemonType("Fire");

		when(pokemonTypeRepository.findById(1)).thenReturn(Optional.of(pokemonType));

		PokemonType foundPokemonType = pokemonTypeService.getPokemonTypeById(1);

		assertNotNull(foundPokemonType);
		assertEquals(pokemonType.getId(), foundPokemonType.getId());
	}

	@Test
	public void testGetPokemonTypeById_NotFound() {
		when(pokemonTypeRepository.findById(1)).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> {
			pokemonTypeService.getPokemonTypeById(1);
		});
	}

	@Test
	public void testGetPokemonTypeByName() {
		PokemonType pokemonType = new PokemonType("Fire");

		when(pokemonTypeRepository.findPokemonTypeByName("Fire")).thenReturn(Optional.of(pokemonType));

		PokemonType foundPokemonType = pokemonTypeService.getPokemonTypeByName("Fire");

		assertNotNull(foundPokemonType);
		assertEquals(pokemonType.getName(), foundPokemonType.getName());
	}

	@Test
	public void testGetPokemonTypeByName_NotFound() {
		when(pokemonTypeRepository.findPokemonTypeByName("Fire")).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> {
			pokemonTypeService.getPokemonTypeByName("Fire");
		});
	}

	@Test
	public void testGetAllPokemonTypes() {
		List<PokemonType> pokemonTypes = Arrays.asList(new PokemonType("Fire"), new PokemonType("Water"));

		when(pokemonTypeRepository.findAll()).thenReturn(pokemonTypes);

		List<PokemonType> allPokemonTypes = pokemonTypeService.getAllPokemonTypes();

		assertNotNull(allPokemonTypes);
		assertEquals(2, allPokemonTypes.size());
	}

	@Test
	public void testUpdatePokemonTypeById() {
		PokemonType existingPokemonType = new PokemonType("Fire");
		PokemonTypeDTO newPokemonTypeDTO = new PokemonTypeDTO("Electric");

		when(pokemonTypeRepository.findById(1)).thenReturn(Optional.of(existingPokemonType));
		when(pokemonTypeRepository.saveAndFlush(any(PokemonType.class))).thenReturn(existingPokemonType);

		ResponseEntity<PokemonType> response = pokemonTypeService.updatePokemonTypeById(1, newPokemonTypeDTO);

		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());

		PokemonType updatedPokemonType = response.getBody();
		assertNotNull(updatedPokemonType);
		assertEquals(newPokemonTypeDTO.getName(), updatedPokemonType.getName());
	}

	@Test
	public void testUpdatePokemonTypeById_NotFound() {
		PokemonTypeDTO newPokemonTypeDTO = new PokemonTypeDTO("Electric");

		when(pokemonTypeRepository.findById(1)).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> {
			pokemonTypeService.updatePokemonTypeById(1, newPokemonTypeDTO);
		});
	}

	@Test
	public void testUpdatePokemonTypeByName() {
		PokemonType existingPokemonType = new PokemonType("Fire");
		PokemonTypeDTO newPokemonTypeDTO = new PokemonTypeDTO("Electric");

		when(pokemonTypeRepository.findPokemonTypeByName("Fire")).thenReturn(Optional.of(existingPokemonType));
		when(pokemonTypeRepository.saveAndFlush(any(PokemonType.class))).thenReturn(existingPokemonType);

		ResponseEntity<PokemonType> response = pokemonTypeService.updatePokemonTypeByName("Fire", newPokemonTypeDTO);

		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());

		PokemonType updatedPokemonType = response.getBody();
		assertNotNull(updatedPokemonType);
		assertEquals(newPokemonTypeDTO.getName(), updatedPokemonType.getName());
	}

	@Test
	public void testUpdatePokemonTypeByName_NotFound() {
		PokemonTypeDTO newPokemonTypeDTO = new PokemonTypeDTO("Electric");

		when(pokemonTypeRepository.findPokemonTypeByName("Fire")).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> {
			pokemonTypeService.updatePokemonTypeByName("Fire", newPokemonTypeDTO);
		});
	}

	@Test
	public void testDeletePokemonTypeById() {
		PokemonType pokemonType = new PokemonType("Fire");

		when(pokemonTypeRepository.findById(1)).thenReturn(Optional.of(pokemonType));
		doNothing().when(pokemonTypeRepository).deleteById(1);

		ResponseEntity<PokemonType> response = pokemonTypeService.deletePokemonTypeById(1);

		assertNotNull(response);
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}

	@Test
	public void testDeletePokemonTypeById_NotFound() {
		when(pokemonTypeRepository.findById(1)).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> {
			pokemonTypeService.deletePokemonTypeById(1);
		});
	}
}
