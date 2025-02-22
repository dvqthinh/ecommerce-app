package com.ecommerce.config;

import com.ecommerce.model.Category;
import com.ecommerce.model.Product;
import com.ecommerce.model.Role;
import com.ecommerce.model.User;
import com.ecommerce.repository.CategoryRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.repository.RoleRepository;
import com.ecommerce.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.Set;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(CategoryRepository categoryRepository, 
                                 ProductRepository productRepository,
                                 RoleRepository roleRepository,
                                 UserRepository userRepository) {
        return args -> {
            // Create sample categories if they don't exist
            Category electronics = categoryRepository.findByName("Electronics");
            if (electronics == null) {
                electronics = new Category();
                electronics.setName("Electronics");
                electronics.setDescription("Electronic devices and accessories");
                categoryRepository.save(electronics);
            }

            Category clothing = categoryRepository.findByName("Clothing");
            if (clothing == null) {
                clothing = new Category();
                clothing.setName("Clothing");
                clothing.setDescription("Men's and women's clothing");
                categoryRepository.save(clothing);
            }

            // Create sample products
            Product laptop = new Product();
            laptop.setName("Laptop");
            laptop.setDescription("15-inch laptop with 16GB RAM");
            laptop.setPrice(new BigDecimal("999.99"));
            laptop.setStockQuantity(10);
            laptop.setImageUrl("/images/laptop.jpg");
            laptop.setCategory(electronics);
            productRepository.save(laptop);

            Product smartphone = new Product();
            smartphone.setName("Smartphone");
            smartphone.setDescription("Latest model with 128GB storage");
            smartphone.setPrice(new BigDecimal("699.99"));
            smartphone.setStockQuantity(20);
            smartphone.setImageUrl("/images/smartphone.jpg");
            smartphone.setCategory(electronics);
            productRepository.save(smartphone);

            Product tshirt = new Product();
            tshirt.setName("T-Shirt");
            tshirt.setDescription("Cotton t-shirt, various colors");
            tshirt.setPrice(new BigDecimal("19.99"));
            tshirt.setStockQuantity(50);
            tshirt.setImageUrl("/images/tshirt.jpg");
            tshirt.setCategory(clothing);
            productRepository.save(tshirt);

            // Create roles
            Role adminRole = new Role("ROLE_ADMIN");
            Role userRole = new Role("ROLE_USER");
            roleRepository.save(adminRole);
            roleRepository.save(userRole);

            // Create admin user if not exists
            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword("$2a$10$8.UnVuG9HHgffUDAlk8qfOuDk9r8GZgR3J3mIMU/3Jq1Kx5CQzQwW"); // password: admin123
                admin.setEmail("admin@example.com");
                admin.setEnabled(true);
                admin.setRoles(Set.of(adminRole));
                userRepository.save(admin);
            }

            // Create regular users if not exists
            if (userRepository.findByUsername("user1").isEmpty()) {
                User user1 = new User();
                user1.setUsername("user1");
                user1.setPassword("$2a$10$8.UnVuG9HHgffUDAlk8qfOuDk9r8GZgR3J3mIMU/3Jq1Kx5CQzQwW"); // password: user123
                user1.setEmail("user1@example.com");
                user1.setEnabled(true);
                user1.setRoles(Set.of(userRole));
                userRepository.save(user1);
            }

            if (userRepository.findByUsername("user2").isEmpty()) {
                User user2 = new User();
                user2.setUsername("user2");
                user2.setPassword("$2a$10$8.UnVuG9HHgffUDAlk8qfOuDk9r8GZgR3J3mIMU/3Jq1Kx5CQzQwW"); // password: user123
                user2.setEmail("user2@example.com");
                user2.setEnabled(true);
                user2.setRoles(Set.of(userRole));
                userRepository.save(user2);
            }

            if (userRepository.findByUsername("manager").isEmpty()) {
                User manager = new User();
                manager.setUsername("manager");
                manager.setPassword("$2a$10$8.UnVuG9HHgffUDAlk8qfOuDk9r8GZgR3J3mIMU/3Jq1Kx5CQzQwW"); // password: user123
                manager.setEmail("manager@example.com");
                manager.setEnabled(true);
                manager.setRoles(Set.of(userRole, adminRole));
                userRepository.save(manager);
            }
        };
    }
}
