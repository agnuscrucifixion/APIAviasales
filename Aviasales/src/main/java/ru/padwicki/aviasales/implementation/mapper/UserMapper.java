package ru.padwicki.aviasales.implementation.mapper;

import org.mapstruct.Mapper;
import ru.padwicki.tire.dto.UserDTO;
import ru.padwicki.aviasales.domain.entity.User;
import ru.padwicki.aviasales.implementation.models.UserModel;

import java.util.List;

@Mapper
public interface UserMapper {
    List<UserDTO> toListDTO(List<User> users);
    List<User> toListUsers(List<UserDTO> userDTO);
    UserDTO toDTO(User user);
    UserModel toModel(UserDTO dto);
    User toEntity(UserModel model);
    UserModel toModel(User user);
    User toEntity(UserDTO dto);
}
