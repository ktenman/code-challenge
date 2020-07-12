package com.edozo.codechallenge.dao;

import com.edozo.codechallenge.MapEntity;

import java.util.List;
import java.util.Optional;

public interface MapDao {

    List<MapEntity> getAll();

    Optional<MapEntity> get(Integer id);

    Optional<MapEntity> search(String address);

    void save(MapEntity mapEntity);

}
