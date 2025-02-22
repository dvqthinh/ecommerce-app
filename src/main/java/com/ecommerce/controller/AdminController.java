package com.ecommerce.controller;

import com.ecommerce.model.Category;
import com.ecommerce.model.Product;
import com.ecommerce.service.CategoryService;
import com.ecommerce.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ProductService productService;
    private final CategoryService categoryService;

    public AdminController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/products")
    public String manageProducts(
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size,
            Model model) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        
        Page<Product> productPage = productService.getAllProducts(
            PageRequest.of(currentPage - 1, pageSize)
        );
        
        model.addAttribute("products", productPage.getContent());
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", productPage.getTotalPages());
        return "admin/products";
    }

    @GetMapping("/products/new")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin/product-form";
    }

    @PostMapping("/products")
    public String addProduct(@ModelAttribute Product product, RedirectAttributes redirectAttributes) {
        Product savedProduct = productService.addProduct(product);
        redirectAttributes.addFlashAttribute("message", 
            "Product '" + savedProduct.getName() + "' added successfully!");
        return "redirect:/admin/products";
    }

    @GetMapping("/products/edit/{id}")
    public String showEditProductForm(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin/product-form";
    }

    @PostMapping("/products/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute Product product, 
            RedirectAttributes redirectAttributes) {
        product.setId(id);
        Product updatedProduct = productService.addProduct(product);
        redirectAttributes.addFlashAttribute("message", 
            "Product '" + updatedProduct.getName() + "' updated successfully!");
        return "redirect:/admin/products";
    }

    @PostMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        productService.removeProduct(id);
        redirectAttributes.addFlashAttribute("message", 
            "Product deleted successfully!");
        return "redirect:/admin/products";
    }

    @GetMapping("/categories")
    public String manageCategories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin/categories";
    }

    @GetMapping("/categories/new")
    public String showAddCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "admin/category-form";
    }

    @PostMapping("/categories")
    public String addCategory(@ModelAttribute Category category, RedirectAttributes redirectAttributes) {
        Category savedCategory = categoryService.createCategory(category);
        redirectAttributes.addFlashAttribute("message", 
            "Category '" + savedCategory.getName() + "' added successfully!");
        return "redirect:/admin/categories";
    }

    @GetMapping("/categories/edit/{id}")
    public String showEditCategoryForm(@PathVariable Long id, Model model) {
        Category category = categoryService.getCategoryById(id);
        model.addAttribute("category", category);
        return "admin/category-form";
    }

    @PostMapping("/categories/{id}")
    public String updateCategory(@PathVariable Long id, @ModelAttribute Category category, 
            RedirectAttributes redirectAttributes) {
        category.setId(id);
        Category updatedCategory = categoryService.updateCategory(id, category);
        redirectAttributes.addFlashAttribute("message", 
            "Category '" + updatedCategory.getName() + "' updated successfully!");
        return "redirect:/admin/categories";
    }

    @PostMapping("/categories/delete/{id}")
    public String deleteCategory(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        categoryService.deleteCategory(id);
        redirectAttributes.addFlashAttribute("message", 
            "Category deleted successfully!");
        return "redirect:/admin/categories";
    }

    @GetMapping("/users")
    public String viewUsers() {
        return "admin/users";
    }
}
