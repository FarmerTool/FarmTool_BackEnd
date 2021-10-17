package com.pipertzis.FarmHelper_BackEnd.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "packages")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Package {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,name = "package_id")
    private UUID packageId;

    @Column(nullable = false,name = "package_name")
    private String packageName;

    @Column(nullable = false,name = "fruit_name")
    private String fruitName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "fruit_id", referencedColumnName = "fruit_id")
    private Fruit fruit;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id", referencedColumnName = "user_id")
    private User user;



}
