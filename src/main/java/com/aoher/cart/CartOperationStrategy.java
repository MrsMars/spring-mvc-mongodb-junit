package com.aoher.cart;

import com.aoher.domain.ProductEntity;
import com.aoher.utils.BaseEntityList;

public interface CartOperationStrategy {
    BaseEntityList<CartElement> updateList(ProductEntity p, BaseEntityList<CartElement> list);
}
