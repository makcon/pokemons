package github.makcon.pokemons.dto.v1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PokemonV1 {

    private Integer id;
    private Integer height;
    private Integer weight;
    private Integer baseExperience;
    private String name;
    private List<String> versions;
}
