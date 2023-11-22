package com.example.firstcrud.Workbook.DataPesister;

import com.example.firstcrud.Workbook.Entity.Ifmis;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DataPersister {
    private final EntityManager entityManager;

    public DataPersister(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void insertValue(List<Ifmis> values){
        for(Ifmis entity: values){
            entityManager.persist(entity);
        }
    }
}
