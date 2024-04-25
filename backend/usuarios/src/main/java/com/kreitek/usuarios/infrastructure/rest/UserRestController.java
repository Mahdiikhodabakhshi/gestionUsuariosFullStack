package com.kreitek.usuarios.infrastructure.rest;

import com.kreitek.usuarios.application.dto.UserDTO;
import com.kreitek.usuarios.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserRestController {

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }


//    @GetMapping(value = "/users" , produces = "application/json")
//    public ResponseEntity<List<UserDTO>> getAllUsers() {
//        List<UserDTO> userDTOS = userService.findAllUsers();
//        return ResponseEntity.ok(userDTOS);
//    }

    @GetMapping(value = "/users" , produces = "application/json")
    public ResponseEntity<Page<UserDTO>> getAllUsersByCreteriaPage(@RequestParam(value = "filter",required = false) String filter , Pageable pageable) {
        Page<UserDTO> userDTOS = userService.findUsersByCreteriaStringPage(pageable , filter);
        return ResponseEntity.ok(userDTOS);
    }


    @GetMapping(value = "/users/{userId}" , produces = "application/json")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long userId) {
       return userService
               .findUserById(userId)
               .map(userDTO -> new ResponseEntity<>(userDTO , HttpStatus.OK))
               .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PostMapping(value = "/users" , produces = "application/json" , consumes = "application/json")
    public ResponseEntity<UserDTO> insertUser(@RequestBody UserDTO userDTO) {
        UserDTO userDTOS = userService.saveUser(userDTO);
        return ResponseEntity.ok(userDTOS);
    }

    @PatchMapping(value = "/users" , produces = "application/json" , consumes = "application/json")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) {
        UserDTO userDTOUpdate = userService.saveUser(userDTO);
        return ResponseEntity.ok(userDTOUpdate);
    }

    @DeleteMapping(value = "/users/{userId}" , produces = "application/json")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();

    }






}
