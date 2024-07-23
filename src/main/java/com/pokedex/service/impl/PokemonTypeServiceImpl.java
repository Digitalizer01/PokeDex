package com.pokedex.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pokedex.dto.PokemonTypeDTO;
import com.pokedex.exception.ResourceNotFoundException;
import com.pokedex.model.PokemonType;
import com.pokedex.repository.PokemonTypeRepository;
import com.pokedex.service.PokemonTypeService;

@Service
public class PokemonTypeServiceImpl implements PokemonTypeService {

	@Autowired
	private PokemonTypeRepository pokemonTypeRepository;

	@Override
	public PokemonType addPokemonType(PokemonTypeDTO pokemonTypeDTO) {
		PokemonType pokemonType = new PokemonType(pokemonTypeDTO.getName());
		return pokemonTypeRepository.saveAndFlush(pokemonType);
	}

	@Override
	public PokemonType getPokemonTypeById(int id) {
		return pokemonTypeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("PokemonType not found. Id: " + id));
	}

	@Override
	public PokemonType getPokemonTypeByName(String name) {
		return pokemonTypeRepository.findPokemonTypeByName(name)
				.orElseThrow(() -> new ResourceNotFoundException("PokemonType not found. Name: " + name));
	}

	@Override
	public List<PokemonType> getAllPokemonTypes() {
		return pokemonTypeRepository.findAll();
	}

	@Override
	public ResponseEntity<PokemonType> updatePokemonTypeById(int id, PokemonTypeDTO newPokemonTypeDTO) {
		PokemonType pokemonType = getPokemonTypeById(id);

		if (pokemonType != null) {
			pokemonType.setName(newPokemonTypeDTO.getName());

			pokemonTypeRepository.saveAndFlush(pokemonType);
		}

		return ResponseEntity.ok(pokemonType);
	}

	@Override
	public ResponseEntity<PokemonType> updatePokemonTypeByName(String name, PokemonTypeDTO newPokemonTypeDTO) {
		PokemonType pokemonType = getPokemonTypeByName(name);

		if (pokemonType != null) {
			pokemonType.setName(newPokemonTypeDTO.getName());

			pokemonTypeRepository.saveAndFlush(pokemonType);
		}

		return ResponseEntity.ok(pokemonType);
	}

	@Override
	public ResponseEntity<PokemonType> deletePokemonTypeById(int id) {
		getPokemonTypeById(id);
		pokemonTypeRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
