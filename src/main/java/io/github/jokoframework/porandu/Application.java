package io.github.jokoframework.porandu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@ComponentScan(basePackages = {"io.github.jokoframework.security", "io.github.jokoframework.porandu"})
@EnableJpaRepositories(basePackages = {"io.github.jokoframework.security",
        "io.github.jokoframework.porandu.repositories"})
@EntityScan(basePackages = {"io.github.jokoframework.security", "io.github.jokoframework.porandu.entities"})
@EnableScheduling
@EnableAsync
@EnableJpaAuditing
public class Application extends SpringBootServletInitializer {


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
        initialize(ctx);

    }

    private static void initialize(ConfigurableApplicationContext ctx) {
    }
}
