package com.pokedex.service.impl;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pokedex.dto.StatsDTO;
import com.pokedex.exception.ResourceNotFoundException;
import com.pokedex.model.Stats;
import com.pokedex.repository.StatsRepository;
import com.pokedex.service.StatsService;

@Service
public class StatsServiceImpl implements StatsService {

	@Autowired
	private StatsRepository statsRepository;

	@Override
	public Stats addStats(StatsDTO statsDTO) {
		Stats stats = new Stats(statsDTO.getHp(), statsDTO.getAttack(), statsDTO.getDefense(),
				statsDTO.getSpecialAttack(), statsDTO.getSpecialDefense(), statsDTO.getSpeed());
		return statsRepository.saveAndFlush(stats);
	}

	@Override
	public Stats getStatsById(int id) {
		return statsRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Stats not found. Id: " + id));
	}

	@Override
	public int getHpById(int id) {
		return getStatsById(id).getHp();
	}

	@Override
	public int getAttackById(int id) {
		return getStatsById(id).getAttack();
	}

	@Override
	public int getDefenseById(int id) {
		return getStatsById(id).getDefense();
	}

	@Override
	public int getSpAttackById(int id) {
		return getStatsById(id).getSpecialAttack();
	}

	@Override
	public int getSpDefenseById(int id) {
		return getStatsById(id).getSpecialDefense();
	}

	@Override
	public int getSpeedById(int id) {
		return getStatsById(id).getSpeed();
	}

	@Override
	public ResponseEntity<Stats> setHpById(int id, int hp) {
		return updateStat(id, stats -> stats.setHp(hp));
	}

	@Override
	public ResponseEntity<Stats> setAttackById(int id, int attack) {
		return updateStat(id, stats -> stats.setAttack(attack));
	}

	@Override
	public ResponseEntity<Stats> setDefenseById(int id, int defense) {
		return updateStat(id, stats -> stats.setDefense(defense));
	}

	@Override
	public ResponseEntity<Stats> setSpAttackById(int id, int spAttack) {
		return updateStat(id, stats -> stats.setSpecialAttack(spAttack));
	}

	@Override
	public ResponseEntity<Stats> setSpDefenseById(int id, int spDefense) {
		return updateStat(id, stats -> stats.setSpecialDefense(spDefense));
	}

	@Override
	public ResponseEntity<Stats> setSpeedById(int id, int speed) {
		return updateStat(id, stats -> stats.setSpeed(speed));
	}

	@Override
	public ResponseEntity<Stats> updateStats(int id, StatsDTO newStatsDTO) {
		Stats stats = getStatsById(id);

		if (stats != null) {
			stats.setHp(newStatsDTO.getHp());
			stats.setAttack(newStatsDTO.getAttack());
			stats.setDefense(newStatsDTO.getDefense());
			stats.setSpecialAttack(newStatsDTO.getSpecialAttack());
			stats.setSpecialDefense(newStatsDTO.getSpecialDefense());
			stats.setSpeed(newStatsDTO.getSpeed());

			statsRepository.saveAndFlush(stats);
		} else {
			throw new RuntimeException("Stats not found. Id: " + id);
		}

		return ResponseEntity.ok(stats);
	}

	@Override
	public ResponseEntity<Stats> deleteStats(int id) {
		getStatsById(id);
		statsRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	private ResponseEntity<Stats> updateStat(int id, Consumer<Stats> updater) {
		Stats stats = getStatsById(id);
		updater.accept(stats);
		statsRepository.saveAndFlush(stats);
		return ResponseEntity.ok(stats);
	}

}
