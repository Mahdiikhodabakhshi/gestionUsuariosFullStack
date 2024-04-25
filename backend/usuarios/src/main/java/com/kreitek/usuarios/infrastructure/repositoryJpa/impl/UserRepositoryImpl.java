package com.kreitek.usuarios.infrastructure.repositoryJpa.impl;

import com.kreitek.usuarios.domain.entity.User;
import com.kreitek.usuarios.domain.repository.UserRepository;
import com.kreitek.usuarios.infrastructure.repositoryJpa.UserJpaRepository;
import com.kreitek.usuarios.specs.UserSpecification;
import com.kreitek.usuarios.specs.shared.SearchCriteriaHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final UserJpaRepository userJpaRepository;

    @Autowired
    public UserRepositoryImpl(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }


    @Override
    public List<User> findAllUsers() {
        return this.userJpaRepository.findAll();
    }

    @Override
    public Optional<User> findUserById(Long userId) {
        return this.userJpaRepository.findById(userId);
    }

    @Override
    public User newUser(User user) {
        return this.userJpaRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        this.userJpaRepository.deleteById(userId);
    }

    @Override
    public Page<User> findAll(Pageable pageable, String filter) {
        UserSpecification specification = new UserSpecification(SearchCriteriaHelper.fromFilterString(filter));
        return userJpaRepository.findAll(specification,pageable);
    }
}
