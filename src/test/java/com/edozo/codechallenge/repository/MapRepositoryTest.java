package com.edozo.codechallenge.repository;

import com.edozo.codechallenge.domain.MapEntity;
import com.edozo.codechallenge.exception.CodeChallengeException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class MapRepositoryTest {

    private MapRepository mapRepository = new MapRepository();

    private MapEntity mapEntity = MapEntity.builder()
            .id(100)
            .address("Cambridge Street 1")
            .build();

    @BeforeEach
    void setUp() {
        Set<MapEntity> maps = new HashSet<>();
        maps.add(mapEntity);
        mapRepository.setMaps(maps);
    }

    @Test
    void saveNewMap() {
        assertThat(mapRepository.getAll()).hasSize(1);
        MapEntity mapEntity = MapEntity.builder().build();

        mapRepository.save(mapEntity);

        assertThat(mapRepository.getAll()).hasSize(2)
                .extracting(MapEntity::getId)
                .containsExactlyInAnyOrder(100, 101);
    }

    @Test
    void updateMap() {
        assertThat(mapRepository.getAll()).hasSize(1);
        MapEntity mapEntity = MapEntity.builder().id(100).address("Oxford Street 2").build();

        mapRepository.save(mapEntity);

        assertThat(mapRepository.getAll())
                .hasSize(1)
                .extracting(MapEntity::getAddress)
                .contains("Oxford Street 2");
    }

    @Test
    @DisplayName("Throw exception when map not found")
    void throwExceptionWhenMapNotFound() {
        MapEntity mapEntity = MapEntity.builder().id(333).build();

        Throwable thrown = catchThrowable(() -> mapRepository.save(mapEntity));

        assertThat(thrown)
                .isInstanceOf(CodeChallengeException.class)
                .hasMessage("MapEntity not found. MapEntity(id=333, userId=null, address=null, createdAt=null, " +
                        "updatedAt=null, downloadUrl=null, bottomLeftX=null, bottomLeftY=null, topRightX=null, " +
                        "topRightY=null)");
    }
}
