package com.webflux.pokedex.controllers;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.webflux.pokedex.models.Pokemon;
import com.webflux.pokedex.models.PokemonEvent;
import com.webflux.pokedex.repositories.PokemonRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/pokemon")
public class PokemonController {

	@Autowired
	private PokemonRepository repo;

	@GetMapping
	public Flux<Pokemon> getAll() {
		return repo.findAll();
	}

	@GetMapping("/{id}")
	public Mono<ResponseEntity<Pokemon>> getPokemon(@PathVariable String id) {
		return repo.findById(id).map(pokemon -> ResponseEntity.ok(pokemon))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Pokemon> savePokemon(@RequestBody Pokemon pokemon) {
		return repo.save(pokemon);
	}

	@PutMapping("{id}")
	public Mono<ResponseEntity<Pokemon>> updatePokemon(@PathVariable(value = "id") String id,
			@RequestBody Pokemon pokemon) {
		return repo.findById(id).flatMap(existingPokemon -> {
			existingPokemon.setName(pokemon.getName());
			existingPokemon.setCategory(pokemon.getCategory());
			existingPokemon.setSkill(pokemon.getSkill());
			existingPokemon.setWeight(pokemon.getWeight());
			return repo.save(existingPokemon);
		}).map(updatePokemon -> ResponseEntity.ok(updatePokemon)).defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@DeleteMapping("{id}")
	public Mono<ResponseEntity<Object>> deletePokemon(@PathVariable(value="id")String id) {
		return repo.findById(id)
					.flatMap(existingPokemon -> 
								repo.delete(existingPokemon)
									.then(Mono.just(ResponseEntity.ok().build())))
					.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping
	public Mono<Void> deleteAllPokemon() {
		return repo.deleteAll();
	}
	
	@GetMapping(value="/events", produces=MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<PokemonEvent> getPokemonEvents() {
		return Flux.interval(Duration.ofSeconds(5))
				.map(val -> new PokemonEvent(val, "Pokemonzitossss"));
	}
}
