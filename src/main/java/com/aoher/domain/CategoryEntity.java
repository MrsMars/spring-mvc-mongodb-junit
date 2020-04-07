package com.aoher.domain;

import com.aoher.domain.abstracts.BaseEntity;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class CategoryEntity extends BaseEntity {

    private String name;

    public CategoryEntity() {
        super();
    }

    public CategoryEntity(Long id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
