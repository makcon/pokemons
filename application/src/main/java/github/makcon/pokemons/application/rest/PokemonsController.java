package github.makcon.pokemons.application.rest;

import github.makcon.pokemons.application.converter.PokemonDtoConverter;
import github.makcon.pokemons.domain.service.PokemonsService;
import github.makcon.pokemons.dto.v1.PokemonV1;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/pokemons")
@RequiredArgsConstructor
public class PokemonsController {

    private final PokemonsService service;
    private final PokemonDtoConverter converter;

    @GetMapping("/most-heaviest")
    public List<PokemonV1> findMostHeaviest(@RequestParam(defaultValue = "5") int size) {
        log.info("Requesting {} most heaviest pokemons", size);
        return converter.fromModels(service.findMostHeaviest(size));
    }

    @GetMapping("/most-highest")
    public List<PokemonV1> findMostHighest(@RequestParam(defaultValue = "5") int size) {
        log.info("Requesting {} most highest pokemons", size);
        return converter.fromModels(service.findMostHighest(size));
    }

    @GetMapping("/most-experienced")
    public List<PokemonV1> findMostExperienced(@RequestParam(defaultValue = "5") int size) {
        log.info("Requesting {} most experienced pokemons", size);
        return converter.fromModels(service.findMostExperienced(size));
    }
}
