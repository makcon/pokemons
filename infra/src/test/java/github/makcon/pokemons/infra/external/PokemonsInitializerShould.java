package github.makcon.pokemons.infra.external;

import github.makcon.pokemons.infra.converter.PokemonEntityConverter;
import github.makcon.pokemons.infra.entity.PokemonEntity;
import github.makcon.pokemons.infra.external.pokemon_api.PokemonApiRequestService;
import github.makcon.pokemons.infra.external.pokemon_api.dto.PokemonApiDto;
import github.makcon.pokemons.infra.repository.PokemonsJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PokemonsInitializerShould {

    @InjectMocks
    private PokemonsInitializer initializer;

    @Mock
    private PokemonApiRequestService requestService;
    @Mock
    private PokemonEntityConverter converter;
    @Mock
    private PokemonsJpaRepository jpaRepository;

    @Test
    void request_pokemons_api_service_2_times_and_save_entities() {
        // given
        var foundDto1 = mock(PokemonApiDto.class);
        var foundDto2 = mock(PokemonApiDto.class);
        var foundDto3 = mock(PokemonApiDto.class);
        var convertedEntity1 = mock(PokemonEntity.class);
        var convertedEntity2 = mock(PokemonEntity.class);
        var convertedEntity3 = mock(PokemonEntity.class);
        var expectedSavedEntities1 = List.of(convertedEntity1, convertedEntity2);
        var expectedSavedEntities2 = List.of(convertedEntity3);
        when(requestService.request(anyInt()))
                .thenReturn(List.of(foundDto1, foundDto2))
                .thenReturn(List.of(foundDto3))
                .thenReturn(List.of());
        when(converter.fromApiDto(foundDto1)).thenReturn(convertedEntity1);
        when(converter.fromApiDto(foundDto2)).thenReturn(convertedEntity2);
        when(converter.fromApiDto(foundDto3)).thenReturn(convertedEntity3);
        when(jpaRepository.saveAll(anyIterable()))
                .thenReturn(expectedSavedEntities1)
                .thenReturn(expectedSavedEntities2);

        // when
        initializer.init();

        // then
        verify(requestService, times(3)).request(anyInt());
        verify(requestService).request(0);
        verify(requestService).request(2);
        verify(requestService).request(3);
        verify(jpaRepository, times(2)).saveAll(anyIterable());
        verify(jpaRepository).saveAll(expectedSavedEntities1);
        verify(jpaRepository).saveAll(expectedSavedEntities2);
    }
}