package com.pokedex.service;

import org.springframework.http.ResponseEntity;

import com.pokedex.dto.StatsDTO;
import com.pokedex.model.Stats;

public interface StatsService {

	// Create
	Stats addStats(StatsDTO statsDTO);

	// Get
	Stats getStatsById(int id);

	int getHpById(int id);

	int getAttackById(int id);

	int getDefenseById(int id);

	int getSpAttackById(int id);

	int getSpDefenseById(int id);

	int getSpeedById(int id);

	// Set
	ResponseEntity<Stats> setHpById(int id, int hp);

	ResponseEntity<Stats> setAttackById(int id, int attack);

	ResponseEntity<Stats> setDefenseById(int id, int defense);

	ResponseEntity<Stats> setSpAttackById(int id, int spAttack);

	ResponseEntity<Stats> setSpDefenseById(int id, int spDefense);

	ResponseEntity<Stats> setSpeedById(int id, int speed);

	// Update
	ResponseEntity<Stats> updateStats(int id, StatsDTO newStatsDTO);

	// Delete
	ResponseEntity<Stats> deleteStats(int id);

}
