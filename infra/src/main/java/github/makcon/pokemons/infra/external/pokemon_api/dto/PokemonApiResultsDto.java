package github.makcon.pokemons.infra.external.pokemon_api.dto;

import lombok.Data;

import java.util.List;

@Data
public class PokemonApiResultsDto {

    private List<Result> results;

    @Data
    public static class Result {

        private String url;
    }
}
