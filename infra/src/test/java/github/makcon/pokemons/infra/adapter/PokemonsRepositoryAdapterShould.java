package github.makcon.pokemons.infra.adapter;

import github.makcon.pokemons.domain.model.Pokemon;
import github.makcon.pokemons.infra.converter.PokemonEntityConverter;
import github.makcon.pokemons.infra.entity.PokemonEntity;
import github.makcon.pokemons.infra.entity.PokemonEntity.Field;
import github.makcon.pokemons.infra.repository.PokemonsJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.data.domain.Sort.Direction.DESC;

@ExtendWith(MockitoExtension.class)
class PokemonsRepositoryAdapterShould {

    private static final int GIVEN_LIMIT = 1;
    private static final String GIVEN_VERSION = UUID.randomUUID().toString();
    private static final PokemonEntity FOUND_POKEMON_ENTITY = mock(PokemonEntity.class);
    private static final Pokemon CONVERTED_POKEMON = mock(Pokemon.class);

    @InjectMocks
    private PokemonsRepositoryAdapter adapter;

    @Mock
    private PokemonEntityConverter converter;
    @Mock
    private PokemonsJpaRepository jpaRepository;

    @BeforeEach
    void setUp() {
        when(converter.toModel(any())).thenReturn(CONVERTED_POKEMON);
    }

    @Test
    void call_repository_find_by_weight_with_version_and_convert_to_model() {
        // given
        when(jpaRepository.findAllByVersionsContaining(any(Pageable.class), anyString()))
                .thenReturn(new PageImpl<>(List.of(FOUND_POKEMON_ENTITY)));

        // when
        var pokemons = adapter.findTopByWeight(GIVEN_LIMIT, GIVEN_VERSION);

        // then
        verify(jpaRepository).findAllByVersionsContaining(pageable(Field.WEIGHT), GIVEN_VERSION);
        assertThat(pokemons).isEqualTo(List.of(CONVERTED_POKEMON));
    }

    @Test
    void call_repository_find_by_weight_and_convert_to_model() {
        // given
        when(jpaRepository.findAll(any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(FOUND_POKEMON_ENTITY)));

        // when
        var pokemons = adapter.findTopByWeight(GIVEN_LIMIT, null);

        // then
        verify(jpaRepository).findAll(pageable(Field.WEIGHT));
        assertThat(pokemons).isEqualTo(List.of(CONVERTED_POKEMON));
    }

    @Test
    void call_repository_find_by_height_with_version_and_convert_to_model() {
        // given
        when(jpaRepository.findAllByVersionsContaining(any(Pageable.class), anyString()))
                .thenReturn(new PageImpl<>(List.of(FOUND_POKEMON_ENTITY)));

        // when
        var pokemons = adapter.findTopByHeight(GIVEN_LIMIT, GIVEN_VERSION);

        // then
        verify(jpaRepository).findAllByVersionsContaining(pageable(Field.HEIGHT), GIVEN_VERSION);
        assertThat(pokemons).isEqualTo(List.of(CONVERTED_POKEMON));
    }

    @Test
    void call_repository_find_by_height_and_convert_to_model() {
        // given
        when(jpaRepository.findAll(any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(FOUND_POKEMON_ENTITY)));

        // when
        var pokemons = adapter.findTopByHeight(GIVEN_LIMIT, null);

        // then
        verify(jpaRepository).findAll(pageable(Field.HEIGHT));
        assertThat(pokemons).isEqualTo(List.of(CONVERTED_POKEMON));
    }

    @Test
    void call_repository_find_by_base_experience_with_version_and_convert_to_model() {
        // given
        when(jpaRepository.findAllByVersionsContaining(any(Pageable.class), anyString()))
                .thenReturn(new PageImpl<>(List.of(FOUND_POKEMON_ENTITY)));

        // when
        var pokemons = adapter.findTopByBaseExperience(GIVEN_LIMIT, GIVEN_VERSION);

        // then
        verify(jpaRepository).findAllByVersionsContaining(pageable(Field.BASE_EXPERIENCE), GIVEN_VERSION);
        assertThat(pokemons).isEqualTo(List.of(CONVERTED_POKEMON));
    }

    @Test
    void call_repository_find_by_base_experience_and_convert_to_model() {
        // given
        when(jpaRepository.findAll(any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(FOUND_POKEMON_ENTITY)));

        // when
        var pokemons = adapter.findTopByBaseExperience(GIVEN_LIMIT, null);

        // then
        verify(jpaRepository).findAll(pageable(Field.BASE_EXPERIENCE));
        assertThat(pokemons).isEqualTo(List.of(CONVERTED_POKEMON));
    }

    private PageRequest pageable(String field) {
        return PageRequest.of(0, GIVEN_LIMIT, DESC, field);
    }
}