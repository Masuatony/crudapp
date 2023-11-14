package com.example.firstcrud.Relationships.Employee.Orders.DTOS;

import com.example.firstcrud.Relationships.Employee.Orders.Models.Customers;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.lang.annotation.Target;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderRequest {
    private Customers customers;
}
