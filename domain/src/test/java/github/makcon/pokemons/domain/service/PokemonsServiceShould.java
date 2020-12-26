package github.makcon.pokemons.domain.service;

import github.makcon.pokemons.domain.model.Pokemon;
import github.makcon.pokemons.domain.port.PokemonsRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PokemonsServiceShould {

    private static final int GIVEN_SIZE = new Random().nextInt();
    private static final String GIVEN_VERSION = UUID.randomUUID().toString();
    private static final List<Pokemon> FOUND_POKEMONS = List.of(mock(Pokemon.class));

    @InjectMocks
    private PokemonsService service;

    @Mock
    private PokemonsRepositoryPort repository;

    @Test
    void request_repository_to_find_most_heaviest_pokemons() {
        // given
        when(repository.findOrderedByWeight(anyInt(), anyString())).thenReturn(FOUND_POKEMONS);

        // when
        var pokemons = service.findMostHeaviest(GIVEN_SIZE, GIVEN_VERSION);

        // then
        verify(repository).findOrderedByWeight(GIVEN_SIZE, GIVEN_VERSION);
        assertThat(pokemons).isEqualTo(FOUND_POKEMONS);
    }

    @Test
    void request_repository_to_find_most_highest_pokemons() {
        // given
        when(repository.findOrderedByHeight(anyInt(), anyString())).thenReturn(FOUND_POKEMONS);

        // when
        var pokemons = service.findMostHighest(GIVEN_SIZE, GIVEN_VERSION);

        // then
        verify(repository).findOrderedByHeight(GIVEN_SIZE, GIVEN_VERSION);
        assertThat(pokemons).isEqualTo(FOUND_POKEMONS);
    }

    @Test
    void request_repository_to_find_most_experienced_pokemons() {
        // given
        when(repository.findOrderedByBaseExperience(anyInt(), anyString())).thenReturn(FOUND_POKEMONS);

        // when
        var pokemons = service.findMostExperienced(GIVEN_SIZE, GIVEN_VERSION);

        // then
        verify(repository).findOrderedByBaseExperience(GIVEN_SIZE, GIVEN_VERSION);
        assertThat(pokemons).isEqualTo(FOUND_POKEMONS);
    }
}