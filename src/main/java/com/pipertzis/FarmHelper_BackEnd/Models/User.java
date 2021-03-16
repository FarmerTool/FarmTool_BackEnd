package com.pipertzis.FarmHelper_BackEnd.Models;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column (name="user_id")
    private UUID userId;
    @Column (nullable = false,name="name")
    private String name;
    @Column (nullable = false,name="surname")
    private String surname;
    @Column (nullable = false,unique = true,name="email")
    private String email;
    @Column (nullable = false,name="password")
    private String password;
    @Column (nullable = false,name="phone_number")
    private String phoneNumber;


    public User() {
    }

    public User(UUID userId, String name, String surname, String email, String password, String phoneNumber) {
        this.userId = userId;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
