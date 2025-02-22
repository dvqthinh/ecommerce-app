package com.ecommerce.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Product, Integer> items = new HashMap<>();

    public void addProduct(Product product, int quantity) {
        items.put(product, items.getOrDefault(product, 0) + quantity);
    }

    public void removeProduct(Product product) {
        items.remove(product);
    }

    public void updateQuantity(Product product, int quantity) {
        if (quantity <= 0) {
            removeProduct(product);
        } else {
            items.put(product, quantity);
        }
    }

    public Map<Product, Integer> getItems() {
        return new HashMap<>(items);
    }

    public double getTotal() {
        return items.entrySet().stream()
            .mapToDouble(entry -> entry.getKey().getPrice().doubleValue() * entry.getValue())
            .sum();
    }

    public void clear() {
        items.clear();
    }
}
