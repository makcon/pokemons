package github.makcon.pokemons.infra.converter;

import github.makcon.pokemons.domain.model.Pokemon;
import github.makcon.pokemons.infra.entity.PokemonEntity;
import org.springframework.stereotype.Component;

@Component
public class PokemonEntityConverter {

    public Pokemon toModel(PokemonEntity entity) {
        return Pokemon.builder()
                .id(entity.getId())
                .weight(entity.getWeight())
                .height(entity.getHeight())
                .baseExperience(entity.getBaseExperience())
                .name(entity.getName())
                .build();
    }
}
