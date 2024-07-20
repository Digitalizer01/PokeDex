package com.pokedex.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pokedex.dto.PokemonTypeRelationDTO;
import com.pokedex.exception.ResourceNotFoundException;
import com.pokedex.model.PokemonType;
import com.pokedex.model.PokemonTypeRelation;
import com.pokedex.repository.PokemonTypeRelationRepository;
import com.pokedex.service.PokemonTypeRelationService;
import com.pokedex.service.PokemonTypeService;

@Service
public class PokemonTypeRelationServiceImpl implements PokemonTypeRelationService {

	@Autowired
	private PokemonTypeService pokemonTypeService;

	@Autowired
	private PokemonTypeRelationRepository pokemonTypeRelationRepository;

	@Override
	public PokemonTypeRelation addPokemonTypeRelation(PokemonTypeRelationDTO pokemonTypeRelationDTO) {
		PokemonType pokemonType = pokemonTypeService.getPokemonTypeByName(pokemonTypeRelationDTO.getNamePokemonType());
		PokemonType relatedPokemonType = pokemonTypeService
				.getPokemonTypeByName(pokemonTypeRelationDTO.getNameRelatedPokemonType());
		int effectivenessPercentage = pokemonTypeRelationDTO.getEffectivenessPercentage();

		PokemonTypeRelation pokemonTypeRelation = new PokemonTypeRelation(pokemonType, relatedPokemonType,
				effectivenessPercentage);
		return pokemonTypeRelationRepository.saveAndFlush(pokemonTypeRelation);
	}

	@Override
	public PokemonTypeRelation getPokemonTypeRelationById(int id) {
		return pokemonTypeRelationRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("PokemonTypeRelation not found. Id: " + id));
	}

	@Override
	public int getEffectivenessPercentage(String namePokemonType, String nameRelatedPokemonType) {
		return pokemonTypeRelationRepository
				.findPokemonTypeRelationEffectiveness_percentageByPokemonTypeNameAndRelatedPokemonTypeName(
						namePokemonType, nameRelatedPokemonType);
	}

	// For example: type FIGHT, effectiveness_percentage: SUPER EFFECTIVE => Ice,
	// Steel, Normal
	@Override
	public List<PokemonTypeRelation> getPokemonTypeRelationByEffectivenessPercentage(String nameType,
			int effectivenessPercentage) {
		return pokemonTypeRelationRepository.findAllByPokemonTypeNameAndEffectivenessPercentage(nameType,
				effectivenessPercentage);
	}

	@Override
	public List<PokemonTypeRelation> getAllPokemonTypeRelations() {
		return pokemonTypeRelationRepository.findAll();
	}

	@Override
	public ResponseEntity<PokemonTypeRelation> updatePokemonTypeRelationById(int id,
			PokemonTypeRelationDTO newPokemonTypeRelationDTO) {
		PokemonTypeRelation pokemonTypeRelation = getPokemonTypeRelationById(id);

		if (pokemonTypeRelation != null) {
			PokemonType pokemonType = pokemonTypeService
					.getPokemonTypeByName(newPokemonTypeRelationDTO.getNamePokemonType());
			PokemonType relatedPokemonType = pokemonTypeService
					.getPokemonTypeByName(newPokemonTypeRelationDTO.getNameRelatedPokemonType());

			pokemonTypeRelation.setPokemonType(pokemonType);
			pokemonTypeRelation.setRelatedPokemonType(relatedPokemonType);
			pokemonTypeRelation.setEffectivenessPercentage(newPokemonTypeRelationDTO.getEffectivenessPercentage());
		}

		return ResponseEntity.ok(pokemonTypeRelation);
	}

	@Override
	public ResponseEntity<PokemonTypeRelation> deletePokemonTypeRelationById(int id) {
		PokemonTypeRelation pokemonTypeRelation = getPokemonTypeRelationById(id);
		pokemonTypeRelationRepository.deleteById(id);
		return ResponseEntity.ok(pokemonTypeRelation);
	}

}
