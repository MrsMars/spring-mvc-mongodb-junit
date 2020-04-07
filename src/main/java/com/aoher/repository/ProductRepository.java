package com.aoher.repository;

import com.aoher.domain.ProductEntity;
import com.aoher.repository.abstracts.BaseRepository;

import java.util.List;

public interface ProductRepository  extends BaseRepository<ProductEntity> {

    List<ProductEntity> findByName(String name);
    List<ProductEntity> findByCategoryId(Long id);
}