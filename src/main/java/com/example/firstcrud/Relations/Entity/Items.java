package com.example.firstcrud.Relations.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Items {
    @Id
    private int id;
    private String productName;
    private int quantity;
    private Long price;
}
