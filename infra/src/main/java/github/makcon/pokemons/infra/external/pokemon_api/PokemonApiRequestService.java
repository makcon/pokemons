package github.makcon.pokemons.infra.external.pokemon_api;

import github.makcon.pokemons.infra.external.pokemon_api.client.PokemonApiClient;
import github.makcon.pokemons.infra.external.pokemon_api.dto.PokemonApiDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PokemonApiRequestService {

    private final PokemonApiClient client;

    @Value("${pokemons-api.results.limit}")
    private int resultsLimit;

    /**
     * TODO filter
     * - We only want Pokémons of "red version". You can find this information on the section "game_indices" for each Pokémon:
     *      - version_name = "red"
     *      - version_url = "https://pokeapi.co/api/v2/version/1/"
     */
    public List<PokemonApiDto> request(int offset) {
        return client.fetchPokemons(offset, resultsLimit)
                .join()
                .getResults()
                .stream()
                .map(it -> client.fetchPokemon(it.getUrl()))
                .collect(Collectors.toList())
                .stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }
}
