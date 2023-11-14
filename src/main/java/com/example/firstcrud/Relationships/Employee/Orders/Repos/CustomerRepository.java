package com.example.firstcrud.Relationships.Employee.Orders.Repos;

import com.example.firstcrud.Relationships.Employee.Orders.Models.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface CustomerRepository extends JpaRepository<Customers, Integer> {
}
