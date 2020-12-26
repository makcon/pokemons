package github.makcon.pokemons.infra.converter;

import github.makcon.pokemons.domain.model.Pokemon;
import github.makcon.pokemons.infra.entity.PokemonEntity;
import github.makcon.pokemons.infra.external.pokemon_api.dto.PokemonApiDto;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class PokemonEntityConverter {

    private static final String DELIMITER = ";";

    public Pokemon toModel(PokemonEntity entity) {
        return Pokemon.builder()
                .id(entity.getId())
                .weight(entity.getWeight())
                .height(entity.getHeight())
                .baseExperience(entity.getBaseExperience())
                .name(entity.getName())
                .versions(
                        Stream.of(entity.getVersions().split(DELIMITER))
                                .collect(Collectors.toList())
                )
                .build();
    }

    public PokemonEntity fromApiDto(PokemonApiDto dto) {
        return PokemonEntity.builder()
                .externalId(dto.getId())
                .height(dto.getHeight())
                .weight(dto.getWeight())
                .baseExperience(dto.getBaseExperience())
                .name(dto.getName())
                .versions(
                        dto.getGameIndices().stream()
                                .map(it -> it.getVersion().getName())
                                .collect(Collectors.joining(DELIMITER))
                )
                .build();
    }
}
