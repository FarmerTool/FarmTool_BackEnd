package com.pipertzis.FarmHelper_BackEnd;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AbstractClass {

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
