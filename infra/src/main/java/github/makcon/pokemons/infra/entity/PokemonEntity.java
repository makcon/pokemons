package github.makcon.pokemons.infra.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.UtilityClass;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Instant;

@Entity
@Data
@Builder
public class PokemonEntity {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer height;
    private Integer weight;
    private Integer baseExperience;
    private String name;
    @CreatedDate
    private Instant createdAt;

    @UtilityClass
    public static class Field {

        public final String HEIGHT = "height";
        public final String WEIGHT = "weight";
        public final String BASE_EXPERIENCE = "baseExperience";
    }
}
