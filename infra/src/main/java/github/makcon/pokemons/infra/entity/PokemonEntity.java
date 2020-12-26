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
    private Integer externalId;
    private Integer height;
    private Integer weight;
    private Integer baseExperience;
    private String name;
    /**
     * FIXME versions stored here as separated string but it's not flexible
     * for example, it would be tricky to filter if we want to find by multiple versions.
     */
    private String versions;

    @UtilityClass
    public static class Field {

        public final String HEIGHT = "height";
        public final String WEIGHT = "weight";
        public final String BASE_EXPERIENCE = "baseExperience";
    }
}
