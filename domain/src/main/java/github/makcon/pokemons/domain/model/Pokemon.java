package github.makcon.pokemons.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Pokemon {

    private final Integer id;
    private final Integer height;
    private final Integer weight;
    private final Integer baseExperience;
    private final String name;
}
