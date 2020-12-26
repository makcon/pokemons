package github.makcon.pokemons.infra.external.pokemon_api.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import github.makcon.pokemons.infra.external.pokemon_api.dto.PokemonApiDto;
import github.makcon.pokemons.infra.external.pokemon_api.dto.PokemonApiResultsDto;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Builder
public class PokemonApiClient {

    private static final String POKEMON_LIST_ENDPOINT = "pokemon?offset=%d&limit=%d";

    private final String baseUrl;
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public CompletableFuture<PokemonApiResultsDto> fetchPokemons(int offset, int limit) {
        var url = baseUrl + String.format(POKEMON_LIST_ENDPOINT, offset, limit);

        return requestGET(url, PokemonApiResultsDto.class);

    }

    public CompletableFuture<PokemonApiDto> fetchPokemon(String url) {
        return requestGET(url, PokemonApiDto.class);
    }

    private <T> CompletableFuture<T> requestGET(String url, Class<T> clazz) {
        var httpRequest = HttpRequest.newBuilder(URI.create(url)).build();
        log.info("Requesting: {}", url);

        return httpClient.sendAsync(httpRequest, BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApplyAsync(it -> deserialize(it, clazz), httpClient.executor().orElseThrow());
    }

    private <T> T deserialize(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            // TODO handle the error properly, maybe need to skip failed item
            log.error("Failed to parse json: {}", json, e);
            throw new RuntimeException("Failed to parse json: " + json, e);
        }
    }
}
