package com.edozo.codechallenge.service;

import com.edozo.codechallenge.domain.MapEntity;
import com.edozo.codechallenge.dto.CreateMapRequest;
import com.edozo.codechallenge.dto.MapDto;
import com.edozo.codechallenge.mapper.MapMapper;
import com.edozo.codechallenge.repository.MapRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

@Service
@Slf4j
public class MapServiceImpl implements MapService {

    @Resource
    private MapRepository mapRepository;

    @Resource
    private MapMapper mapMapper;

    @Override
    public Set<MapDto> getAll() {
        return mapRepository.getAll().stream()
                .map(this::toDto)
                .collect(toSet());
    }

    @Override
    public Optional<MapDto> get(Integer id) {
        return mapRepository.getAll().stream()
                .filter(map -> map.getId().equals(id))
                .findFirst()
                .map(this::toDto);
    }

    @Override
    public List<MapDto> search(String address) {
        return mapRepository.getAll().stream()
                .filter(map -> map.getAddress().contains(address))
                .map(this::toDto)
                .collect(toList());
    }

    @Override
    public void save(CreateMapRequest createMapRequest) {
        MapEntity mapEntity = mapMapper.toEntity(createMapRequest);
        mapRepository.save(mapEntity);
    }

    private MapDto toDto(MapEntity mapEntity) {
        return mapMapper.toDto(mapEntity);
    }
}
