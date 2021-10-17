package com.pipertzis.FarmHelper_BackEnd.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "fruits")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Fruit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "fruit_id")
    private UUID fruitId;
    @Column(nullable = false, name = "fruit_name")
    private String fruitName;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id", referencedColumnName = "user_id")
    private User user;


}
