package io.github.himcs.zero.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({
        "io.github.himcs.zero"
})
@EnableJpaRepositories({
        "io.github.himcs.zero"
})
@EntityScan({
        "io.github.himcs.zero"
})
public class Bootstrap {
    public static void main(final String[] args) {
        SpringApplication.run(Bootstrap.class, args);
    }
}
