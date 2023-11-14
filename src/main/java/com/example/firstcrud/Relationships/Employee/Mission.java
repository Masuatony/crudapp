package com.example.firstcrud.Relationships.Employee;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter @Getter
public class Mission {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private int duration;
    @ManyToMany
    private List<Employee> employees;

}
