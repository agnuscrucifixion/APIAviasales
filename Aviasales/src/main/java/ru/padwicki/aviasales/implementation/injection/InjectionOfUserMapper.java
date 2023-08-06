package ru.padwicki.aviasales.implementation.injection;

import org.springframework.beans.factory.annotation.Autowired;
import ru.padwicki.aviasales.implementation.mapper.UserMapper;
import ru.padwicki.aviasales.implementation.mapper.UserMapperImpl;

public interface InjectionOfUserMapper {
    public void setUserMapperImpl(UserMapperImpl userMapper);
}
