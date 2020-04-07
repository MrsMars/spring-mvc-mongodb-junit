package com.aoher.controller;

import com.aoher.domain.CategoryEntity;
import com.aoher.domain.ProductEntity;
import com.aoher.service.CategoryService;
import com.aoher.service.ProductService;
import com.aoher.utils.BaseEntityList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AppController {

    private static final String ATTRIBUTE_CATEGORIES = "categories";
    private static final String ATTRIBUTE_PRODUCTS = "products";
    private static final String ATTRIBUTE_SELECTED_CATEGORY_ID = "selectedCategoryId";

    private BaseEntityList<ProductEntity> cart;
    private BaseEntityList<CategoryEntity> categories;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @PostConstruct
    public void init() {
        categoryService.deleteAll();
        productService.deleteAll();
        cart = new BaseEntityList<>();

        for (int i = 1; i < 4; i++) {
            CategoryEntity c = new CategoryEntity((long) i,"Category "+i);
            c = categoryService.create(c);
            List<ProductEntity> products = new ArrayList<>();
            for (int j = 1; j < 21; j++) {
                ProductEntity p = new ProductEntity(
                        Long.valueOf(i + "" + j),
                        "Product " + i + "" + j,
                        (i) + " x " + (i * j / 2),
                        "",
                        j * j * 1000d, c);
                products.add(p);
            }
            productService.save(products);
        }
        categories = categoryService.findAll();
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String welcome(ModelMap model) {
        if (categories == null) {
            categories = categoryService.findAll();
        }

        Long selectedCategoryId = categories.get(0).getId();

        model.addAttribute(ATTRIBUTE_CATEGORIES, categories);
        model.addAttribute(ATTRIBUTE_PRODUCTS, productService.findByCategoryId(selectedCategoryId));
        model.addAttribute(ATTRIBUTE_SELECTED_CATEGORY_ID, selectedCategoryId);
        return "index";
    }

    @GetMapping("/category/{id}")
    public String onCategorySelect(@PathVariable Long id, ModelMap model) {
        model.addAttribute(ATTRIBUTE_CATEGORIES, categories);
        model.addAttribute(ATTRIBUTE_PRODUCTS, productService.findByCategoryId(id));
        model.addAttribute(ATTRIBUTE_SELECTED_CATEGORY_ID, id);
        return "index";
    }
}
