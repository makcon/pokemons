package github.makcon.pokemons.application.rest;

import github.makcon.pokemons.application.mother.PokemonEntityMother;
import github.makcon.pokemons.infra.repository.PokemonsJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.IntStream;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest("spring.profiles.active=test")
@AutoConfigureMockMvc
class PokemonsControllerShould {

    private static final int GIVEN_SIZE = 3;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PokemonsJpaRepository jpaRepository;

    @BeforeEach
    void setUp() {
        jpaRepository.deleteAll();

        IntStream.rangeClosed(0, GIVEN_SIZE)
                .forEach(it -> jpaRepository.save(PokemonEntityMother.random()));
    }

    @Test
    void find_most_heaviest() throws Exception {
        // when - then
        mockMvc.perform(get("/v1/pokemons/most-heaviest?size=" + GIVEN_SIZE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(GIVEN_SIZE)));
    }

    @Test
    void find_most_highest() throws Exception {
        // when - then
        mockMvc.perform(get("/v1/pokemons/most-highest?size=" + GIVEN_SIZE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(GIVEN_SIZE)));
    }

    @Test
    void find_most_experienced() throws Exception {
        // when - then
        mockMvc.perform(get("/v1/pokemons/most-experienced?size=" + GIVEN_SIZE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(GIVEN_SIZE)));
    }
}