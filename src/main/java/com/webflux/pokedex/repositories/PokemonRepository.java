package com.webflux.pokedex.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.webflux.pokedex.models.Pokemon;

public interface PokemonRepository extends ReactiveMongoRepository<Pokemon, String> {

}
