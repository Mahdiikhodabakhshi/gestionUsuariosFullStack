package com.kreitek.usuarios.application.service;

import com.kreitek.usuarios.application.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDTO> findAllUsers();
    Optional<UserDTO> findUserById(Long userId);
    UserDTO saveUser(UserDTO userDTO);
    void deleteUser(Long userId);

    Page<UserDTO> findUsersByCreteriaStringPage(Pageable pageable, String filter);


}
