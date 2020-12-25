package github.makcon.pokemons.infra.mother;

import github.makcon.pokemons.infra.external.pokemon_api.dto.PokemonApiResultsDto;
import lombok.experimental.UtilityClass;

import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@UtilityClass
public class PokemonApiResultsMother {

    public PokemonApiResultsDto withRandomUrls(int count) {
        var results = IntStream.range(0, count)
                .mapToObj(it -> PokemonApiResultsMother.resultWithRandomUrl())
                .collect(Collectors.toList());

        var dto = new PokemonApiResultsDto();
        dto.setResults(results);

        return dto;
    }

    public PokemonApiResultsDto.Result resultWithRandomUrl() {
        var dto = new PokemonApiResultsDto.Result();
        dto.setUrl(UUID.randomUUID().toString());

        return dto;
    }
}
