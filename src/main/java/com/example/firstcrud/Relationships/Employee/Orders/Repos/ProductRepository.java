package com.example.firstcrud.Relationships.Employee.Orders.Repos;

import com.example.firstcrud.Product.Model.Product;
import com.example.firstcrud.Relationships.Employee.Orders.Models.ProductPurchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface ProductRepository extends JpaRepository<ProductPurchase,Integer> {
    Product findByName(String name);
}
