package com.pokedex.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pokedex.dto.GenerationDTO;
import com.pokedex.exception.ResourceNotFoundException;
import com.pokedex.model.Generation;
import com.pokedex.repository.GenerationRepository;
import com.pokedex.service.GenerationService;

@Service
public class GenerationServiceImpl implements GenerationService {

	@Autowired
	private GenerationRepository generationRepository;

	@Override
	public Generation addGeneration(GenerationDTO generationDTO) {
		Generation generation = new Generation(generationDTO.getNumber(), generationDTO.getRegion(),
				generationDTO.getYear());

		return generationRepository.saveAndFlush(generation);
	}

	@Override
	public Generation getGenerationById(int id) {
		return generationRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Generation not found. Id: " + id));
	}

	@Override
	public List<Generation> getAllGenerations() {
		return generationRepository.findAll();
	}

	@Override
	public List<String> getAllGenerationRegions() {
		return generationRepository.findDistinctRegion();
	}

	@Override
	public Generation getGenerationsByYear(int year) {
		return generationRepository.findGenerationByYear(year);
	}

	@Override
	public ResponseEntity<Generation> updateGeneration(int id, GenerationDTO newGenerationDTO) {
		Generation generation = getGenerationById(id);

		if (generation != null) {
			generation.setNumber(newGenerationDTO.getNumber());
			generation.setRegion(newGenerationDTO.getRegion());
			generation.setYear(newGenerationDTO.getYear());

			generationRepository.saveAndFlush(generation);
		}

		return ResponseEntity.ok(generation);
	}

	@Override
	public ResponseEntity<Generation> deleteGeneration(int id) {
		Generation generation = getGenerationById(id);
		generationRepository.deleteById(id);
		return ResponseEntity.ok(generation);
	}

}
