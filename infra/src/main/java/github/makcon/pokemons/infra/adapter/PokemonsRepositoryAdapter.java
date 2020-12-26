package github.makcon.pokemons.infra.adapter;

import github.makcon.pokemons.domain.model.Pokemon;
import github.makcon.pokemons.domain.port.PokemonsRepositoryPort;
import github.makcon.pokemons.infra.converter.PokemonEntityConverter;
import github.makcon.pokemons.infra.entity.PokemonEntity.Field;
import github.makcon.pokemons.infra.repository.PokemonsJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.domain.Sort.Direction.DESC;

@Component
@RequiredArgsConstructor
public class PokemonsRepositoryAdapter implements PokemonsRepositoryPort {

    private final PokemonsJpaRepository jpaRepository;
    private final PokemonEntityConverter converter;

    @Override
    public List<Pokemon> findTopByWeight(int limit, String version) {
        return findAndConvert(Field.WEIGHT, limit, version);
    }

    @Override
    public List<Pokemon> findTopByHeight(int limit, String version) {
        return findAndConvert(Field.HEIGHT, limit, version);
    }

    @Override
    public List<Pokemon> findTopByBaseExperience(int limit, String version) {
        return findAndConvert(Field.BASE_EXPERIENCE, limit, version);
    }

    private List<Pokemon> findAndConvert(String field, int limit, String version) {
        var pageable = PageRequest.of(0, limit, DESC, field);

        var entities = version == null
                ? jpaRepository.findAll(pageable)
                : jpaRepository.findAllByVersionsContaining(pageable, version);

        return entities
                .stream()
                .map(converter::toModel)
                .collect(Collectors.toList());
    }
}
