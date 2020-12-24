package github.makcon.pokemons.domain.service;

import github.makcon.pokemons.domain.model.Pokemon;
import github.makcon.pokemons.domain.port.PokemonRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PokemonsService {

    private final PokemonRepositoryPort repository;

    public List<Pokemon> findMostHeaviest(int size) {
        return repository.findOrderedByWeight(size);
    }

    public List<Pokemon> findMostHighest(int size) {
        return repository.findOrderedByHeight(size);
    }

    public List<Pokemon> findMostExperienced(int size) {
        return repository.findOrderedByBaseExperience(size);
    }
}
