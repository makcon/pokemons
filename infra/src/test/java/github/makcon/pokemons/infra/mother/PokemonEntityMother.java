package github.makcon.pokemons.infra.mother;

import github.makcon.pokemons.infra.entity.PokemonEntity;
import lombok.experimental.UtilityClass;

import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
                .baseExperience(randomInt())
                .versions(versions());
    }

    private String versions() {
        return IntStream.rangeClosed(1, new Random().nextInt(3) + 1)
                .mapToObj(it -> randomString())
                .collect(Collectors.joining(";"));
    }

    private int randomInt() {
        return new Random().nextInt();
    }

    private String randomString() {
        return UUID.randomUUID().toString();
    }
}
