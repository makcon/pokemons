package github.makcon.pokemons.infra.external.pokemon_api.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import github.makcon.pokemons.infra.external.pokemon_api.client.PokemonApiClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.net.http.HttpClient;
import java.util.concurrent.Executor;

@Configuration
public class PokemonApiConfig {

    @Bean
    public PokemonApiClient pokemonApiClient(@Value("${pokemons-api.base-url}") String baseUrl,
                                             Executor clientExecutor) {
        return PokemonApiClient.builder()
                .baseUrl(baseUrl)
                .objectMapper(objectMapper())
                .httpClient(HttpClient.newBuilder().executor(clientExecutor).build())
                .build();
    }

    @Bean
    public Executor clientExecutor(@Value("${pokemons-api.results.limit}") int poolSize) {
        var executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix("pokemons-api-");
        executor.setCorePoolSize(poolSize);
        executor.setMaxPoolSize(poolSize);

        return executor;
    }

    private ObjectMapper objectMapper() {
        return new ObjectMapper()
                .findAndRegisterModules()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
    }
}
