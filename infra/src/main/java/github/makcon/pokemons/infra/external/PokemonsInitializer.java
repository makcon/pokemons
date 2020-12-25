package github.makcon.pokemons.infra.external;

import github.makcon.pokemons.infra.converter.PokemonEntityConverter;
import github.makcon.pokemons.infra.entity.PokemonEntity;
import github.makcon.pokemons.infra.external.pokemon_api.PokemonApiRequestService;
import github.makcon.pokemons.infra.repository.PokemonsJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class PokemonsInitializer {

    private final PokemonApiRequestService requestService;
    private final PokemonEntityConverter converter;
    private final PokemonsJpaRepository jpaRepository;

//    @PostConstruct
    public void init() {
        log.info("Starting to initialize pokemons");
        var offset = 0;
        var entities = fetchAndSave(offset);

        while (!entities.isEmpty()) {
            offset += entities.size();
            entities = fetchAndSave(offset);
        }

        log.info("Finished to initialize pokemons, total count: {}", jpaRepository.count());
    }

    private List<PokemonEntity> fetchAndSave(int offset) {
        var entities = requestService.request(offset)
                .stream()
                .map(converter::fromApiDto)
                .collect(Collectors.toList());

        return entities.isEmpty() ? List.of() : jpaRepository.saveAll(entities);
    }
}
