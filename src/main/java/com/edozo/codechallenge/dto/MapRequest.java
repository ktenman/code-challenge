package com.edozo.codechallenge.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class MapRequest {
    @NotNull
    private Integer userId;
    @NotBlank
    private String address;
    @NotBlank
    private String downloadUrl;
    @NotNull
    private Coordinates coordinates;
}
