package ru.padwicki.aviasales.implementation.injection;

import org.springframework.beans.factory.annotation.Autowired;
import ru.padwicki.aviasales.implementation.impl.AdminImpl;
import ru.padwicki.aviasales.implementation.impl.UserImpl;

public interface InjectionAdminImpl {

    public void setAdminImpl(AdminImpl adminImpl);
}
