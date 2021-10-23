package com.pipertzis.FarmHelper_BackEnd.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.UUID;

@Entity
@Table(name = "entry")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Entry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "entry_id")
    private UUID entryId;

    @Column(nullable = false, name = "amount")
    private int amount;

    @Column(name = "price")
    private double price;

    @Column(name = "weight")
    private double weight;

    @Column(nullable = false, name = "date")
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id", referencedColumnName = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "fruit_id", referencedColumnName = "fruit_id")
    private Fruit fruit;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "variety_id", referencedColumnName = "variety_id")
    private Variety variety;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "package_id", referencedColumnName = "package_id")
    private Package aPackage;


}
