package com.pokedex.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.pokedex.dto.PokemonDTO;
import com.pokedex.dto.StatsDTO;
import com.pokedex.model.Generation;
import com.pokedex.model.Pokemon;
import com.pokedex.model.PokemonType;
import com.pokedex.model.Stats;
import com.pokedex.repository.PokemonRepository;
import com.pokedex.service.impl.PokemonServiceImpl;

@ExtendWith(MockitoExtension.class)
public class PokemonServiceTests {

	@InjectMocks
	private PokemonServiceImpl pokemonService;

	@Mock
	private PokemonTypeService pokemonTypeService;

	@Mock
	private StatsService statsService;

	@Mock
	private GenerationService generationService;

	@Mock
	private PokemonRepository pokemonRepository;

	private Pokemon pokemon;
	private PokemonDTO pokemonDTO;
	private PokemonType type1;
	private PokemonType type2;
	private PokemonType newType1;
	private PokemonType newType2;
	private Stats stats;
	private StatsDTO statsDTO;
	private Generation generation;

	@BeforeEach
	public void setUp() {
		type1 = new PokemonType();
		type1.setName("Type1");

		type2 = new PokemonType();
		type2.setName("Type2");

		newType1 = new PokemonType();
		newType1.setName("NewType1");

		newType2 = new PokemonType();
		newType2.setName("NewType2");

		stats = new Stats(1, 2, 3, 4, 5, 6);
		statsDTO = new StatsDTO(1, 2, 3, 4, 5, 6);

		generation = new Generation();
		generation.setId(1);

		pokemon = new Pokemon();
		pokemon.setId(1);
		pokemon.setIdPokedex(1);
		pokemon.setName("Pokemon1");
		pokemon.setPokemonType1(type1);
		pokemon.setPokemonType2(type2);
		pokemon.setDescription("Description");
		pokemon.setStats(stats);
		pokemon.setGeneration(generation);
		pokemon.setLegendary(false);

		pokemonDTO = new PokemonDTO();
		pokemonDTO.setId(1);
		pokemonDTO.setIdPokedex(1);
		pokemonDTO.setName("Pokemon1");
		pokemonDTO.setNamePokemonType1("Type1");
		pokemonDTO.setNamePokemonType2("Type2");
		pokemonDTO.setDescription("Description");
		pokemonDTO.setStats(statsDTO);
		pokemonDTO.setIdGeneration(1);
		pokemonDTO.setLegendary(false);
	}

	@Test
	public void testAddPokemon() {
		when(pokemonTypeService.getPokemonTypeByName("Type1")).thenReturn(type1);
		when(pokemonTypeService.getPokemonTypeByName("Type2")).thenReturn(type2);
		when(generationService.getGenerationById(1)).thenReturn(generation);
		when(pokemonRepository.saveAndFlush(any(Pokemon.class))).thenReturn(pokemon);

		Pokemon createdPokemon = pokemonService.addPokemon(pokemonDTO);

		assertNotNull(createdPokemon);
		assertEquals(pokemon.getName(), createdPokemon.getName());
		verify(pokemonRepository, times(1)).saveAndFlush(any(Pokemon.class));
	}

	@Test
	public void testGetPokemonById() {
		when(pokemonRepository.findById(1)).thenReturn(Optional.of(pokemon));

		Pokemon foundPokemon = pokemonService.getPokemonById(1);

		assertNotNull(foundPokemon);
		assertEquals(pokemon.getName(), foundPokemon.getName());
	}

	@Test
	public void testGetPokemonByIdPokedex() {
		when(pokemonRepository.findByIdPokedex(1)).thenReturn(Optional.of(pokemon));

		Pokemon foundPokemon = pokemonService.getPokemonByIdPokedex(1);

		assertNotNull(foundPokemon);
		assertEquals(pokemon.getName(), foundPokemon.getName());
	}

	@Test
	public void testGetPokemonByName() {
		when(pokemonRepository.findByName("Pokemon1")).thenReturn(Optional.of(pokemon));

		Pokemon foundPokemon = pokemonService.getPokemonByName("Pokemon1");

		assertNotNull(foundPokemon);
		assertEquals(pokemon.getName(), foundPokemon.getName());
	}

	@Test
	public void testGetAllPokemon() {
		List<Pokemon> pokemons = Arrays.asList(pokemon);
		when(pokemonRepository.findAll()).thenReturn(pokemons);

		List<Pokemon> foundPokemons = pokemonService.getAllPokemon();

		assertEquals(1, foundPokemons.size());
		verify(pokemonRepository, times(1)).findAll();
	}

	@Test
	public void testGetPokemonType1ById() {
		when(pokemonRepository.findById(1)).thenReturn(Optional.of(pokemon));

		PokemonType type = pokemonService.getPokemonType1ById(1);

		assertNotNull(type);
		assertEquals(type1.getName(), type.getName());
	}

	@Test
	public void testGetPokemonType1ByIdPokedex() {
		when(pokemonRepository.findByIdPokedex(1)).thenReturn(Optional.of(pokemon));

		PokemonType type = pokemonService.getPokemonType1ByIdPokedex(1);

		assertNotNull(type);
		assertEquals(type1.getName(), type.getName());
	}

	@Test
	public void testGetPokemonType1ByName() {
		when(pokemonRepository.findByName("Pokemon1")).thenReturn(Optional.of(pokemon));

		PokemonType type = pokemonService.getPokemonType1ByName("Pokemon1");

		assertNotNull(type);
		assertEquals(type1.getName(), type.getName());
	}

	@Test
	public void testGetPokemonType2ById() {
		when(pokemonRepository.findById(1)).thenReturn(Optional.of(pokemon));

		PokemonType type = pokemonService.getPokemonType2ById(1);

		assertNotNull(type);
		assertEquals(type2.getName(), type.getName());
	}

	@Test
	public void testGetPokemonType2ByIdPokedex() {
		when(pokemonRepository.findByIdPokedex(1)).thenReturn(Optional.of(pokemon));

		PokemonType type = pokemonService.getPokemonType2ByIdPokedex(1);

		assertNotNull(type);
		assertEquals(type2.getName(), type.getName());
	}

	@Test
	public void testGetPokemonType2ByName() {
		when(pokemonRepository.findByName("Pokemon1")).thenReturn(Optional.of(pokemon));

		PokemonType type = pokemonService.getPokemonType2ByName("Pokemon1");

		assertNotNull(type);
		assertEquals(type2.getName(), type.getName());
	}

	@Test
	public void testGetAllPokemonByPokemonType1Name() {
		List<Pokemon> pokemons = Arrays.asList(pokemon);
		when(pokemonRepository.findAllByPokemonType1Name("Type1")).thenReturn(pokemons);

		List<Pokemon> foundPokemons = pokemonService.getAllPokemonByPokemonType1Name("Type1");

		assertEquals(1, foundPokemons.size());
	}

	@Test
	public void testGetAllPokemonByPokemonType2Name() {
		List<Pokemon> pokemons = Arrays.asList(pokemon);
		when(pokemonRepository.findAllByPokemonType2Name("Type2")).thenReturn(pokemons);

		List<Pokemon> foundPokemons = pokemonService.getAllPokemonByPokemonType2Name("Type2");

		assertEquals(1, foundPokemons.size());
	}

	@Test
	public void testGetAllPokemonByPokemonType1NameAndPokemonType2Name() {
		List<Pokemon> pokemons = Arrays.asList(pokemon);
		when(pokemonRepository.findAllByPokemonType1NameAndPokemonType2Name("Type1", "Type2")).thenReturn(pokemons);

		List<Pokemon> foundPokemons = pokemonService.getAllPokemonByPokemonType1NameAndPokemonType2Name("Type1",
				"Type2");

		assertEquals(1, foundPokemons.size());
	}

	@Test
	public void testGetDescriptionById() {
		when(pokemonRepository.findById(1)).thenReturn(Optional.of(pokemon));

		String description = pokemonService.getDescriptionById(1);

		assertEquals(pokemon.getDescription(), description);
	}

	@Test
	public void testGetDescriptionByIdPokedex() {
		when(pokemonRepository.findByIdPokedex(1)).thenReturn(Optional.of(pokemon));

		String description = pokemonService.getDescriptionByIdPokedex(1);

		assertEquals(pokemon.getDescription(), description);
	}

	@Test
	public void testGetDescriptionByName() {
		when(pokemonRepository.findByName("Pokemon1")).thenReturn(Optional.of(pokemon));

		String description = pokemonService.getDescriptionByName("Pokemon1");

		assertEquals(pokemon.getDescription(), description);
	}

	@Test
	public void testGetStatsById() {
		when(pokemonRepository.findById(1)).thenReturn(Optional.of(pokemon));

		Stats foundStats = pokemonService.getStatsById(1);

		assertEquals(pokemon.getStats(), foundStats);
	}

	@Test
	public void testGetStatsByIdPokedex() {
		when(pokemonRepository.findByIdPokedex(1)).thenReturn(Optional.of(pokemon));

		Stats foundStats = pokemonService.getStatsByIdPokedex(1);

		assertEquals(pokemon.getStats(), foundStats);
	}

	@Test
	public void testGetStatsByName() {
		when(pokemonRepository.findByName("Pokemon1")).thenReturn(Optional.of(pokemon));

		Stats foundStats = pokemonService.getStatsByName("Pokemon1");

		assertEquals(pokemon.getStats(), foundStats);
	}

	@Test
	public void testGetGenerationById() {
		when(pokemonRepository.findById(1)).thenReturn(Optional.of(pokemon));

		Generation foundGeneration = pokemonService.getGenerationById(1);

		assertEquals(pokemon.getGeneration(), foundGeneration);
	}

	@Test
	public void testGetGenerationByIdPokedex() {
		when(pokemonRepository.findByIdPokedex(1)).thenReturn(Optional.of(pokemon));

		Generation foundGeneration = pokemonService.getGenerationByIdPokedex(1);

		assertEquals(pokemon.getGeneration(), foundGeneration);
	}

	@Test
	public void testGetGenerationByName() {
		when(pokemonRepository.findByName("Pokemon1")).thenReturn(Optional.of(pokemon));

		Generation foundGeneration = pokemonService.getGenerationByName("Pokemon1");

		assertEquals(pokemon.getGeneration(), foundGeneration);
	}

	@Test
	public void testIsLegendaryById() {
		when(pokemonRepository.findById(1)).thenReturn(Optional.of(pokemon));

		boolean isLegendary = pokemonService.isLegendaryById(1);

		assertFalse(isLegendary);
	}

	@Test
	public void testIsLegendaryByIdPokedex() {
		when(pokemonRepository.findByIdPokedex(1)).thenReturn(Optional.of(pokemon));

		boolean isLegendary = pokemonService.isLegendaryByIdPokedex(1);

		assertFalse(isLegendary);
	}

	@Test
	public void testIsLegendaryByName() {
		when(pokemonRepository.findByName("Pokemon1")).thenReturn(Optional.of(pokemon));

		boolean isLegendary = pokemonService.isLegendaryByName("Pokemon1");

		assertFalse(isLegendary);
	}

	@Test
	public void testSetIdPokedexById() {
		when(pokemonRepository.findById(1)).thenReturn(Optional.of(pokemon));
		when(pokemonRepository.saveAndFlush(any(Pokemon.class))).thenReturn(pokemon);

		ResponseEntity<Pokemon> response = pokemonService.setIdPokedexById(1, 2);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(2, response.getBody().getIdPokedex());
	}

	@Test
	public void testSetIdPokedexByName() {
		when(pokemonRepository.findByName("Pokemon1")).thenReturn(Optional.of(pokemon));
		when(pokemonRepository.saveAndFlush(any(Pokemon.class))).thenReturn(pokemon);

		ResponseEntity<Pokemon> response = pokemonService.setIdPokedexByName("Pokemon1", 2);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(2, response.getBody().getIdPokedex());
	}

	@Test
	public void testSetNameById() {
		when(pokemonRepository.findById(1)).thenReturn(Optional.of(pokemon));
		when(pokemonRepository.saveAndFlush(any(Pokemon.class))).thenReturn(pokemon);

		ResponseEntity<Pokemon> response = pokemonService.setNameById(1, "NewName");

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("NewName", response.getBody().getName());
	}

	@Test
	public void testSetNameByIdPokedex() {
		when(pokemonRepository.findByIdPokedex(1)).thenReturn(Optional.of(pokemon));
		when(pokemonRepository.saveAndFlush(any(Pokemon.class))).thenReturn(pokemon);

		ResponseEntity<Pokemon> response = pokemonService.setNameByIdPokedex("NewName", 1);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("NewName", response.getBody().getName());
	}

	@Test
	public void testSetNameByNewName() {
		when(pokemonRepository.findByName("Pokemon1")).thenReturn(Optional.of(pokemon));
		when(pokemonRepository.saveAndFlush(any(Pokemon.class))).thenReturn(pokemon);

		ResponseEntity<Pokemon> response = pokemonService.setNameByNewName("Pokemon1", "NewName");

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("NewName", response.getBody().getName());
	}

	@Test
	public void testSetPokemonType1ById() {
		when(pokemonRepository.findById(1)).thenReturn(Optional.of(pokemon));
		when(pokemonTypeService.getPokemonTypeByName("NewType1")).thenReturn(newType1);
		when(pokemonRepository.saveAndFlush(any(Pokemon.class))).thenReturn(pokemon);

		ResponseEntity<Pokemon> response = pokemonService.setPokemonType1ById(1, "NewType1");

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("NewType1", response.getBody().getPokemonType1().getName());
	}

	@Test
	public void testSetPokemonType1ByIdPokedex() {
		when(pokemonRepository.findByIdPokedex(1)).thenReturn(Optional.of(pokemon));
		when(pokemonTypeService.getPokemonTypeByName("NewType1")).thenReturn(newType1);
		when(pokemonRepository.saveAndFlush(any(Pokemon.class))).thenReturn(pokemon);

		ResponseEntity<Pokemon> response = pokemonService.setPokemonType1ByIdPokedex(1, "NewType1");

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("NewType1", response.getBody().getPokemonType1().getName());
	}

	@Test
	public void testSetPokemonType1ByName() {
		when(pokemonRepository.findByName("Pokemon1")).thenReturn(Optional.of(pokemon));
		when(pokemonTypeService.getPokemonTypeByName("NewType1")).thenReturn(newType1);
		when(pokemonRepository.saveAndFlush(any(Pokemon.class))).thenReturn(pokemon);

		ResponseEntity<Pokemon> response = pokemonService.setPokemonType1ByName("Pokemon1", "NewType1");

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("NewType1", response.getBody().getPokemonType1().getName());
	}

	@Test
	public void testSetPokemonType2ById() {
		when(pokemonRepository.findById(1)).thenReturn(Optional.of(pokemon));
		when(pokemonTypeService.getPokemonTypeByName("NewType2")).thenReturn(newType2);
		when(pokemonRepository.saveAndFlush(any(Pokemon.class))).thenReturn(pokemon);

		ResponseEntity<Pokemon> response = pokemonService.setPokemonType2ById(1, "NewType2");

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("NewType2", response.getBody().getPokemonType2().getName());
	}

	@Test
	public void testSetPokemonType2ByIdPokedex() {
		when(pokemonRepository.findByIdPokedex(1)).thenReturn(Optional.of(pokemon));
		when(pokemonTypeService.getPokemonTypeByName("NewType2")).thenReturn(newType2);
		when(pokemonRepository.saveAndFlush(any(Pokemon.class))).thenReturn(pokemon);

		ResponseEntity<Pokemon> response = pokemonService.setPokemonType2ByIdPokedex(1, "NewType2");

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("NewType2", response.getBody().getPokemonType2().getName());
	}

	@Test
	public void testSetPokemonType2ByName() {
		when(pokemonRepository.findByName("Pokemon1")).thenReturn(Optional.of(pokemon));
		when(pokemonTypeService.getPokemonTypeByName("NewType2")).thenReturn(newType2);
		when(pokemonRepository.saveAndFlush(any(Pokemon.class))).thenReturn(pokemon);

		ResponseEntity<Pokemon> response = pokemonService.setPokemonType2ByName("Pokemon1", "NewType2");

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("NewType2", response.getBody().getPokemonType2().getName());
	}

	@Test
	public void testSetPokemonType1AndType2ById() {
		when(pokemonRepository.findById(1)).thenReturn(Optional.of(pokemon));
		when(pokemonTypeService.getPokemonTypeByName("NewType1")).thenReturn(newType1);
		when(pokemonTypeService.getPokemonTypeByName("NewType2")).thenReturn(newType2);
		when(pokemonRepository.saveAndFlush(any(Pokemon.class))).thenReturn(pokemon);

		ResponseEntity<Pokemon> response = pokemonService.setPokemonType1AndType2ById(1, "NewType1", "NewType2");

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("NewType1", response.getBody().getPokemonType1().getName());
		assertEquals("NewType2", response.getBody().getPokemonType2().getName());
	}

	@Test
	public void testSetPokemonType1AndType2ByIdPokedex() {
		when(pokemonRepository.findByIdPokedex(1)).thenReturn(Optional.of(pokemon));
		when(pokemonTypeService.getPokemonTypeByName("NewType1")).thenReturn(newType1);
		when(pokemonTypeService.getPokemonTypeByName("NewType2")).thenReturn(newType2);
		when(pokemonRepository.saveAndFlush(any(Pokemon.class))).thenReturn(pokemon);

		ResponseEntity<Pokemon> response = pokemonService.setPokemonType1AndType2ByIdPokedex(1, "NewType1", "NewType2");

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("NewType1", response.getBody().getPokemonType1().getName());
		assertEquals("NewType2", response.getBody().getPokemonType2().getName());
	}

	@Test
	public void testSetPokemonType1AndType2ByName() {
		when(pokemonRepository.findByName("Pokemon1")).thenReturn(Optional.of(pokemon));
		when(pokemonTypeService.getPokemonTypeByName("NewType1")).thenReturn(newType1);
		when(pokemonTypeService.getPokemonTypeByName("NewType2")).thenReturn(newType2);
		when(pokemonRepository.saveAndFlush(any(Pokemon.class))).thenReturn(pokemon);

		ResponseEntity<Pokemon> response = pokemonService.setPokemonType1AndType2ByName("Pokemon1", "NewType1",
				"NewType2");

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("NewType1", response.getBody().getPokemonType1().getName());
		assertEquals("NewType2", response.getBody().getPokemonType2().getName());
	}

	@Test
	public void testSetDescriptionById() {
		when(pokemonRepository.findById(1)).thenReturn(Optional.of(pokemon));
		when(pokemonRepository.saveAndFlush(any(Pokemon.class))).thenReturn(pokemon);

		ResponseEntity<Pokemon> response = pokemonService.setDescriptionById(1, "NewDescription");

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("NewDescription", response.getBody().getDescription());
	}

	@Test
	public void testSetDescriptionByIdPokedex() {
		when(pokemonRepository.findByIdPokedex(1)).thenReturn(Optional.of(pokemon));
		when(pokemonRepository.saveAndFlush(any(Pokemon.class))).thenReturn(pokemon);

		ResponseEntity<Pokemon> response = pokemonService.setDescriptionByIdPokedex(1, "NewDescription");

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("NewDescription", response.getBody().getDescription());
	}

	@Test
	public void testSetDescriptionByName() {
		when(pokemonRepository.findByName("Pokemon1")).thenReturn(Optional.of(pokemon));
		when(pokemonRepository.saveAndFlush(any(Pokemon.class))).thenReturn(pokemon);

		ResponseEntity<Pokemon> response = pokemonService.setDescriptionByName("Pokemon1", "NewDescription");

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("NewDescription", response.getBody().getDescription());
	}

	@Test
	public void testSetStatsById() {
		when(pokemonRepository.findById(1)).thenReturn(Optional.of(pokemon));
		when(statsService.getStatsById(2)).thenReturn(stats);
		when(pokemonRepository.saveAndFlush(any(Pokemon.class))).thenReturn(pokemon);

		ResponseEntity<Pokemon> response = pokemonService.setStatsById(1, 2);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(stats, response.getBody().getStats());
	}

	@Test
	public void testSetStatsByIdPokedex() {
		when(pokemonRepository.findByIdPokedex(1)).thenReturn(Optional.of(pokemon));
		when(statsService.getStatsById(2)).thenReturn(stats);
		when(pokemonRepository.saveAndFlush(any(Pokemon.class))).thenReturn(pokemon);

		ResponseEntity<Pokemon> response = pokemonService.setStatsByIdPokedex(1, 2);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(stats, response.getBody().getStats());
	}

	@Test
	public void testSetStatsByName() {
		when(pokemonRepository.findByName("Pokemon1")).thenReturn(Optional.of(pokemon));
		when(statsService.getStatsById(2)).thenReturn(stats);
		when(pokemonRepository.saveAndFlush(any(Pokemon.class))).thenReturn(pokemon);

		ResponseEntity<Pokemon> response = pokemonService.setStatsByName("Pokemon1", 2);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(stats, response.getBody().getStats());
	}

	@Test
	public void testSetGenerationById() {
		when(pokemonRepository.findById(1)).thenReturn(Optional.of(pokemon));
		when(generationService.getGenerationById(2)).thenReturn(generation);
		when(pokemonRepository.saveAndFlush(any(Pokemon.class))).thenReturn(pokemon);

		ResponseEntity<Pokemon> response = pokemonService.setGenerationById(1, 2);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(generation, response.getBody().getGeneration());
	}

	@Test
	public void testSetGenerationByIdPokedex() {
		when(pokemonRepository.findByIdPokedex(1)).thenReturn(Optional.of(pokemon));
		when(generationService.getGenerationById(2)).thenReturn(generation);
		when(pokemonRepository.saveAndFlush(any(Pokemon.class))).thenReturn(pokemon);

		ResponseEntity<Pokemon> response = pokemonService.setGenerationByIdPokedex(1, 2);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(generation, response.getBody().getGeneration());
	}

	@Test
	public void testSetGenerationByName() {
		when(pokemonRepository.findByName("Pokemon1")).thenReturn(Optional.of(pokemon));
		when(generationService.getGenerationById(2)).thenReturn(generation);
		when(pokemonRepository.saveAndFlush(any(Pokemon.class))).thenReturn(pokemon);

		ResponseEntity<Pokemon> response = pokemonService.setGenerationByName("Pokemon1", 2);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(generation, response.getBody().getGeneration());
	}

	@Test
	public void testSetLegendaryById() {
		when(pokemonRepository.findById(1)).thenReturn(Optional.of(pokemon));
		when(pokemonRepository.saveAndFlush(any(Pokemon.class))).thenReturn(pokemon);

		ResponseEntity<Pokemon> response = pokemonService.setLegendaryById(1, true);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertTrue(response.getBody().isLegendary());
	}

	@Test
	public void testSetLegendaryByIdPokedex() {
		when(pokemonRepository.findByIdPokedex(1)).thenReturn(Optional.of(pokemon));
		when(pokemonRepository.saveAndFlush(any(Pokemon.class))).thenReturn(pokemon);

		ResponseEntity<Pokemon> response = pokemonService.setLegendaryByIdPokedex(1, true);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertTrue(response.getBody().isLegendary());
	}

	@Test
	public void testSetLegendaryByName() {
		when(pokemonRepository.findByName("Pokemon1")).thenReturn(Optional.of(pokemon));
		when(pokemonRepository.saveAndFlush(any(Pokemon.class))).thenReturn(pokemon);

		ResponseEntity<Pokemon> response = pokemonService.setLegendaryByName("Pokemon1", true);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertTrue(response.getBody().isLegendary());
	}

	@Test
	public void testUpdatePokemonById() {
		when(pokemonRepository.findById(1)).thenReturn(Optional.of(pokemon));

		ResponseEntity<Pokemon> response = pokemonService.updatePokemonById(1, pokemonDTO);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(pokemonDTO.getName(), response.getBody().getName());
	}

	@Test
	public void testUpdatePokemonByIdPokedex() {
		when(pokemonRepository.findByIdPokedex(1)).thenReturn(Optional.of(pokemon));

		ResponseEntity<Pokemon> response = pokemonService.updatePokemonByIdPokedex(1, pokemonDTO);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(pokemonDTO.getName(), response.getBody().getName());
	}

	@Test
	public void testUpdatePokemonByName() {
		when(pokemonRepository.findByName("Pokemon1")).thenReturn(Optional.of(pokemon));

		ResponseEntity<Pokemon> response = pokemonService.updatePokemonByName("Pokemon1", pokemonDTO);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(pokemonDTO.getName(), response.getBody().getName());
	}

	@Test
	public void testDeletePokemonById() {
		when(pokemonRepository.findById(1)).thenReturn(Optional.of(pokemon));
		doNothing().when(pokemonRepository).deleteById(1);

		ResponseEntity<Pokemon> response = pokemonService.deletePokemonById(1);

		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}

	@Test
	public void testDeletePokemonByIdPokedex() {
		when(pokemonRepository.findByIdPokedex(1)).thenReturn(Optional.of(pokemon));
		doNothing().when(pokemonRepository).deleteByIdPokedex(1);

		ResponseEntity<Pokemon> response = pokemonService.deletePokemonByIdPokedex(1);

		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}

	@Test
	public void testDeletePokemonByName() {
		when(pokemonRepository.findByName("Pokemon1")).thenReturn(Optional.of(pokemon));
		doNothing().when(pokemonRepository).deleteByName("Pokemon1");

		ResponseEntity<Pokemon> response = pokemonService.deletePokemonByName("Pokemon1");

		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}

}
