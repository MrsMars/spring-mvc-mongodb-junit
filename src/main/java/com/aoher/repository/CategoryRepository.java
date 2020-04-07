package com.aoher.repository;

import com.aoher.domain.CategoryEntity;
import com.aoher.repository.abstracts.BaseRepository;

public interface CategoryRepository extends BaseRepository<CategoryEntity> {
    CategoryEntity findByName(String name);
}
