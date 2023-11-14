package com.example.firstcrud.Product.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Table(name = "PRODUCT_TBL")
public class Product {
    private String name;
    private double amount;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @SequenceGenerator()
    private int id;
    private ProductEnum productEnum;
    @Column()
    private double  price;
}
