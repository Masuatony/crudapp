package com.example.firstcrud.Relations.Repo;

import com.example.firstcrud.Relations.Entity.Customer;
import com.example.firstcrud.Relations.Entity.Items;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo extends JpaRepository<Items, Integer> {
}
