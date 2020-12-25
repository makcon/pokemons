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
    public List<Pokemon> findOrderedByWeight(int limit) {
        return findAndConvert(Field.WEIGHT, limit);
    }

    @Override
    public List<Pokemon> findOrderedByHeight(int limit) {
        return findAndConvert(Field.HEIGHT, limit);
    }

    @Override
    public List<Pokemon> findOrderedByBaseExperience(int limit) {
        return findAndConvert(Field.BASE_EXPERIENCE, limit);
    }

    private List<Pokemon> findAndConvert(String field, int limit) {
        return jpaRepository.findAll(PageRequest.of(0, limit, DESC, field))
                .stream()
                .map(converter::toModel)
                .collect(Collectors.toList());
    }
}
