package com.edozo.codechallenge.controllers;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public class MapDto {

    private final int id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MapDto mapDto = (MapDto) o;
        return id == mapDto.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
