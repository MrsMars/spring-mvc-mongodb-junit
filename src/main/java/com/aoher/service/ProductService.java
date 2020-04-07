package com.aoher.service;

import com.aoher.domain.ProductEntity;
import com.aoher.repository.ProductRepository;
import com.aoher.service.abstracts.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService extends BaseService<ProductEntity,ProductRepository> {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductEntity> findByName(String name) {
        return productRepository.findByName(name);
    }

    public List<ProductEntity> findByCategoryId(Long id) {
        return productRepository.findByCategoryId(id);
    }


    protected ProductRepository getRepo() {
        return productRepository;
    }

    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
