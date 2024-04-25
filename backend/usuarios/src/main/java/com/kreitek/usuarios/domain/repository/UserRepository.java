package com.kreitek.usuarios.domain.repository;

import com.kreitek.usuarios.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> findAllUsers();
    Optional<User> findUserById(Long userId);
    User newUser(User user);
    void deleteUser(Long userId);

    Page<User> findAll(Pageable pageable, String filter);
}
