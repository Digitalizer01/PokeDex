package com.pokedex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pokedex.model.PokemonTypeRelation;

@Repository
public interface PokemonTypeRelationRepository extends JpaRepository<PokemonTypeRelation, Integer> {

	int findPokemonTypeRelationEffectiveness_percentageByPokemonTypeIdAndRelatedPokemonTypeId(int idtype,
			int idrelatedtype);

	int findPokemonTypeRelationEffectiveness_percentageByPokemonTypeNameAndRelatedPokemonTypeName(
			String namePokemonType, String nameRelatedPokemonType);

	List<PokemonTypeRelation> findAllByPokemonTypeNameAndEffectivenessPercentage(String nameType,
			int effectivenessPercentage);

}
