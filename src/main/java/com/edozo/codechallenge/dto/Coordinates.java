package com.edozo.codechallenge.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Coordinates {
    @NotNull
    private Double bottomLeftX;
    @NotNull
    private Double bottomLeftY;
    @NotNull
    private Double topRightX;
    @NotNull
    private Double topRightY;
}
