package com.example.firstcrud.Relationships.Employee;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String identifier;
    private String firstName;
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;
    private String birthCode;
    private String role;
    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    @ManyToMany()
    @JoinTable(name = "employee_mission",
    joinColumns = @JoinColumn(name = "employee_id"),
    inverseJoinColumns = @JoinColumn(name = "mission_id"))
    private List<Mission> missions;
}
