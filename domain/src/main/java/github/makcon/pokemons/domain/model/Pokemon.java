package github.makcon.pokemons.domain.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Pokemon {

    private final Integer id;
    private final Integer height;
    private final Integer weight;
    private final Integer baseExperience;
    private final String name;
    private final List<String> versions;
}
