package com.pipertzis.FarmHelper_BackEnd.Models.ModelDTO;


import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserDTO {

    private UUID userId;
    private String username;
    private String surname;
    private String email;
    private String phoneNumber;


}
