package com.edozo.codechallenge.controller;

import com.edozo.codechallenge.dto.CreateMapRequest;
import com.edozo.codechallenge.dto.MapDto;
import com.edozo.codechallenge.service.MapService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/maps")
public class MapController {

    @Resource
    private MapService mapService;

    @GetMapping("/{id}")
    public ResponseEntity<MapDto> getMapById(@PathVariable("id") final int id) {
        return mapService.getMapById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public Set<MapDto> getAllMaps() {
        return mapService.getAll();
    }

    @PostMapping
    public ResponseEntity save(@RequestBody @Valid CreateMapRequest createMapRequest) {
        mapService.save(createMapRequest);
        return ResponseEntity.ok().build();
    }

}
