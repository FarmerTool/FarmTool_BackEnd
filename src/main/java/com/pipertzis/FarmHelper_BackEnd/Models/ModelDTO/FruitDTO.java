package com.pipertzis.FarmHelper_BackEnd.Models.ModelDTO;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class FruitDTO {

    private UUID fruitId;
    private String fruitName;
    private UUID userId;
    private String username;
    private String surname;

}
