package github.makcon.pokemons.application.rest;

import github.makcon.pokemons.application.converter.PokemonDtoConverter;
import github.makcon.pokemons.domain.service.PokemonsService;
import github.makcon.pokemons.dto.v1.PokemonV1;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@Slf4j
@RestController
@RequestMapping("/v1/pokemons")
@RequiredArgsConstructor
public class PokemonsController {

    private static final String DEFAULT_SIZE = "5";
    private static final String DEFAULT_VERSION = "red";

    private final PokemonsService service;
    private final PokemonDtoConverter converter;

    @GetMapping("/most-heaviest")
    public List<PokemonV1> findMostHeaviest(@RequestParam(defaultValue = DEFAULT_SIZE) int size,
                                            @RequestParam(defaultValue = DEFAULT_VERSION) String version) {
        log.info("Requesting {} most heaviest pokemons", size);
        return converter.fromModels(service.findMostHeaviest(size, version));
    }

    @GetMapping("/most-highest")
    public List<PokemonV1> findMostHighest(@RequestParam(defaultValue = DEFAULT_SIZE) int size,
                                           @RequestParam(defaultValue = DEFAULT_VERSION) String version) {
        log.info("Requesting {} most highest pokemons", size);
        return converter.fromModels(service.findMostHighest(size, version));
    }

    @GetMapping("/most-experienced")
    public List<PokemonV1> findMostExperienced(@RequestParam(defaultValue = DEFAULT_SIZE) int size,
                                               @RequestParam(defaultValue = DEFAULT_VERSION) String version) {
        log.info("Requesting {} most experienced pokemons", size);
        return converter.fromModels(service.findMostExperienced(size, version));
    }
}
