package com.example.firstcrud.Product.Model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ProductEnum {
    SCUTA_NEW("dacuta", "bike","speed"),
    F1_FORMULA("london","rich","super speed");
    private final String name;
    private final String category;
    private final String runnable;

}
