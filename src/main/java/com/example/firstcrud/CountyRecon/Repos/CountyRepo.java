package com.example.firstcrud.CountyRecon.Repos;

import com.example.firstcrud.CountyRecon.Model.County;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountyRepo extends JpaRepository<County,Long> {
    interface getAnalytics{

    }
}
