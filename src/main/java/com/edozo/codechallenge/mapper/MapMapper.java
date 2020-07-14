package com.edozo.codechallenge.mapper;

import com.edozo.codechallenge.domain.MapEntity;
import com.edozo.codechallenge.dto.Coordinates;
import com.edozo.codechallenge.dto.CreateMapRequest;
import com.edozo.codechallenge.dto.MapDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Clock;
import java.time.LocalDateTime;

@Service
public class MapMapper {

    @Resource
    private Clock clock;

    public MapDto toDto(MapEntity mapEntity) {
        Coordinates coordinates = Coordinates.builder()
                .bottomLeftX(mapEntity.getBottomLeftX())
                .bottomLeftY(mapEntity.getBottomLeftY())
                .topRightX(mapEntity.getTopRightX())
                .topRightY(mapEntity.getTopRightY())
                .build();
        return MapDto.builder()
                .id(mapEntity.getId())
                .userId(mapEntity.getUserId())
                .address(mapEntity.getAddress())
                .downloadUrl(mapEntity.getDownloadUrl())
                .coordinates(coordinates)
                .build();
    }

    public MapEntity toEntity(CreateMapRequest createMapRequest) {
        LocalDateTime now = LocalDateTime.now(clock);
        if (createMapRequest.getId() == null) {
            return MapEntity.builder()
                    .userId(createMapRequest.getUserId())
                    .address(createMapRequest.getAddress())
                    .downloadUrl(createMapRequest.getDownloadUrl())
                    .bottomLeftX(createMapRequest.getCoordinates().getBottomLeftX())
                    .bottomLeftY(createMapRequest.getCoordinates().getBottomLeftY())
                    .topRightX(createMapRequest.getCoordinates().getTopRightX())
                    .topRightY(createMapRequest.getCoordinates().getTopRightY())
                    .createdAt(now)
                    .updatedAt(now)
                    .build();
        }
        return MapEntity.builder()
                .id(createMapRequest.getId())
                .userId(createMapRequest.getUserId())
                .address(createMapRequest.getAddress())
                .downloadUrl(createMapRequest.getDownloadUrl())
                .bottomLeftX(createMapRequest.getCoordinates().getBottomLeftX())
                .bottomLeftY(createMapRequest.getCoordinates().getBottomLeftY())
                .topRightX(createMapRequest.getCoordinates().getTopRightX())
                .topRightY(createMapRequest.getCoordinates().getTopRightY())
                .updatedAt(LocalDateTime.now(clock))
                .build();
    }

}
