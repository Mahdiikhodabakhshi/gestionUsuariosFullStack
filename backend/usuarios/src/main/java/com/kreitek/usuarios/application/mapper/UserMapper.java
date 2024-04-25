package com.kreitek.usuarios.application.mapper;

import com.kreitek.usuarios.application.dto.UserDTO;
import com.kreitek.usuarios.domain.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDTO, User> {
}
