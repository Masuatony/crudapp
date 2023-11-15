package com.example.firstcrud.Configuration.Requests;

import com.example.firstcrud.Configuration.Data.Student;
import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
//@RequiredArgsConstructor
public class CreateStudent {
    private Student createStudent;
}
