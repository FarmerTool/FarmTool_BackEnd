package com.pipertzis.FarmHelper_BackEnd.Models;


import javax.persistence.*;
import java.util.Locale;
import java.util.UUID;

@Entity
@Table(name = "variety")
public class Variety {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "variety_id")
    private UUID varietyId;
    @Column(nullable = false,name = "variety_name")
    private String varietyName;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false,name = "fruit_id", referencedColumnName = "fruit_id")
    private Fruit fruit;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false,name = "user_id", referencedColumnName = "user_id")
    private User user;


    public Variety() {
    }

    public UUID getVarietyId() {
        return varietyId;
    }

    public String getVarietyName() {
        return varietyName;
    }

    public void setVarietyName(String varietyName) {
        this.varietyName = varietyName.toLowerCase(Locale.ROOT);
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
