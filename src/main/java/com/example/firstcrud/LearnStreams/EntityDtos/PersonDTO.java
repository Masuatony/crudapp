package com.example.firstcrud.LearnStreams.EntityDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class PersonDTO {
    private final Integer id;
    private final String name;
    private final Integer age;

    public static PersonDTO map(Person person){
        return new PersonDTO(
                person.getId(),
                person.getFirstName(),
                person.getAge()
        );
    }
}
