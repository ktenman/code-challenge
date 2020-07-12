package com.edozo.codechallenge.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Getter
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
    @Getter
    public static class Coordinates {
        private final Double bottomLeftX;
        private final Double bottomLeftY;
        private final Double topRightX;
        private final Double topRightY;
    }
}
