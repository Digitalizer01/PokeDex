package com.pokedex.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.pokedex.dto.GenerationDTO;
import com.pokedex.model.Generation;

public interface GenerationService {

	// Create
	Generation addGeneration(GenerationDTO generationDTO);

	// Get
	Generation getGenerationById(int id);

	List<Generation> getAllGenerations();

	List<String> getAllGenerationRegions();

	Generation getGenerationByYear(int year);

	// Set

	// Update
	ResponseEntity<Generation> updateGeneration(int id, GenerationDTO newGenerationDTO);

	// Delete
	ResponseEntity<Generation> deleteGeneration(int id);
}
