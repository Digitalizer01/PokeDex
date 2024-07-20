package com.pokedex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pokedex.dto.PokemonTypeRelationDTO;
import com.pokedex.model.PokemonTypeRelation;
import com.pokedex.service.PokemonTypeRelationService;

@RestController
@RequestMapping("/pokemontyperelation")
public class PokemonTypeRelationController {

	@Autowired
	private PokemonTypeRelationService pokemonTypeRelationService;

	@PostMapping
	public PokemonTypeRelation addPokemonTypeRelation(PokemonTypeRelationDTO pokemonTypeRelationDTO) {
		return pokemonTypeRelationService.addPokemonTypeRelation(pokemonTypeRelationDTO);
	}

	@GetMapping("/{id}")
	public PokemonTypeRelation getPokemonTypeRelationById(@PathVariable int id) {
		return pokemonTypeRelationService.getPokemonTypeRelationById(id);
	}

	@GetMapping("/{id}/{idrelated}")
	public int getEffectivenessPercentage(@PathVariable int id, @PathVariable int idrelated) {
		return getEffectivenessPercentage(id, idrelated);
	}

}
