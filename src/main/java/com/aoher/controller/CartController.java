package com.aoher.controller;

import com.aoher.cart.CartElement;
import com.aoher.cart.CartOperationStrategy;
import com.aoher.cart.impl.AddToCart;
import com.aoher.cart.impl.SubtractFromCart;
import com.aoher.service.ProductService;
import com.aoher.utils.BaseEntityList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@Controller
@SessionAttributes({"cart"})
@Scope(value = "session")
@RequestMapping("/cart")
public class CartController {

    private BaseEntityList<CartElement> cart;
    private CartOperationStrategy strategy;

    @Autowired
    private ProductService service;

    @PostConstruct
    public void init() {
        cart = new BaseEntityList<>();
    }

    @GetMapping("/get")
    public @ResponseBody BaseEntityList<CartElement> getCart() {
        return cart;
    }

    @GetMapping("/updateProductAmountInCart/{id}/{amount}/{override}")
    public @ResponseBody BaseEntityList<CartElement> updateProductAmountInCart(
            @PathVariable Long id, @PathVariable int amount, @PathVariable boolean override) {
        strategy = new AddToCart(amount, override);
        return strategy.updateList(service.findById(id).orElse(null), cart);
    }

    @GetMapping("/subtractProductFromCart/{id}")
    public @ResponseBody BaseEntityList<CartElement> subtractProductFromCart(
            @PathVariable Long id) {
        strategy = new SubtractFromCart(1);
        return strategy.updateList(service.findById(id).orElse(null), cart);
    }

    @GetMapping("/removeProductFromCart/{id}")
    public @ResponseBody BaseEntityList<CartElement> removeProductFromCart(
            @PathVariable Long id) {
        strategy = new SubtractFromCart(true);
        return strategy.updateList(service.findById(id).orElse(null), cart);
    }

    @DeleteMapping("/emtyCart")
    public @ResponseBody BaseEntityList<CartElement> emptyCart() {
        cart = new BaseEntityList<>();
        return cart;
    }
}
