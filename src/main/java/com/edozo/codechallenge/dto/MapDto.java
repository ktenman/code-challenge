package com.edozo.codechallenge.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MapDto {

    private final Integer id;
    private final Integer userId;
    private final String address;
    private final Coordinates coordinates;
    private final String downloadUrl;

}
