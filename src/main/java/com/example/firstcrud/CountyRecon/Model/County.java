package com.example.firstcrud.CountyRecon.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class County {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String voucher;
    private String accountDetails;
    private String subItemDescription;
    private String payyee;
    private String paymentDate;
    private Double amount;
    private String status;
    private String reason;
    private String source;
}
