package com.example.firstcrud.Relations.DTO;

import com.example.firstcrud.Relations.Entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderReqsts {
    private Customer customer;
}
