package github.makcon.pokemons.application.mother;

import github.makcon.pokemons.domain.model.Pokemon;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
                .baseExperience(randomInt())
                .versions(versions());
    }

    private List<String> versions() {
        return IntStream.rangeClosed(1, new Random().nextInt(3) + 1)
                .mapToObj(it -> randomString())
                .collect(Collectors.toList());
    }

    private int randomInt() {
        return new Random().nextInt();
    }

    private String randomString() {
        return UUID.randomUUID().toString();
    }
}
