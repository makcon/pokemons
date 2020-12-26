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
