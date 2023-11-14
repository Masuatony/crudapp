package com.example.firstcrud.GL;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Gl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountNumber;
    private String generalLedger;
    private String currency;
    private String glName;
    private String country;
    private String baseCountry;
    private String type;
//    private char modifiedFlag = 'N';
//    private Date date = new Date();
//    private String status = "Active";
    private ModifiedFlag modifiedFlag = ModifiedFlag.N;
    @Column(name = "status")
    private boolean isActive;
    private Date createdOn = new Date();
    private Date updatedOn;
//    private String status;
//    private Timestamp

}
