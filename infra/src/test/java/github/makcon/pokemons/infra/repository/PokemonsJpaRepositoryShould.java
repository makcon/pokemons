package github.makcon.pokemons.infra.repository;

import github.makcon.pokemons.infra.config.RepositoryConfig;
import github.makcon.pokemons.infra.entity.PokemonEntity;
import github.makcon.pokemons.infra.entity.PokemonEntity.Field;
import github.makcon.pokemons.infra.mother.PokemonEntityMother;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.data.domain.Sort.Direction.DESC;

@SpringBootTest(classes = RepositoryConfig.class)
@EnableAutoConfiguration
class PokemonsJpaRepositoryShould {

    private static final int GIVEN_LIMIT = 3;

    @Autowired
    private PokemonsJpaRepository repository;

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void return_limited_entities_ordered_by_weight() {
        // given
        repository.save(PokemonEntityMother.withWeight(2));
        repository.save(PokemonEntityMother.withWeight(10));
        repository.save(PokemonEntityMother.withWeight(3));
        repository.save(PokemonEntityMother.withWeight(5));
        repository.save(PokemonEntityMother.withWeight(6));
        repository.save(PokemonEntityMother.withWeight(10));

        // when
        var pokemons = find(Field.WEIGHT);

        // then
        assertThat(pokemons).hasSize(GIVEN_LIMIT);
        assertThat(pokemons.get(0).getWeight()).isEqualTo(10);
        assertThat(pokemons.get(1).getWeight()).isEqualTo(10);
        assertThat(pokemons.get(2).getWeight()).isEqualTo(6);
    }

    @Test
    void return_limited_entities_ordered_by_height() {
        // given
        repository.save(PokemonEntityMother.withHeight(1));
        repository.save(PokemonEntityMother.withHeight(15));
        repository.save(PokemonEntityMother.withHeight(3));
        repository.save(PokemonEntityMother.withHeight(5));
        repository.save(PokemonEntityMother.withHeight(10));
        repository.save(PokemonEntityMother.withHeight(7));

        // when
        var pokemons = find(Field.HEIGHT);

        // then
        assertThat(pokemons).hasSize(GIVEN_LIMIT);
        assertThat(pokemons.get(0).getHeight()).isEqualTo(15);
        assertThat(pokemons.get(1).getHeight()).isEqualTo(10);
        assertThat(pokemons.get(2).getHeight()).isEqualTo(7);
    }

    @Test
    void return_limited_entities_ordered_by_baseExperience() {
        // given
        repository.save(PokemonEntityMother.withBaseExperience(5));
        repository.save(PokemonEntityMother.withBaseExperience(6));
        repository.save(PokemonEntityMother.withBaseExperience(3));
        repository.save(PokemonEntityMother.withBaseExperience(9));
        repository.save(PokemonEntityMother.withBaseExperience(1));
        repository.save(PokemonEntityMother.withBaseExperience(4));

        // when
        var pokemons = find(Field.BASE_EXPERIENCE);

        // then
        assertThat(pokemons).hasSize(GIVEN_LIMIT);
        assertThat(pokemons.get(0).getBaseExperience()).isEqualTo(9);
        assertThat(pokemons.get(1).getBaseExperience()).isEqualTo(6);
        assertThat(pokemons.get(2).getBaseExperience()).isEqualTo(5);
    }

    private List<PokemonEntity> find(String field) {
        return repository.findAll(PageRequest.of(0, GIVEN_LIMIT, DESC, field))
                .getContent();
    }
}