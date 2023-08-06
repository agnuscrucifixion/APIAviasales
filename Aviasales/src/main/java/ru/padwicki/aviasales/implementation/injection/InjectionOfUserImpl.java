package ru.padwicki.aviasales.implementation.injection;

import org.springframework.beans.factory.annotation.Autowired;
import ru.padwicki.aviasales.implementation.impl.UserImpl;

public interface InjectionOfUserImpl {
    public void setUserImpl(UserImpl userImpl);
}
