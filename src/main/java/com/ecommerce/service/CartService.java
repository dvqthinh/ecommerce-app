package com.ecommerce.service;

import com.ecommerce.model.Cart;
import com.ecommerce.model.Product;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    private final Cart cart = new Cart();

    public void addProduct(Product product, int quantity) {
        cart.addProduct(product, quantity);
    }

    public void removeProduct(Product product) {
        cart.removeProduct(product);
    }

    public void updateQuantity(Product product, int quantity) {
        cart.updateQuantity(product, quantity);
    }

    public Cart getCart() {
        return cart;
    }

    public void clearCart() {
        cart.clear();
    }
}
