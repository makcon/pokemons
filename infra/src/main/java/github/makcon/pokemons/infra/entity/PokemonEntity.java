package github.makcon.pokemons.infra.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.UtilityClass;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PokemonEntity {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer height;
    private Integer weight;
    private Integer baseExperience;
    private String name;

    @UtilityClass
    public static class Field {

        public final String HEIGHT = "height";
        public final String WEIGHT = "weight";
        public final String BASE_EXPERIENCE = "baseExperience";
    }
}
