package com.example.firstcrud.Product.Repository;

import com.example.firstcrud.Product.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends JpaRepository<Product,Integer> {
    Product findByName( String name);
//    @Query("")
}
