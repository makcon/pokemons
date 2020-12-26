package github.makcon.pokemons.domain.service;

import github.makcon.pokemons.domain.model.Pokemon;
import github.makcon.pokemons.domain.port.PokemonsRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PokemonsService {

    private final PokemonsRepositoryPort repository;

    public List<Pokemon> findMostHeaviest(int size, String version) {
        return repository.findOrderedByWeight(size, version);
    }

    public List<Pokemon> findMostHighest(int size, String version) {
        return repository.findOrderedByHeight(size, version);
    }

    public List<Pokemon> findMostExperienced(int size, String version) {
        return repository.findOrderedByBaseExperience(size, version);
    }
}
