package com.example.firstcrud.Configuration.Requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStatus {
    Long id;
    String status;
}
