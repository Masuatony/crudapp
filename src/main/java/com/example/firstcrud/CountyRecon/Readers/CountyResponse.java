package com.example.firstcrud.CountyRecon.Readers;

import com.example.firstcrud.CountyRecon.Model.County;
import lombok.*;

import java.util.List;

@Builder
//@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class CountyResponse {
    List<County> recurrent;
    List<County> development;
    List<County> deposits;
}
