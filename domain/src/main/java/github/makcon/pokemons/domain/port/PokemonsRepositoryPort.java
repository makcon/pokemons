package github.makcon.pokemons.domain.port;

import github.makcon.pokemons.domain.model.Pokemon;

import java.util.List;

public interface PokemonsRepositoryPort {

    List<Pokemon> findOrderedByWeight(int limit);

    List<Pokemon> findOrderedByHeight(int limit);

    List<Pokemon> findOrderedByBaseExperience(int limit);
}
