package com.edozo.codechallenge.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MapDto {

    private final Integer id;
    private final Integer userId;
    private final String address;
    private final Coordinates coordinates;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final String downloadUrl;

    @AllArgsConstructor
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Coordinates {
        private final Double bottomLeftX;
        private final Double bottomLeftY;
        private final Double topRightX;
        private final Double topRightY;
    }
}
