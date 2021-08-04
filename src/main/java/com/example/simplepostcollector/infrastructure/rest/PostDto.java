package com.example.simplepostcollector.infrastructure.rest;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PostDto {

    private Long id;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long userId;
    private String title;
    private String body;

}
