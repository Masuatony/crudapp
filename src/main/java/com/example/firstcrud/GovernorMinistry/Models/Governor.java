package com.example.firstcrud.GovernorMinistry.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Governor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer voucher;
    private String accountDetails;
    private String subItemDescription;
    private String payyee;
    private Date paymentDate;
    private Double amount;
    private String status;
    private Double amountVat;
    private Double amountString;
    private Double amountDev;
    private String particulars;
    private String particulars1;
}
