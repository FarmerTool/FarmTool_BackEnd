package com.pipertzis.FarmHelper_BackEnd.Models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "variety")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Variety {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "variety_id")
    private UUID varietyId;
    @Column(nullable = false, name = "variety_name")
    private String varietyName;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "fruit_id", referencedColumnName = "fruit_id")
    private Fruit fruit;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id", referencedColumnName = "user_id")
    private User user;


}
