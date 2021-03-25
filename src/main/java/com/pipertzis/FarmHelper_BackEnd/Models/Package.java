package com.pipertzis.FarmHelper_BackEnd.Models;

import javax.persistence.*;
import java.util.Locale;
import java.util.UUID;

@Entity
@Table(name = "packages")
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


    public Package() {
    }

    public Package(UUID packageId, String packageName, String fruitName, Fruit fruit, User user) {
        this.packageId = packageId;
        this.packageName = packageName;
        this.fruitName = fruitName;
        this.fruit = fruit;
        this.user = user;
    }

    public UUID getPackageId() {
        return packageId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName.toLowerCase(Locale.ROOT);
    }

    public String getFruitName() {
        return fruitName;
    }

    public void setFruitName(String fruitName) {
        this.fruitName = fruitName;
    }

    public Fruit getFruit() {
        return fruit;
    }

    public void setFruit(Fruit fruit) {
        this.fruit = fruit;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
