package com.edozo.codechallenge.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MapController {

    @GetMapping("/maps/{id}")
    public MapDto getMapById(@PathVariable("id") final int id) {

        return MapDto.builder().id(id).build();
    }

}
