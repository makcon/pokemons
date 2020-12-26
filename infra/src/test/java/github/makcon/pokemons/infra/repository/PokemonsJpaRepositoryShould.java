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
import java.util.UUID;

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
        var entity1 = repository.save(PokemonEntityMother.withWeight(10));
        repository.save(PokemonEntityMother.withWeight(3));
        repository.save(PokemonEntityMother.withWeight(5));
        var entity3 = repository.save(PokemonEntityMother.withWeight(6));
        var entity2 = repository.save(PokemonEntityMother.withWeight(10));

        // when
        var pokemons = find(Field.WEIGHT);

        // then
        assertThat(pokemons).hasSize(GIVEN_LIMIT);
        assertThat(pokemons).contains(entity1);
        assertThat(pokemons).contains(entity2);
        assertThat(pokemons).contains(entity3);
    }

    @Test
    void return_limited_entities_ordered_by_height() {
        // given
        repository.save(PokemonEntityMother.withHeight(1));
        var entity1 = repository.save(PokemonEntityMother.withHeight(15));
        repository.save(PokemonEntityMother.withHeight(3));
        repository.save(PokemonEntityMother.withHeight(5));
        var entity2 = repository.save(PokemonEntityMother.withHeight(10));
        var entity3 = repository.save(PokemonEntityMother.withHeight(7));

        // when
        var pokemons = find(Field.HEIGHT);

        // then
        assertThat(pokemons).hasSize(GIVEN_LIMIT);
        assertThat(pokemons).contains(entity1);
        assertThat(pokemons).contains(entity2);
        assertThat(pokemons).contains(entity3);
    }

    @Test
    void return_limited_entities_ordered_by_baseExperience() {
        // given
        var entity3 = repository.save(PokemonEntityMother.withBaseExperience(5));
        var entity2 = repository.save(PokemonEntityMother.withBaseExperience(6));
        repository.save(PokemonEntityMother.withBaseExperience(3));
        var entity1 = repository.save(PokemonEntityMother.withBaseExperience(9));
        repository.save(PokemonEntityMother.withBaseExperience(1));
        repository.save(PokemonEntityMother.withBaseExperience(4));

        // when
        var pokemons = find(Field.BASE_EXPERIENCE);

        // then
        assertThat(pokemons).hasSize(GIVEN_LIMIT);
        assertThat(pokemons).contains(entity1);
        assertThat(pokemons).contains(entity2);
        assertThat(pokemons).contains(entity3);
    }

    @Test
    void return_entities_with_specific_versions() {
        // given
        var version1 = randomVersion();
        var entity1 = repository.save(PokemonEntityMother.withSameHeightAndVersions(version1, randomVersion()));
        var entity2 = repository.save(PokemonEntityMother.withSameHeightAndVersions(version1));
        var entity3 = repository.save(PokemonEntityMother.withSameHeightAndVersions(randomVersion(), version1));
        repository.save(PokemonEntityMother.withSameHeightAndVersions(randomVersion()));
        repository.save(PokemonEntityMother.withSameHeightAndVersions(randomVersion(), randomVersion()));

        // when
        var pokemons = find(Integer.MAX_VALUE, Field.HEIGHT, version1);

        // then
        assertThat(pokemons).hasSize(3);
        assertThat(pokemons).contains(entity1);
        assertThat(pokemons).contains(entity2);
        assertThat(pokemons).contains(entity3);
    }

    private String randomVersion() {
        return UUID.randomUUID().toString();
    }

    private List<PokemonEntity> find(String field) {
        return find(GIVEN_LIMIT, field, PokemonEntityMother.DEFAULT_VERSION);
    }

    private List<PokemonEntity> find(int limit, String field, String version) {
        return repository.findAllByVersionsContaining(
                PageRequest.of(0, limit, DESC, field),
                version
        )
                .getContent();
    }
}