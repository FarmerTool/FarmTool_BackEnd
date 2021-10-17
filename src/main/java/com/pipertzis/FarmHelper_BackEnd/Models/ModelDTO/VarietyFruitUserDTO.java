package com.pipertzis.FarmHelper_BackEnd.Models.ModelDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VarietyFruitUserDTO {

    private UUID varietyId;
    private String varietyName;
    private String fruitName;
    private String username;
}
