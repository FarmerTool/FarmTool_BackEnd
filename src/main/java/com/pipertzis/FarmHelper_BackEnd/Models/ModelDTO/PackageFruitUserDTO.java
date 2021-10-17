package com.pipertzis.FarmHelper_BackEnd.Models.ModelDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PackageFruitUserDTO {

    private UUID packageId;
    private String packageName;
    private String fruitName;
    private String username;
}
