package com.pipertzis.FarmHelper_BackEnd.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column (name="user_id")
    private UUID userId;
    @Column (nullable = false,name="name")
    private String username;
    @Column (nullable = false,name="surname")
    private String surname;
    @Column (nullable = false,unique = true,name="email")
    private String email;
    @Column (nullable = false,name="password")
    private String password;
    @Column (nullable = false,name="phone_number")
    private String phoneNumber;



}
