package github.makcon.pokemons.infra.mother;

import github.makcon.pokemons.infra.entity.PokemonEntity;
import lombok.experimental.UtilityClass;

import java.util.Random;
import java.util.UUID;

@UtilityClass
public class PokemonEntityMother {

    public PokemonEntity random() {
        return builder().build();
    }

    public PokemonEntity withWeight(int weight) {
        return builder().weight(weight).build();
    }

    public PokemonEntity withHeight(int height) {
        return builder().height(height).build();
    }

    public PokemonEntity withBaseExperience(int baseExperience) {
        return builder().baseExperience(baseExperience).build();
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
