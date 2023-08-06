package ru.padwicki.aviasales.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@EnableJpaRepositories(basePackages = "ru.padwicki.aviasales.domain.repository")
@EntityScan("ru.padwicki.aviasales.domain.entity")
@ComponentScan("ru.padwicki.aviasales")
@ComponentScan("ru.padwicki.brokers")
@SpringBootApplication
public class Application {
    public static void main(String[] args) {

        SpringApplication.run(Application.class);
    }
}
