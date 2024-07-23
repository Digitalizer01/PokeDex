package com.pokedex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pokedex.dto.PokemonTypeRelationDTO;
import com.pokedex.model.PokemonTypeRelation;
import com.pokedex.service.PokemonTypeRelationService;

@RestController
@RequestMapping("/pokemontyperelation")
public class PokemonTypeRelationController {

    @Autowired
    private PokemonTypeRelationService pokemonTypeRelationService;

    @PostMapping
    public PokemonTypeRelation addPokemonTypeRelation(@RequestBody PokemonTypeRelationDTO pokemonTypeRelationDTO) {
        return pokemonTypeRelationService.addPokemonTypeRelation(pokemonTypeRelationDTO);
    }

    @GetMapping("/{id}")
    public PokemonTypeRelation getPokemonTypeRelationById(@PathVariable int id) {
        return pokemonTypeRelationService.getPokemonTypeRelationById(id);
    }

    @GetMapping("/effectiveness/{namePokemonType}/{nameRelatedPokemonType}")
    public int getEffectivenessPercentage(@PathVariable String namePokemonType, @PathVariable String nameRelatedPokemonType) {
        return pokemonTypeRelationService.getEffectivenessPercentage(namePokemonType, nameRelatedPokemonType);
    }

    @GetMapping("/effectiveness/{namePokemonType}/{effectivenessPercentage}")
    public List<PokemonTypeRelation> getPokemonTypeRelationByEffectivenessPercentage(@PathVariable String namePokemonType, @PathVariable int effectivenessPercentage) {
        return pokemonTypeRelationService.getPokemonTypeRelationByEffectivenessPercentage(namePokemonType, effectivenessPercentage);
    }

    @GetMapping
    public List<PokemonTypeRelation> getAllPokemonTypeRelations() {
        return pokemonTypeRelationService.getAllPokemonTypeRelations();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PokemonTypeRelation> updatePokemonTypeRelationById(@PathVariable int id, @RequestBody PokemonTypeRelationDTO newPokemonTypeRelationDTO) {
        return pokemonTypeRelationService.updatePokemonTypeRelationById(id, newPokemonTypeRelationDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PokemonTypeRelation> deletePokemonTypeRelationById(@PathVariable int id) {
        return pokemonTypeRelationService.deletePokemonTypeRelationById(id);
    }
}
