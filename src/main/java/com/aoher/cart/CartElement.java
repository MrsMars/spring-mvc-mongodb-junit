package com.aoher.cart;

import com.aoher.domain.abstracts.BaseEntity;
import com.aoher.domain.ProductEntity;

public class CartElement extends BaseEntity {

    private ProductEntity product;
    private int amount;

    public CartElement(ProductEntity product, int amount) {
        this.product = product;
        this.amount = amount;
        this.setId(product.getId());
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
