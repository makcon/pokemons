package github.makcon.pokemons.infra.external.pokemon_api.dto;

import lombok.Data;

import java.util.List;

@Data
public class PokemonApiDto {

    private Integer id;
    private Integer height;
    private Integer weight;
    private Integer baseExperience;
    private String name;
    private List<GameIndex> gameIndices;

    @Data
    public static class GameIndex {

        private Version version;

        @Data
        public static class Version {

            private String name;
            private String url;
        }
    }
}
