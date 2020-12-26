package github.makcon.pokemons.infra.mother;

import github.makcon.pokemons.infra.external.pokemon_api.dto.PokemonApiDto;
import github.makcon.pokemons.infra.external.pokemon_api.dto.PokemonApiDto.GameIndex;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@UtilityClass
public class PokemonApiDtoMother {

    public PokemonApiDto random() {
        var dto = new PokemonApiDto();
        dto.setId(randomInt());
        dto.setHeight(randomInt());
        dto.setWeight(randomInt());
        dto.setBaseExperience(randomInt());
        dto.setName(randomString());
        dto.setGameIndices(gameIndices());

        return dto;
    }

    private List<GameIndex> gameIndices() {
        return IntStream.rangeClosed(1, new Random().nextInt(4) + 1)
                .mapToObj(it -> gameIndex())
                .collect(Collectors.toList());
    }

    private GameIndex gameIndex() {
        var version = new GameIndex.Version();
        version.setName(randomString());

        var gameIndex = new GameIndex();
        gameIndex.setVersion(version);

        return gameIndex;
    }

    private int randomInt() {
        return new Random().nextInt();
    }

    private String randomString() {
        return UUID.randomUUID().toString();
    }
}
