package com.aoher.repository.abstracts;

import com.aoher.domain.abstracts.BaseEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BaseRepository<T extends BaseEntity> extends MongoRepository<T, Long> {
}