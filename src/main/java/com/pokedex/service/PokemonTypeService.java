package com.pokedex.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.pokedex.dto.PokemonTypeDTO;
import com.pokedex.model.PokemonType;

public interface PokemonTypeService {
	// Create
	PokemonType addPokemonType(PokemonTypeDTO pokemonTypeDTO);

	// Get
	PokemonType getPokemonTypeById(int id);

	PokemonType getPokemonTypeByName(String name);

	List<PokemonType> getAllPokemonTypes();

	// Set

	// Update
	ResponseEntity<PokemonType> updatePokemonTypeById(int id, PokemonTypeDTO newPokemonTypeDTO);

	ResponseEntity<PokemonType> updatePokemonTypeByName(String name, PokemonTypeDTO newPokemonTypeDTO);

	// Delete
	ResponseEntity<PokemonType> deletePokemonTypeById(int id);
}
