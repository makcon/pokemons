package github.makcon.pokemons.infra.repository;

import github.makcon.pokemons.infra.entity.PokemonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonsJpaRepository extends JpaRepository<PokemonEntity, Integer> {
}
