package com.edozo.codechallenge.controller;

import com.edozo.codechallenge.dto.MapDto;
import com.edozo.codechallenge.service.MapService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Resource
    private MapService mapService;

    @GetMapping
    public List<MapDto> search(@RequestParam final String address) {
        return mapService.search(address);
    }

}
