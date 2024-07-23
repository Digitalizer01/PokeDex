package com.pokedex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pokedex.dto.GenerationDTO;
import com.pokedex.model.Generation;
import com.pokedex.service.GenerationService;

@RestController
@RequestMapping("/generation")
public class GenerationController {

    @Autowired
    private GenerationService generationService;

    @PostMapping
    public ResponseEntity<Generation> addGeneration(@RequestBody GenerationDTO generationDTO) {
        Generation generation = generationService.addGeneration(generationDTO);
        return ResponseEntity.ok(generation);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Generation> getGenerationById(@PathVariable int id) {
        Generation generation = generationService.getGenerationById(id);
        return ResponseEntity.ok(generation);
    }

    @GetMapping
    public ResponseEntity<List<Generation>> getAllGenerations() {
        List<Generation> generations = generationService.getAllGenerations();
        return ResponseEntity.ok(generations);
    }

    @GetMapping("/regions")
    public ResponseEntity<List<String>> getAllGenerationRegions() {
        List<String> generationNames = generationService.getAllGenerationRegions();
        return ResponseEntity.ok(generationNames);
    }

    @GetMapping("/year/{year}")
    public ResponseEntity<Generation> getGenerationsByYear(@PathVariable int year) {
        Generation generation = generationService.getGenerationByYear(year);
        return ResponseEntity.ok(generation);
    }

    @PutMapping("/{id}")
    public void updateGeneration(@PathVariable int id, @RequestBody GenerationDTO generationDTO) {
		generationService.updateGeneration(id, generationDTO);
	}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGeneration(@PathVariable int id) {
        generationService.deleteGeneration(id);
        return ResponseEntity.noContent().build();
    }
}
