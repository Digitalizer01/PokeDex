package com.pokedex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pokedex.dto.PokemonTypeDTO;
import com.pokedex.model.PokemonType;
import com.pokedex.service.PokemonTypeService;

@RestController
@RequestMapping("/pokemontype")
public class PokemonTypeController {

	@Autowired
	private PokemonTypeService pokemonTypeService;

	@PostMapping
	public PokemonType addPokemonType(@RequestBody PokemonTypeDTO pokemonTypeDTO) {
		return pokemonTypeService.addPokemonType(pokemonTypeDTO);
	}

	@GetMapping("/{id}")
	public PokemonType getPokemonTypeById(@PathVariable int id) {
		return pokemonTypeService.getPokemonTypeById(id);
	}

	@GetMapping("/{name}")
	public PokemonType getPokemonTypeByName(@PathVariable String name) {
		return pokemonTypeService.getPokemonTypeByName(name);
	}

	@GetMapping("/all")
	public List<PokemonType> getAllPokemonTypes() {
		return pokemonTypeService.getAllPokemonTypes();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<PokemonType> deletePokemonTypeById(@PathVariable int id) {
		return pokemonTypeService.deletePokemonTypeById(id);
	}
}
