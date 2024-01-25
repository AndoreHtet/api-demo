package com.example.firstapidemo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record StudentDto (
        int id,
       @JsonProperty("first_name") String firstName,
        @JsonProperty("last_name") String lastName,
        String email,
        String school

){

}
