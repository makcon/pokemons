package github.makcon.pokemons.infra.mother;

import github.makcon.pokemons.infra.external.pokemon_api.dto.PokemonApiDto;
import lombok.experimental.UtilityClass;

import java.util.Random;
import java.util.UUID;

@UtilityClass
public class PokemonApiDtoMother {

    public PokemonApiDto random() {
        var dto = new PokemonApiDto();
        dto.setId(randomInt());
        dto.setHeight(randomInt());
        dto.setWeight(randomInt());
        dto.setBaseExperience(randomInt());
        dto.setName(randomString());

        return dto;
    }

    private static int randomInt() {
        return new Random().nextInt();
    }

    private String randomString() {
        return UUID.randomUUID().toString();
    }
}
