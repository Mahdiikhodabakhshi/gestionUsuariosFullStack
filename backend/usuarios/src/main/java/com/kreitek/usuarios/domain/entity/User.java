package com.kreitek.usuarios.domain.entity;

import com.kreitek.usuarios.domain.entity.type.UserType;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "userSquence")
    @Column(name = "id" , nullable = false)
    private Long id;

    @Column(name = "name" , nullable = false , length = 100)
    private String name;

    @Column(name = "lastName" , nullable = false )
    private String lastName;

    @Column(name = "email" , nullable = false , length = 200)
    private String email;

    @Column(name = "user_type" , nullable = false , length = 50)
    @Enumerated(EnumType.STRING)
    private UserType userType;

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
}
