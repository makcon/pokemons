package github.makcon.pokemons.application.converter;

import github.makcon.pokemons.domain.model.Pokemon;
import github.makcon.pokemons.dto.v1.PokemonV1;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PokemonDtoConverter {

    public List<PokemonV1> fromModels(List<Pokemon> pokemons) {
        return pokemons.stream()
                .map(this::fromModel)
                .collect(Collectors.toList());
    }

    public PokemonV1 fromModel(Pokemon pokemon) {
        return PokemonV1.builder()
                .id(pokemon.getId())
                .weight(pokemon.getWeight())
                .height(pokemon.getHeight())
                .baseExperience(pokemon.getBaseExperience())
                .name(pokemon.getName())
                .build();
    }
}
