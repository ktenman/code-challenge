package com.edozo.codechallenge.repository;

import com.edozo.codechallenge.domain.MapEntity;
import com.edozo.codechallenge.exception.CodeChallengeException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.lang.Thread.currentThread;
import static org.apache.commons.csv.CSVFormat.DEFAULT;

@Service
@Slf4j
public class MapRepository {
    private static final String DATE_TIME_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final String INPUT_DATA_FILE = "maps.csv";
    private Set<MapEntity> maps;

    @PostConstruct
    public void init() {
        log.info("Initializing maps");
        try {
            InputStream inputStream = currentThread().getContextClassLoader().getResourceAsStream(INPUT_DATA_FILE);
            Iterable<CSVRecord> records = DEFAULT
                    .withIgnoreEmptyLines()
                    .withIgnoreSurroundingSpaces()
                    .withTrailingDelimiter()
                    .withHeader("headers")
                    .withFirstRecordAsHeader()
                    .parse(new InputStreamReader(Objects.requireNonNull(inputStream)));
            this.maps = StreamSupport.stream(records.spliterator(), false)
                    .map(this::toMapEntity)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            log.error("Failed to read from file {}", INPUT_DATA_FILE, e);
        }
    }

    private MapEntity toMapEntity(CSVRecord record) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_PATTERN);
        try {
            return MapEntity.builder()
                    .id(Integer.valueOf(record.get("id")))
                    .userId(Integer.valueOf(record.get("userId")))
                    .address(record.get("address"))
                    .createdAt(LocalDateTime.parse(record.get("createdAt"), dateTimeFormatter))
                    .updatedAt(LocalDateTime.parse(record.get("updatedAt"), dateTimeFormatter))
                    .downloadUrl(record.get("downloadUrl"))
                    .bottomLeftX(Double.valueOf(record.get("bottomLeftX")))
                    .bottomLeftY(Double.valueOf(record.get("bottomLeftY")))
                    .topRightX(Double.valueOf(record.get("topRightX")))
                    .topRightY(Double.valueOf(record.get("topRightY")))
                    .build();
        } catch (Exception e) {
            log.error("Failed to map record {}", record);
            return null;
        }
    }

    public Set<MapEntity> getAll() {
        return this.maps;
    }

    void setMaps(Set<MapEntity> maps) {
        this.maps = maps;
    }

    public void save(MapEntity mapEntity) {
        if (mapEntity.getId() == null) {
            mapEntity.setId(getNewId());
            getAll().add(mapEntity);
            return;
        }
        MapEntity foundMapEntity = this.maps.stream()
                .filter(m -> m.getId().equals(mapEntity.getId()))
                .findFirst()
                .orElseThrow(() -> new CodeChallengeException(String.format("MapEntity not found. %s", mapEntity)));
        getAll().remove(foundMapEntity);
        getAll().add(mapEntity);
    }

    private int getNewId() {
        return this.maps.stream()
                .map(MapEntity::getId)
                .max(Integer::compare)
                .orElse(0) + 1;
    }

}
