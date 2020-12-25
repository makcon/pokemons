package github.makcon.pokemons.application.converter;

import github.makcon.pokemons.application.mother.PokemonMother;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class PokemonDtoConverterShould {

    @InjectMocks
    private PokemonDtoConverter converter;

    @Test
    void convert_from_models() {
        // given
        var givenPokemon = PokemonMother.random();

        // when
        var dtos = converter.fromModels(List.of(givenPokemon));

        // then
        assertThat(dtos).hasSize(1);
        var dto = dtos.get(0);
        assertThat(dto.getId()).isEqualTo(givenPokemon.getId());
        assertThat(dto.getHeight()).isEqualTo(givenPokemon.getHeight());
        assertThat(dto.getWeight()).isEqualTo(givenPokemon.getWeight());
        assertThat(dto.getBaseExperience()).isEqualTo(givenPokemon.getBaseExperience());
        assertThat(dto.getName()).isEqualTo(givenPokemon.getName());
    }
}