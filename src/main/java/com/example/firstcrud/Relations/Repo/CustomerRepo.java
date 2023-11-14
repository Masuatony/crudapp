package com.example.firstcrud.Relations.Repo;

import com.example.firstcrud.Relations.Entity.Customer;
import com.example.firstcrud.Relations.Entity.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {
//    @Query("SELECT c.name, p.productName FROM Customer c JOIN c.product p")
//    public String
}
