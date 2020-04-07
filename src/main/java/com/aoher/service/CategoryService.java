package com.aoher.service;

import com.aoher.domain.CategoryEntity;
import com.aoher.repository.CategoryRepository;
import com.aoher.service.abstracts.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoryService extends BaseService<CategoryEntity, CategoryRepository> {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryEntity create(CategoryEntity category){
        return categoryRepository.save(category);
    }

    public void deleteAll(){
        categoryRepository.deleteAll();
    }

    protected CategoryRepository getRepo(){
        return categoryRepository;
    }

    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
}