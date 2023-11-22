package com.example.firstcrud.GovernorMinistry.Repository;

import com.example.firstcrud.GovernorMinistry.Models.Governor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GovernorRepo extends JpaRepository<Governor,Long> {

}
