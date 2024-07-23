package com.pokedex.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.pokedex.dto.PokemonDTO;
import com.pokedex.model.Generation;
import com.pokedex.model.Pokemon;
import com.pokedex.model.PokemonType;
import com.pokedex.model.Stats;

public interface PokemonService {
	// Create
	Pokemon addPokemon(PokemonDTO pokemonDTO);

	// Get
	Pokemon getPokemonById(int id);

	Pokemon getPokemonByIdPokedex(int idpokedex);

	Pokemon getPokemonByName(String name);

	List<Pokemon> getAllPokemon();

	PokemonType getPokemonType1ById(int id);

	PokemonType getPokemonType1ByIdPokedex(int idpokedex);

	PokemonType getPokemonType1ByName(String name);

	PokemonType getPokemonType2ById(int id);

	PokemonType getPokemonType2ByIdPokedex(int idpokedex);

	PokemonType getPokemonType2ByName(String name);

	List<Pokemon> getAllPokemonByPokemonType1Name(String namePokemonType1);

	List<Pokemon> getAllPokemonByPokemonType2Name(String namePokemonType2);

	List<Pokemon> getAllPokemonByPokemonType1NameAndPokemonType2Name(String namePokemonType1, String namePokemonType2);

	String getDescriptionById(int id);

	String getDescriptionByIdPokedex(int idpokedex);

	String getDescriptionByName(String name);

	Stats getStatsById(int id);

	Stats getStatsByIdPokedex(int idpokedex);

	Stats getStatsByName(String name);

	Generation getGenerationById(int id);

	Generation getGenerationByIdPokedex(int idpokedex);

	Generation getGenerationByName(String name);

	Boolean isLegendaryById(int id);

	Boolean isLegendaryByIdPokedex(int idpokedex);

	Boolean isLegendaryByName(String name);

	List<Pokemon> getAllPokemonByGeneration(int idgeneration);

	List<Pokemon> getAllPokemonByGenerationNumber(int number);

	List<Pokemon> getAllPokemonByGenerationRegion(String region);

	List<Pokemon> getAllPokemonByGenerationYear(int year);

	List<Pokemon> getAllPokemonByLegendary(boolean legendary);

	// Set
	ResponseEntity<Pokemon> setIdPokedexById(int id, int idpokedex);

	ResponseEntity<Pokemon> setIdPokedexByName(String name, int idpokedex);

	ResponseEntity<Pokemon> setNameById(int id, String name);

	ResponseEntity<Pokemon> setNameByIdPokedex(String name, int idpokedex);

	ResponseEntity<Pokemon> setNameByNewName(String oldname, String newname);

	ResponseEntity<Pokemon> setPokemonType1ById(int id, String nameType1);

	ResponseEntity<Pokemon> setPokemonType1ByIdPokedex(int idpokedex, String nameType1);

	ResponseEntity<Pokemon> setPokemonType1ByName(String name, String nameType1);

	ResponseEntity<Pokemon> setPokemonType2ById(int id, String nameType2);

	ResponseEntity<Pokemon> setPokemonType2ByIdPokedex(int idpokedex, String nameType2);

	ResponseEntity<Pokemon> setPokemonType2ByName(String name, String nameType2);

	ResponseEntity<Pokemon> setPokemonType1AndType2ById(int id, String nameType1, String nameType2);

	ResponseEntity<Pokemon> setPokemonType1AndType2ByIdPokedex(int idpokedex, String nameType1, String nameType2);

	ResponseEntity<Pokemon> setPokemonType1AndType2ByName(String name, String nameType1, String nameType2);

	ResponseEntity<Pokemon> setDescriptionById(int id, String description);

	ResponseEntity<Pokemon> setDescriptionByIdPokedex(int idpokedex, String description);

	ResponseEntity<Pokemon> setDescriptionByName(String name, String description);

	ResponseEntity<Pokemon> setStatsById(int id, int idstats);

	ResponseEntity<Pokemon> setStatsByIdPokedex(int idpokedex, int idstats);

	ResponseEntity<Pokemon> setStatsByName(String name, int idstats);

	ResponseEntity<Pokemon> setGenerationById(int id, int idgeneration);

	ResponseEntity<Pokemon> setGenerationByIdPokedex(int idpokedex, int idgeneration);

	ResponseEntity<Pokemon> setGenerationByName(String name, int idgeneration);

	ResponseEntity<Pokemon> setLegendaryById(int id, boolean legendary);

	ResponseEntity<Pokemon> setLegendaryByIdPokedex(int idpokedex, boolean legendary);

	ResponseEntity<Pokemon> setLegendaryByName(String name, boolean legendary);

	// Update
	ResponseEntity<Pokemon> updatePokemonById(int id, PokemonDTO newPokemonDTO);

	ResponseEntity<Pokemon> updatePokemonByIdPokedex(int idpokedex, PokemonDTO newPokemonDTO);

	ResponseEntity<Pokemon> updatePokemonByName(String name, PokemonDTO newPokemonDTO);

	// Delete
	ResponseEntity<Pokemon> deletePokemonById(int id);

	ResponseEntity<Pokemon> deletePokemonByIdPokedex(int idpokedex);

	ResponseEntity<Pokemon> deletePokemonByName(String name);
}
