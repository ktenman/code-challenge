package com.edozo.codechallenge.repository;

import com.edozo.codechallenge.MapEntity;
import com.edozo.codechallenge.dao.MapDao;

import java.util.List;
import java.util.Optional;

public class MapRepository implements MapDao {
    @Override
    public List<MapEntity> getAll() {
        return null;
    }

    @Override
    public Optional<MapEntity> get(Integer id) {
        return Optional.empty();
    }

    @Override
    public Optional<MapEntity> search(String address) {
        return Optional.empty();
    }

    @Override
    public void save(MapEntity mapEntity) {

    }
}
