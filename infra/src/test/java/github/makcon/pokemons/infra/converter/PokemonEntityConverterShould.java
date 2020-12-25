package github.makcon.pokemons.infra.converter;

import github.makcon.pokemons.infra.mother.PokemonApiDtoMother;
import github.makcon.pokemons.infra.mother.PokemonEntityMother;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class PokemonEntityConverterShould {

    @InjectMocks
    private PokemonEntityConverter converter;

    @Test
    void convert_to_model() {
        // given
        var givenEntity = PokemonEntityMother.random();

        // when
        var model = converter.toModel(givenEntity);

        // then
        assertThat(model.getId()).isEqualTo(givenEntity.getId());
        assertThat(model.getHeight()).isEqualTo(givenEntity.getHeight());
        assertThat(model.getWeight()).isEqualTo(givenEntity.getWeight());
        assertThat(model.getBaseExperience()).isEqualTo(givenEntity.getBaseExperience());
        assertThat(model.getName()).isEqualTo(givenEntity.getName());
    }

    @Test
    void convert_from_api_dto() {
        // given
        var givenDto = PokemonApiDtoMother.random();

        // when
        var entity = converter.fromApiDto(givenDto);

        // then
        assertThat(entity.getId()).isNull();
        assertThat(entity.getExternalId()).isEqualTo(givenDto.getId());
        assertThat(entity.getHeight()).isEqualTo(givenDto.getHeight());
        assertThat(entity.getWeight()).isEqualTo(givenDto.getWeight());
        assertThat(entity.getBaseExperience()).isEqualTo(givenDto.getBaseExperience());
        assertThat(entity.getName()).isEqualTo(givenDto.getName());
    }
}