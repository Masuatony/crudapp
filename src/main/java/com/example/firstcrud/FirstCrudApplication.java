package com.example.firstcrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@SpringBootApplication
//@EnableSwagger2
//@EnableSwagger2
public class FirstCrudApplication {
    public static void main(String[] args) {
        SpringApplication.run(FirstCrudApplication.class, args);

//        String sentense = "The Red Fox Jumped Fox Over The Fense:";

//        List<String> filtered = Arrays.StreaM()
        System.out.println("Initialization Okay");

    }

}
