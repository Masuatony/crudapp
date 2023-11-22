package com.example.firstcrud.Workbook.IfmisRepo;

import com.example.firstcrud.Workbook.Entity.Ifmis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IfmisRepo extends JpaRepository<Ifmis,Long> {
    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM ifmis")
    Integer getSizeOfdata();
}
