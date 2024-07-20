package com.pokedex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pokedex.dto.GenerationDTO;
import com.pokedex.model.Generation;
import com.pokedex.service.GenerationService;

@RestController
@RequestMapping("/generation")
public class GenerationController {

	@Autowired
	private GenerationService generationService;

	@PostMapping
	public Generation addGeneration(@RequestBody GenerationDTO generationDTO) {
		return generationService.addGeneration(generationDTO);
	}

	@GetMapping("/{id}")
	public Generation getGenerationById(@PathVariable int id) {
		return generationService.getGenerationById(id);
	}

	@GetMapping("/all")
	public List<Generation> getAllGenerations() {
		return generationService.getAllGenerations();
	}

	@GetMapping("/all/names")
	public List<String> getAllGenerationNames() {
		return generationService.getAllGenerationRegions();
	}

	@GetMapping("/year/{year}")
	public Generation getGenerationsByYear(@PathVariable int year) {
		return generationService.getGenerationsByYear(year);
	}

	@PutMapping("/{id}")
	public void updateGeneration(@PathVariable int id, @RequestBody GenerationDTO generationDTO) {
		generationService.updateGeneration(id, generationDTO);
	}

	@DeleteMapping("/{id}")
	public void deleteGeneration(@PathVariable int id) {
		generationService.deleteGeneration(id);
	}
}
