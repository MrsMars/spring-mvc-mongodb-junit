package com.aoher.service.abstracts;

import com.aoher.domain.abstracts.BaseEntity;
import com.aoher.repository.abstracts.BaseRepository;
import com.aoher.utils.BaseEntityList;

import java.util.List;
import java.util.Optional;

public abstract class BaseService<T extends BaseEntity, R extends BaseRepository<T>> {

    public Optional<T> findById(Long id){
        return getRepo().findById(id);
    }

    public BaseEntityList<T> findAll() {
        return toBaseList(getRepo().findAll());
    }

    public BaseEntityList<T> toBaseList(List<T> list){
        return new BaseEntityList<>(list);
    }

    public void deleteAll(){
        getRepo().deleteAll();
    }

    public List<T> save(List<T> entity) {
        return getRepo().insert(entity);
    }

    abstract R getRepo();
}
