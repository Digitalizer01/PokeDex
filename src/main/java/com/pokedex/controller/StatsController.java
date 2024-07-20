package com.pokedex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pokedex.dto.StatsDTO;
import com.pokedex.model.Stats;
import com.pokedex.service.StatsService;

@RestController
@RequestMapping("/stats")
public class StatsController {
	@Autowired
	private StatsService statsService;

	@PostMapping()
	public Stats addGeneration(@RequestBody StatsDTO statsDTO) {
		return statsService.addStats(statsDTO);
	}

	@GetMapping("/{id}")
	public Stats getStatsById(@PathVariable int id) {
		return statsService.getStatsById(id);
	}

	@GetMapping("/hp/{id}")
	public int getHpById(@PathVariable int id) {
		return statsService.getHpById(id);
	}

	@GetMapping("/attack/{id}")
	public int getAttackById(@PathVariable int id) {
		return statsService.getAttackById(id);
	}

	@GetMapping("/defense/{id}")
	public int getDefenseById(@PathVariable int id) {
		return statsService.getDefenseById(id);
	}

	@GetMapping("/spattack/{id}")
	public int getSpAttackById(@PathVariable int id) {
		return statsService.getSpAttackById(id);
	}

	@GetMapping("/spdefense/{id}")
	public int getSpDefenseById(@PathVariable int id) {
		return statsService.getSpDefenseById(id);
	}

	@GetMapping("/speed/{id}")
	public int getSpeedById(@PathVariable int id) {
		return statsService.getSpeedById(id);
	}

	@PutMapping("/hp/{id}")
	public void setHpById(@PathVariable int id, @RequestBody int hp) {
		statsService.setHpById(id, hp);
	}

	@PutMapping("/attack/{id}")
	public void setAttackById(@PathVariable int id, @RequestBody int attack) {
		statsService.setAttackById(id, attack);
	}

	@PutMapping("/defense/{id}")
	public void setDefenseById(@PathVariable int id, @RequestBody int defense) {
		statsService.setDefenseById(id, defense);
	}

	@PutMapping("/spattack/{id}")
	public void setSpAttackById(@PathVariable int id, @RequestBody int spAttack) {
		statsService.setSpAttackById(id, spAttack);
	}

	@PutMapping("/spdefense/{id}")
	public void setSpDefenseById(@PathVariable int id, @RequestBody int spDefense) {
		statsService.setSpDefenseById(id, spDefense);
	}

	@PutMapping("/speed/{id}")
	public void setSpeedById(@PathVariable int id, @RequestBody int speed) {
		statsService.setSpeedById(id, speed);
	}

	@PutMapping("/{id}")
	public void updateStats(@PathVariable int id, @RequestBody StatsDTO newStatsDTO) {
		statsService.updateStats(id, newStatsDTO);
	}

	@DeleteMapping("/{id}")
	public void deleteStats(@PathVariable int id) {
		statsService.deleteStats(id);
	}
}
