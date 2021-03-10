package com.pipertzis.FarmHelper_BackEnd.Models;

import javax.persistence.*;
import java.util.Locale;
import java.util.UUID;

@Entity
@Table(name = "fruits")
public class Fruit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "FruitId")
    private UUID fruitId;
    @Column(name = "fruitName")
    private String fruitName;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;

    public Fruit() {
    }

    public Fruit(UUID fruitId, String fruitName, User user) {
        this.fruitId = fruitId;
        this.fruitName = fruitName;
        this.user = user;
    }

    public UUID getFruitId() {
        return fruitId;
    }

    public void setFruitId(UUID fruitId) {
        this.fruitId = fruitId;
    }

    public String getFruitName() {
        return fruitName.toLowerCase(Locale.ROOT);
    }

    public void setFruitName(String fruitName) {
        this.fruitName = fruitName.toLowerCase(Locale.ROOT);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
