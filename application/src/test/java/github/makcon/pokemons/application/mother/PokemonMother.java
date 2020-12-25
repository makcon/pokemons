package github.makcon.pokemons.application.mother;

import github.makcon.pokemons.domain.model.Pokemon;
import lombok.experimental.UtilityClass;

import java.util.Random;
import java.util.UUID;

@UtilityClass
public class PokemonMother {

    public Pokemon random() {
        return builder().build();
    }

    public Pokemon.PokemonBuilder builder() {
        return Pokemon.builder()
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
