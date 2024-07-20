package com.pokedex.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.pokedex.dto.PokemonTypeRelationDTO;
import com.pokedex.model.PokemonTypeRelation;

public interface PokemonTypeRelationService {
	// Create
	PokemonTypeRelation addPokemonTypeRelation(PokemonTypeRelationDTO pokemonTypeRelationDTO);

	// Get
	PokemonTypeRelation getPokemonTypeRelationById(int id);

	int getEffectivenessPercentage(String namePokemonType, String nameRelatedPokemonType);

	List<PokemonTypeRelation> getPokemonTypeRelationByEffectivenessPercentage(String namePokemonType,
			int effectiveness_percentage);

	List<PokemonTypeRelation> getAllPokemonTypeRelations();

	// Set

	// Update
	ResponseEntity<PokemonTypeRelation> updatePokemonTypeRelationById(int id,
			PokemonTypeRelationDTO newPokemonTypeRelationDTO);

	// Delete
	ResponseEntity<PokemonTypeRelation> deletePokemonTypeRelationById(int id);
}
