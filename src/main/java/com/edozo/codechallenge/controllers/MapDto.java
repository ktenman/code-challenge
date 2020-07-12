package com.edozo.codechallenge.controllers;

import java.util.Objects;

public class MapDto {

    private final int id;

    public MapDto(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

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
