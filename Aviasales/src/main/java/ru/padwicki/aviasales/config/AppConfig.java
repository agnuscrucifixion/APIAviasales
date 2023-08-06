package ru.padwicki.aviasales.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.padwicki.aviasales.implementation.mapper.TicketMapperImpl;
import ru.padwicki.aviasales.implementation.mapper.UserMapper;
import ru.padwicki.aviasales.implementation.mapper.UserMapperImpl;

@Configuration
public class AppConfig {
    @Bean
    public UserMapperImpl userMapper() {
        return new UserMapperImpl();
    }
    @Bean
    public TicketMapperImpl ticketMapper() {
        return new TicketMapperImpl();
    }
}
