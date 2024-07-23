package com.pokedex.service.impl;

import java.util.List;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pokedex.dto.PokemonDTO;
import com.pokedex.exception.ResourceNotFoundException;
import com.pokedex.model.Generation;
import com.pokedex.model.Pokemon;
import com.pokedex.model.PokemonType;
import com.pokedex.model.Stats;
import com.pokedex.repository.PokemonRepository;
import com.pokedex.service.GenerationService;
import com.pokedex.service.PokemonService;
import com.pokedex.service.PokemonTypeService;
import com.pokedex.service.StatsService;

@Service
public class PokemonServiceImpl implements PokemonService {

	@Autowired
	private PokemonTypeService pokemonTypeService;

	@Autowired
	private StatsService statsService;

	@Autowired
	private GenerationService generationService;

	@Autowired
	private PokemonRepository pokemonRepository;

	@Override
	public Pokemon addPokemon(PokemonDTO pokemonDTO) {
		PokemonType type1 = pokemonTypeService.getPokemonTypeByName(pokemonDTO.getNamePokemonType1());
		PokemonType type2 = pokemonDTO.getNamePokemonType2() != null
				? pokemonTypeService.getPokemonTypeByName(pokemonDTO.getNamePokemonType2())
				: null;

		Stats stats = new Stats(pokemonDTO.getStats().getHp(), pokemonDTO.getStats().getAttack(),
				pokemonDTO.getStats().getDefense(), pokemonDTO.getStats().getSpecialAttack(),
				pokemonDTO.getStats().getSpecialDefense(), pokemonDTO.getStats().getSpeed());

		Generation generation = generationService.getGenerationById(pokemonDTO.getIdGeneration());

		Pokemon pokemon = new Pokemon();
		pokemon.setIdPokedex(pokemonDTO.getIdPokedex());
		pokemon.setName(pokemonDTO.getName());
		pokemon.setPokemonType1(type1);
		pokemon.setPokemonType2(type2);
		pokemon.setDescription(pokemonDTO.getDescription());
		pokemon.setStats(stats);
		pokemon.setGeneration(generation);
		pokemon.setLegendary(pokemonDTO.isLegendary());

		return pokemonRepository.saveAndFlush(pokemon);
	}

	@Override
	public Pokemon getPokemonById(int id) {
		return pokemonRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Pokemon not found. Id: " + id));
	}

	@Override
	public Pokemon getPokemonByIdPokedex(int idpokedex) {
		return pokemonRepository.findByIdPokedex(idpokedex)
				.orElseThrow(() -> new ResourceNotFoundException("Pokemon not found. IdPokedex: " + idpokedex));
	}

	@Override
	public Pokemon getPokemonByName(String name) {
		return pokemonRepository.findByName(name)
				.orElseThrow(() -> new ResourceNotFoundException("Pokemon not found. Name: " + name));
	}

	@Override
	public List<Pokemon> getAllPokemon() {
		return pokemonRepository.findAll();
	}

	@Override
	public PokemonType getPokemonType1ById(int id) {
		return getPokemonById(id).getPokemonType1();
	}

	@Override
	public PokemonType getPokemonType1ByIdPokedex(int idpokedex) {
		return getPokemonByIdPokedex(idpokedex).getPokemonType1();
	}

	@Override
	public PokemonType getPokemonType1ByName(String name) {
		return getPokemonByName(name).getPokemonType1();
	}

	@Override
	public PokemonType getPokemonType2ById(int id) {
		return getPokemonById(id).getPokemonType2();
	}

	@Override
	public PokemonType getPokemonType2ByIdPokedex(int idpokedex) {
		return getPokemonByIdPokedex(idpokedex).getPokemonType2();
	}

	@Override
	public PokemonType getPokemonType2ByName(String name) {
		return getPokemonByName(name).getPokemonType2();
	}

	@Override
	public List<Pokemon> getAllPokemonByPokemonType1Name(String namePokemonType1) {
		return pokemonRepository.findAllByPokemonType1Name(namePokemonType1);
	}

	@Override
	public List<Pokemon> getAllPokemonByPokemonType2Name(String namePokemonType2) {
		return pokemonRepository.findAllByPokemonType2Name(namePokemonType2);
	}

	@Override
	public List<Pokemon> getAllPokemonByPokemonType1NameAndPokemonType2Name(String namePokemonType1,
			String namePokemonType2) {
		return pokemonRepository.findAllByPokemonType1NameAndPokemonType2Name(namePokemonType1, namePokemonType2);
	}

	@Override
	public String getDescriptionById(int id) {
		return getPokemonById(id).getDescription();
	}

	@Override
	public String getDescriptionByIdPokedex(int idpokedex) {
		return getPokemonByIdPokedex(idpokedex).getDescription();
	}

	@Override
	public String getDescriptionByName(String name) {
		return getPokemonByName(name).getDescription();
	}

	@Override
	public Stats getStatsById(int id) {
		return getPokemonById(id).getStats();
	}

	@Override
	public Stats getStatsByIdPokedex(int idpokedex) {
		return getPokemonByIdPokedex(idpokedex).getStats();
	}

	@Override
	public Stats getStatsByName(String name) {
		return getPokemonByName(name).getStats();
	}

	@Override
	public Generation getGenerationById(int id) {
		return getPokemonById(id).getGeneration();
	}

	@Override
	public Generation getGenerationByIdPokedex(int idpokedex) {
		return getPokemonByIdPokedex(idpokedex).getGeneration();
	}

	@Override
	public Generation getGenerationByName(String name) {
		return getPokemonByName(name).getGeneration();
	}

	@Override
	public Boolean isLegendaryById(int id) {
		return getPokemonById(id).isLegendary();
	}

	@Override
	public Boolean isLegendaryByIdPokedex(int idpokedex) {
		return getPokemonByIdPokedex(idpokedex).isLegendary();
	}

	@Override
	public Boolean isLegendaryByName(String name) {
		return getPokemonByName(name).isLegendary();
	}

	@Override
	public List<Pokemon> getAllPokemonByGeneration(int idgeneration) {
		return pokemonRepository.findAllByGenerationId(idgeneration);
	}

	@Override
	public List<Pokemon> getAllPokemonByGenerationNumber(int number) {
		return pokemonRepository.findAllByGenerationNumber(number);
	}

	@Override
	public List<Pokemon> getAllPokemonByGenerationRegion(String region) {
		return pokemonRepository.findAllByGenerationRegion(region);
	}

	@Override
	public List<Pokemon> getAllPokemonByGenerationYear(int year) {
		return pokemonRepository.findAllByGenerationYear(year);
	}

	@Override
	public List<Pokemon> getAllPokemonByLegendary(boolean legendary) {
		return pokemonRepository.findAllByLegendary(legendary);
	}

	@Override
	public ResponseEntity<Pokemon> setIdPokedexById(int id, int idpokedex) {
		return updatePokemonById(id, pokemon -> pokemon.setIdPokedex(idpokedex));
	}

	@Override
	public ResponseEntity<Pokemon> setIdPokedexByName(String name, int idpokedex) {
		return updatePokemonByName(name, pokemon -> pokemon.setIdPokedex(idpokedex));
	}

	@Override
	public ResponseEntity<Pokemon> setNameById(int id, String name) {
		return updatePokemonById(id, pokemon -> pokemon.setName(name));
	}

	@Override
	public ResponseEntity<Pokemon> setNameByIdPokedex(String name, int idpokedex) {
		return updatePokemonByIdPokedex(idpokedex, pokemon -> pokemon.setName(name));
	}

	@Override
	public ResponseEntity<Pokemon> setNameByNewName(String oldname, String newname) {
		return updatePokemonByName(oldname, pokemon -> pokemon.setName(newname));
	}

	@Override
	public ResponseEntity<Pokemon> setPokemonType1ById(int id, String nameType1) {
		PokemonType type1 = pokemonTypeService.getPokemonTypeByName(nameType1);

		return updatePokemonById(id, pokemon -> pokemon.setPokemonType1(type1));
	}

	@Override
	public ResponseEntity<Pokemon> setPokemonType1ByIdPokedex(int idpokedex, String nameType1) {
		PokemonType type1 = pokemonTypeService.getPokemonTypeByName(nameType1);

		return updatePokemonByIdPokedex(idpokedex, pokemon -> pokemon.setPokemonType1(type1));
	}

	@Override
	public ResponseEntity<Pokemon> setPokemonType1ByName(String name, String nameType1) {
		PokemonType type1 = pokemonTypeService.getPokemonTypeByName(nameType1);

		return updatePokemonByName(name, pokemon -> pokemon.setPokemonType1(type1));
	}

	@Override
	public ResponseEntity<Pokemon> setPokemonType2ById(int id, String nameType2) {
		PokemonType type2 = pokemonTypeService.getPokemonTypeByName(nameType2);

		return updatePokemonById(id, pokemon -> pokemon.setPokemonType2(type2));
	}

	@Override
	public ResponseEntity<Pokemon> setPokemonType2ByIdPokedex(int idpokedex, String nameType2) {
		PokemonType type2 = pokemonTypeService.getPokemonTypeByName(nameType2);

		return updatePokemonByIdPokedex(idpokedex, pokemon -> pokemon.setPokemonType2(type2));
	}

	@Override
	public ResponseEntity<Pokemon> setPokemonType2ByName(String name, String nameType2) {
		PokemonType type2 = pokemonTypeService.getPokemonTypeByName(nameType2);

		return updatePokemonByName(name, pokemon -> pokemon.setPokemonType2(type2));
	}

	@Override
	public ResponseEntity<Pokemon> setPokemonType1AndType2ById(int id, String nameType1, String nameType2) {
		Pokemon pokemon = getPokemonById(id);

		if (pokemon != null) {
			PokemonType type1 = pokemonTypeService.getPokemonTypeByName(nameType1);
			PokemonType type2 = pokemonTypeService.getPokemonTypeByName(nameType2);
			pokemon.setPokemonType1(type1);
			pokemon.setPokemonType2(type2);

			pokemonRepository.saveAndFlush(pokemon);
		}

		return ResponseEntity.ok(pokemon);
	}

	@Override
	public ResponseEntity<Pokemon> setPokemonType1AndType2ByIdPokedex(int idpokedex, String nameType1,
			String nameType2) {
		Pokemon pokemon = getPokemonByIdPokedex(idpokedex);

		if (pokemon != null) {
			PokemonType type1 = pokemonTypeService.getPokemonTypeByName(nameType1);
			PokemonType type2 = pokemonTypeService.getPokemonTypeByName(nameType2);
			pokemon.setPokemonType1(type1);
			pokemon.setPokemonType2(type2);

			pokemonRepository.saveAndFlush(pokemon);
		}

		return ResponseEntity.ok(pokemon);
	}

	@Override
	public ResponseEntity<Pokemon> setPokemonType1AndType2ByName(String name, String nameType1, String nameType2) {
		Pokemon pokemon = getPokemonByName(name);

		if (pokemon != null) {
			PokemonType type1 = pokemonTypeService.getPokemonTypeByName(nameType1);
			PokemonType type2 = pokemonTypeService.getPokemonTypeByName(nameType2);
			pokemon.setPokemonType1(type1);
			pokemon.setPokemonType2(type2);

			pokemonRepository.saveAndFlush(pokemon);
		}

		return ResponseEntity.ok(pokemon);
	}

	@Override
	public ResponseEntity<Pokemon> setDescriptionById(int id, String description) {
		return updatePokemonById(id, pokemon -> pokemon.setDescription(description));
	}

	@Override
	public ResponseEntity<Pokemon> setDescriptionByIdPokedex(int idpokedex, String description) {
		return updatePokemonByIdPokedex(idpokedex, pokemon -> pokemon.setDescription(description));
	}

	@Override
	public ResponseEntity<Pokemon> setDescriptionByName(String name, String description) {
		return updatePokemonByName(name, pokemon -> pokemon.setDescription(description));
	}

	@Override
	public ResponseEntity<Pokemon> setStatsById(int id, int idstats) {
		Stats stats = statsService.getStatsById(idstats);

		return updatePokemonById(id, pokemon -> pokemon.setStats(stats));
	}

	@Override
	public ResponseEntity<Pokemon> setStatsByIdPokedex(int idpokedex, int idstats) {
		Stats stats = statsService.getStatsById(idstats);

		return updatePokemonByIdPokedex(idpokedex, pokemon -> pokemon.setStats(stats));
	}

	@Override
	public ResponseEntity<Pokemon> setStatsByName(String name, int idstats) {
		Stats stats = statsService.getStatsById(idstats);

		return updatePokemonByName(name, pokemon -> pokemon.setStats(stats));
	}

	@Override
	public ResponseEntity<Pokemon> setGenerationById(int id, int idgeneration) {
		Generation generation = generationService.getGenerationById(idgeneration);

		return updatePokemonById(id, pokemon -> pokemon.setGeneration(generation));
	}

	@Override
	public ResponseEntity<Pokemon> setGenerationByIdPokedex(int idpokedex, int idgeneration) {
		Generation generation = generationService.getGenerationById(idgeneration);

		return updatePokemonByIdPokedex(idpokedex, pokemon -> pokemon.setGeneration(generation));
	}

	@Override
	public ResponseEntity<Pokemon> setGenerationByName(String name, int idgeneration) {
		Generation generation = generationService.getGenerationById(idgeneration);

		return updatePokemonByName(name, pokemon -> pokemon.setGeneration(generation));
	}

	@Override
	public ResponseEntity<Pokemon> setLegendaryById(int id, boolean legendary) {
		return updatePokemonById(id, pokemon -> pokemon.setLegendary(legendary));
	}

	@Override
	public ResponseEntity<Pokemon> setLegendaryByIdPokedex(int idpokedex, boolean legendary) {
		return updatePokemonByIdPokedex(idpokedex, pokemon -> pokemon.setLegendary(legendary));
	}

	@Override
	public ResponseEntity<Pokemon> setLegendaryByName(String name, boolean legendary) {
		return updatePokemonByName(name, pokemon -> pokemon.setLegendary(legendary));
	}

	@Override
	public ResponseEntity<Pokemon> updatePokemonById(int id, PokemonDTO newPokemonDTO) {
		Pokemon pokemon = getPokemonById(id);

		if (pokemon != null) {
			pokemon = updatePokemon(pokemon, newPokemonDTO);
		}

		return ResponseEntity.ok(pokemon);
	}

	@Override
	public ResponseEntity<Pokemon> updatePokemonByIdPokedex(int idpokedex, PokemonDTO newPokemonDTO) {
		Pokemon pokemon = getPokemonByIdPokedex(idpokedex);

		if (pokemon != null) {
			pokemon = updatePokemon(pokemon, newPokemonDTO);
		}

		return ResponseEntity.ok(pokemon);
	}

	@Override
	public ResponseEntity<Pokemon> updatePokemonByName(String name, PokemonDTO newPokemonDTO) {
		Pokemon pokemon = getPokemonByName(name);

		if (pokemon != null) {
			pokemon = updatePokemon(pokemon, newPokemonDTO);
		}

		return ResponseEntity.ok(pokemon);
	}

	private Pokemon updatePokemon(Pokemon pokemon, PokemonDTO newPokemonDTO) {
		pokemon.setName(newPokemonDTO.getName());
		pokemon.setPokemonType1(pokemonTypeService.getPokemonTypeByName(newPokemonDTO.getNamePokemonType1()));
		pokemon.setPokemonType2(pokemonTypeService.getPokemonTypeByName(newPokemonDTO.getNamePokemonType2()));
		pokemon.setDescription(newPokemonDTO.getDescription());

		Stats stats = new Stats(newPokemonDTO.getStats().getHp(), newPokemonDTO.getStats().getAttack(),
				newPokemonDTO.getStats().getDefense(), newPokemonDTO.getStats().getSpecialAttack(),
				newPokemonDTO.getStats().getSpecialDefense(), newPokemonDTO.getStats().getSpeed());

		pokemon.setStats(stats);
		pokemon.setGeneration(generationService.getGenerationById(newPokemonDTO.getIdGeneration()));
		pokemon.setLegendary(newPokemonDTO.isLegendary());

		return pokemon;
	}

	private ResponseEntity<Pokemon> updatePokemonById(int id, Consumer<Pokemon> updater) {
		Pokemon pokemon = getPokemonById(id);
		updater.accept(pokemon);
		pokemonRepository.saveAndFlush(pokemon);
		return ResponseEntity.ok(pokemon);
	}

	private ResponseEntity<Pokemon> updatePokemonByIdPokedex(int idpokedex, Consumer<Pokemon> updater) {
		Pokemon pokemon = getPokemonByIdPokedex(idpokedex);
		updater.accept(pokemon);
		pokemonRepository.saveAndFlush(pokemon);
		return ResponseEntity.ok(pokemon);
	}

	private ResponseEntity<Pokemon> updatePokemonByName(String name, Consumer<Pokemon> updater) {
		Pokemon pokemon = getPokemonByName(name);
		updater.accept(pokemon);
		pokemonRepository.saveAndFlush(pokemon);
		return ResponseEntity.ok(pokemon);
	}

	@Override
	public ResponseEntity<Pokemon> deletePokemonById(int id) {
		getPokemonById(id);
		pokemonRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Pokemon> deletePokemonByIdPokedex(int idpokedex) {
		getPokemonByIdPokedex(idpokedex);
		pokemonRepository.deleteByIdPokedex(idpokedex);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Pokemon> deletePokemonByName(String name) {
		getPokemonByName(name);
		pokemonRepository.deleteByName(name);
		return ResponseEntity.noContent().build();
	}

}
