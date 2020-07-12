package com.edozo.codechallenge.controller;

import com.edozo.codechallenge.dto.MapDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/maps/")
public class MapController {

    @GetMapping("{id}")
    public MapDto getMapById(@PathVariable("id") final int id) {

        return MapDto.builder().id(id).build();
    }

}
