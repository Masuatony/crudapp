package com.example.firstcrud.GovernorMinistry.FileReaders;

import com.example.firstcrud.GovernorMinistry.Models.Governor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class DevelopmentData {
    List<Governor> development = new ArrayList<>();
}
