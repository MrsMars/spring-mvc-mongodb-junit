package com.aoher.controller;

import com.aoher.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, ModelMap model) {

        model.addAttribute("product", productService.findById(id));
        return "product-detail";
    }

    public void setService(ProductService service) {
        this.productService = service;
    }
}
