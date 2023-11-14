package com.example.firstcrud.Relationships.Employee.Orders.Models;

import com.example.firstcrud.Relations.DTO.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Customers {
    @Id
    @GeneratedValue
    private Integer id;
    private String firstName;
    private String lastName;
    @Column(nullable = false,unique = true)
    private String email;
    private String phoneNumber;
//    private Gender gender;
    @OneToMany(targetEntity = ProductPurchase.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "cust_fk", referencedColumnName = "id")
    private List<ProductPurchase> productPurchaseList;
}
