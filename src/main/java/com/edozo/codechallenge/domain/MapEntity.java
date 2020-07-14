package com.edozo.codechallenge.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@Builder
@Getter
public class MapEntity {
    @EqualsAndHashCode.Include
    @Setter
    private Integer id;
    private final Integer userId;
    private final String address;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final String downloadUrl;
    private final Double bottomLeftX;
    private final Double bottomLeftY;
    private final Double topRightX;
    private final Double topRightY;
}
