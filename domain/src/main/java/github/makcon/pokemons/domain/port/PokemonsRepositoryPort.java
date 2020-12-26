package github.makcon.pokemons.domain.port;

import github.makcon.pokemons.domain.model.Pokemon;

import java.util.List;

public interface PokemonsRepositoryPort {

    List<Pokemon> findTopByWeight(int limit, String version);

    List<Pokemon> findTopByHeight(int limit, String version);

    List<Pokemon> findTopByBaseExperience(int limit, String version);
}
