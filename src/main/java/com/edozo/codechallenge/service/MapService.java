package com.edozo.codechallenge.service;

import com.edozo.codechallenge.dto.CreateMapRequest;
import com.edozo.codechallenge.dto.MapRequest;
import com.edozo.codechallenge.dto.MapDto;
import com.edozo.codechallenge.dto.UpdateMapRequest;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface MapService {

    Set<MapDto> getAll();

    Optional<MapDto> get(Integer id);

    List<MapDto> search(String address);

    void save(CreateMapRequest createMapRequest);

    void update(UpdateMapRequest updateMapRequest);

}
