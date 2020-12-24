package github.makcon.pokemons.infra.mother;

import github.makcon.pokemons.domain.model.Pokemon;
import github.makcon.pokemons.infra.entity.PokemonEntity;
import lombok.experimental.UtilityClass;

import java.util.Random;
import java.util.UUID;

@UtilityClass
public class PokemonMother {

    public PokemonEntity randomEntity() {
        return entityBuilder().build();
    }

    public PokemonEntity.PokemonEntityBuilder entityBuilder() {
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
