package com.example.firstcrud.Relationships.Employee.Orders.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class ProductPurchase {
    @Id
    private Integer pid;
    private String name;
    private Integer price;
    private String description;
    private Integer quantity;
}
