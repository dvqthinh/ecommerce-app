package com.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.ecommerce.model.Product;
import com.ecommerce.service.CartService;
import com.ecommerce.service.ProductService;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/products")
    public String viewProducts() {
        return "user/products";
    }

    @GetMapping("/cart")
    public String viewCart() {
        return "user/cart";
    }

    @GetMapping("/checkout")
    public String checkout() {
        return "user/checkout";
    }
}
