package github.makcon.pokemons.application.rest;

import github.makcon.pokemons.application.mother.PokemonEntityMother;
import github.makcon.pokemons.infra.repository.PokemonsJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;
import java.util.stream.IntStream;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest("spring.profiles.active=test")
@AutoConfigureMockMvc
class PokemonsControllerShould {

    private static final int GIVEN_SIZE = 3;
    private static final String GIVEN_VERSION = UUID.randomUUID().toString();

    private static final String MOST_HIGHEST_URL = "/v1/pokemons/most-highest?size=%d&version=%s";
    private static final String MOST_EXPERIENCED_URL = "/v1/pokemons/most-experienced?size=%d&version=%s";
    private static final String MOST_HEAVIEST_URL = "/v1/pokemons/most-heaviest?size=%d&version=%s";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PokemonsJpaRepository jpaRepository;

    @BeforeEach
    void setUp() {
        jpaRepository.deleteAll();

        IntStream.rangeClosed(0, GIVEN_SIZE)
                .forEach(it -> jpaRepository.save(PokemonEntityMother.randomWithVersion(GIVEN_VERSION)));
    }

    @Test
    void find_most_heaviest() throws Exception {
        // when - then
        mockMvc.perform(get(String.format(MOST_HEAVIEST_URL, GIVEN_SIZE, GIVEN_VERSION)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(GIVEN_SIZE)));
    }

    @Test
    void find_most_highest() throws Exception {
        // when - then
        mockMvc.perform(get(String.format(MOST_HIGHEST_URL, GIVEN_SIZE, GIVEN_VERSION)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(GIVEN_SIZE)));
    }

    @Test
    void find_most_experienced() throws Exception {
        // when - then
        mockMvc.perform(get(String.format(MOST_EXPERIENCED_URL, GIVEN_SIZE, GIVEN_VERSION)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(GIVEN_SIZE)));
    }
}