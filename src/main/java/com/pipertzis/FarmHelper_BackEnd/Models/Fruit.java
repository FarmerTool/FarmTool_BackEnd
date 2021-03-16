package com.pipertzis.FarmHelper_BackEnd.Models;

import javax.persistence.*;
import java.util.Locale;
import java.util.UUID;

@Entity
@Table(name = "fruits")
public class Fruit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "fruit_id")
    private UUID fruitId;
    @Column(nullable = false,name = "fruit_name")
    private String fruitName;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false,name = "user_id", referencedColumnName = "user_id")
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
