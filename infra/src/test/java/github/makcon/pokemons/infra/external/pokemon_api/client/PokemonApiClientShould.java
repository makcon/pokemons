package github.makcon.pokemons.infra.external.pokemon_api.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import github.makcon.pokemons.infra.external.pokemon_api.dto.PokemonApiDto;
import github.makcon.pokemons.infra.external.pokemon_api.dto.PokemonApiResultsDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PokemonApiClientShould {

    private PokemonApiClient client;

    private final String baseUrl = "http://url/";
    @Mock
    private HttpClient httpClient;
    @Mock
    private ObjectMapper objectMapper;

    @Captor
    private ArgumentCaptor<HttpRequest> httpRequestCaptor;
    private static final String FOUND_JSON = UUID.randomUUID().toString();

    @BeforeEach
    void setUp() {
        client = PokemonApiClient.builder()
                .baseUrl(baseUrl)
                .httpClient(httpClient)
                .objectMapper(objectMapper)
                .build();
    }

    @Test
    void fetch_pokemons_with_offset_and_size() throws Exception {
        // given
        var foundDto = mockClientRequest(PokemonApiResultsDto.class);

        // when
        var pokemons = client.fetchPokemons(1, 2).join();

        // then
        verifyClientCall(baseUrl + "pokemon?offset=1&limit=2");
        verify(objectMapper).readValue(FOUND_JSON, PokemonApiResultsDto.class);
        assertThat(pokemons).isEqualTo(foundDto);
    }

    @Test
    void fetch_pokemon_by_url() throws Exception {
        // given
        var foundDto = mockClientRequest(PokemonApiDto.class);

        // when
        var pokemons = client.fetchPokemon(baseUrl).join();

        // then
        verifyClientCall(baseUrl);
        verify(objectMapper).readValue(FOUND_JSON, PokemonApiDto.class);
        assertThat(pokemons).isEqualTo(foundDto);
    }

    private <T> T mockClientRequest(Class<T> dtoClass) throws JsonProcessingException {
        var httpResponse = mock(HttpResponse.class);
        var foundDto = mock(dtoClass);
        when(httpClient.sendAsync(any(), any()))
                .thenReturn(CompletableFuture.completedFuture(httpResponse));
        when(httpResponse.body()).thenReturn(FOUND_JSON);
        when(objectMapper.readValue(anyString(), any(Class.class))).thenReturn(foundDto);

        return foundDto;
    }

    private void verifyClientCall(String url) {
        verify(httpClient).sendAsync(httpRequestCaptor.capture(), eq(HttpResponse.BodyHandlers.ofString()));
        assertThat(httpRequestCaptor.getValue().uri().toString()).isEqualTo(url);
        assertThat(httpRequestCaptor.getValue().method()).isEqualTo("GET");
    }
}