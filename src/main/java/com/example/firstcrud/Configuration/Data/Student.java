package com.example.firstcrud.Configuration.Data;

import com.example.firstcrud.Configuration.Constants.ConfigFlags;
import com.example.firstcrud.Configuration.Constants.GenderStudent;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String phone;
    @Column(nullable = false,unique = true)
    private String email;
    private GenderStudent gender;
    @Column(updatable = false)
    private ConfigFlags creationFlag;
    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedOn;
    private ConfigFlags modifiedFlag;
    private String status;
}
