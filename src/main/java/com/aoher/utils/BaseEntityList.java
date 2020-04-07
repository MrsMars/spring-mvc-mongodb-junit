package com.aoher.utils;

import com.aoher.domain.abstracts.BaseEntity;

import java.util.ArrayList;
import java.util.Collection;

public class BaseEntityList<T extends BaseEntity> extends ArrayList<T> {

    public BaseEntityList() {
        super();
    }

    public BaseEntityList(Collection<? extends T> c) {
        super(c);
    }

    public T get(Long entityId){
        int ind = indexOf(entityId);
        return ind > -1 ? get(ind) : null;

    }

    public boolean remove(Long id){
        int ind = indexOf(id);
        if (ind>-1) {
            remove(ind);
            return true;
        }
        return false;
    }

    public int indexOf(Long entityId){
        return indexOf(new BaseEntity(entityId));
    }
}