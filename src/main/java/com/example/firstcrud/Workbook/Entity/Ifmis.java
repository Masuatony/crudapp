package com.example.firstcrud.Workbook.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
public class Ifmis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer voucher;
    private String accountDetails;
    private String subItemDescription;
    private String payyee;
    private String paymentDate;
    private Double amount;
    private String status;

    public Ifmis(List<Ifmis> transactions) {
    }
}
