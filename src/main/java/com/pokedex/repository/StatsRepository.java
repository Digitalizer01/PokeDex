package com.pokedex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pokedex.model.Stats;

@Repository
public interface StatsRepository extends JpaRepository<Stats, Integer> {
}
