package github.makcon.pokemons.infra.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "github.makcon.pokemons.infra.repository")
@EntityScan(basePackages = "github.makcon.pokemons.infra.entity")
public class RepositoryConfig {
}
