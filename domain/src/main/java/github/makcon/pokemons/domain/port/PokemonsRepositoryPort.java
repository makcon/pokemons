package github.makcon.pokemons.domain.port;

import github.makcon.pokemons.domain.model.Pokemon;

import java.util.List;

public interface PokemonsRepositoryPort {

    List<Pokemon> findOrderedByWeight(int limit, String version);

    List<Pokemon> findOrderedByHeight(int limit, String version);

    List<Pokemon> findOrderedByBaseExperience(int limit, String version);
}
