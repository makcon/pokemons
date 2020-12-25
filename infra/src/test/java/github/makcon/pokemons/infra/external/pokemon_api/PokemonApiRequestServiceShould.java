package github.makcon.pokemons.infra.external.pokemon_api;

import github.makcon.pokemons.infra.external.pokemon_api.client.PokemonApiClient;
import github.makcon.pokemons.infra.mother.PokemonApiDtoMother;
import github.makcon.pokemons.infra.mother.PokemonApiResultsMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PokemonApiRequestServiceShould {

    private static final int GIVEN_OFFSET = new Random().nextInt();
    private static final int GIVEN_LIMIT = new Random().nextInt();

    @InjectMocks
    private PokemonApiRequestService service;

    @Mock
    private PokemonApiClient client;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(service, "resultsLimit", GIVEN_LIMIT);
    }

    @Test
    void request_client_to_get_list_and_then_each_individual_item() {
        // given
        var foundResultsDto = PokemonApiResultsMother.withRandomUrls(2);
        var foundApiDto1 = PokemonApiDtoMother.random();
        var foundApiDto2 = PokemonApiDtoMother.random();
        when(client.fetchPokemons(anyInt(), anyInt()))
                .thenReturn(CompletableFuture.completedFuture(foundResultsDto));
        when(client.fetchPokemon(anyString()))
                .thenReturn(CompletableFuture.completedFuture(foundApiDto1))
                .thenReturn(CompletableFuture.completedFuture(foundApiDto2));

        // when
        var pokemonApiDtos = service.request(GIVEN_OFFSET);

        // then
        verify(client).fetchPokemons(GIVEN_OFFSET, GIVEN_LIMIT);
        verify(client, times(2)).fetchPokemon(anyString());
        verify(client).fetchPokemon(foundResultsDto.getResults().get(0).getUrl());
        verify(client).fetchPokemon(foundResultsDto.getResults().get(1).getUrl());
        assertThat(pokemonApiDtos).isEqualTo(List.of(foundApiDto1, foundApiDto2));
    }
}