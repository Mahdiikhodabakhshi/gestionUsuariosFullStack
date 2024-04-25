package com.kreitek.usuarios.application.dto;

import com.kreitek.usuarios.domain.entity.type.UserType;

import java.io.Serializable;
import java.util.Objects;

public class UserDTO implements Serializable {

    private Long id;
    private String name;
    private String lastName;
    private String email;
    private UserType userType;

    public UserDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(id, userDTO.id) && Objects.equals(name, userDTO.name) && Objects.equals(lastName, userDTO.lastName) && Objects.equals(email, userDTO.email) && userType == userDTO.userType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, email, userType);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", userType=" + userType +
                '}';
    }
}
