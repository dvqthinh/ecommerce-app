package com.ecommerce.model;

import javax.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private Order order;
    
    @ManyToOne
    private Product product;
    
    private int quantity;
    private BigDecimal price;
}
