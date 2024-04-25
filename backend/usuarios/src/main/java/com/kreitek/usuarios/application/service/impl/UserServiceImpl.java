package com.kreitek.usuarios.application.service.impl;

import com.kreitek.usuarios.application.dto.UserDTO;
import com.kreitek.usuarios.application.mapper.UserMapper;
import com.kreitek.usuarios.application.service.UserService;
import com.kreitek.usuarios.domain.entity.User;
import com.kreitek.usuarios.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> findAllUsers() {
        List<User> users = userRepository.findAllUsers();
        return userMapper.toDto(users);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserDTO> findUserById(Long userId) {
        return userRepository.findUserById(userId).map(userMapper::toDto);
    }

    @Override
    @Transactional
    public UserDTO saveUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        user = userRepository.newUser(user);
        return userMapper.toDto(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteUser(userId);
    }

    @Override
    public Page<UserDTO> findUsersByCreteriaStringPage(Pageable pageable, String filter) {
        Page<User> userPage = userRepository.findAll(pageable,filter);
        return userPage.map(userMapper::toDto);
    }
}
