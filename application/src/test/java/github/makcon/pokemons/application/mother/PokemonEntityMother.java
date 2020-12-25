package github.makcon.pokemons.application.mother;

import github.makcon.pokemons.infra.entity.PokemonEntity;
import lombok.experimental.UtilityClass;

import java.util.Random;
import java.util.UUID;

@UtilityClass
public class PokemonEntityMother {

    public PokemonEntity random() {
        return builder().build();
    }

    public PokemonEntity.PokemonEntityBuilder builder() {
        return PokemonEntity.builder()
                .id(randomInt())
                .name(randomString())
                .height(randomInt())
                .weight(randomInt())
                .baseExperience(randomInt());
    }

    private static int randomInt() {
        return new Random().nextInt();
    }

    private String randomString() {
        return UUID.randomUUID().toString();
    }
}
