package github.makcon.pokemons.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "github.makcon.pokemons")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
