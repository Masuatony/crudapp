package com.example.firstcrud.LearnStreams.Examples;

import com.example.firstcrud.LearnStreams.EntityDtos.Car;
import com.example.firstcrud.LearnStreams.EntityDtos.Person;
import com.example.firstcrud.LearnStreams.EntityDtos.PersonDTO;
import com.example.firstcrud.LearnStreams.MockData.MockData;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GroupingData {
    public static void main(String[] args) throws Exception {
//        GroupingData.UnderstandCollect();
//        GroupingData.indeterminate();
        GroupingData.groupAndCount();

    }

    public static void UnderstandCollect() throws Exception{
        List<String> emails = MockData.getPeople()
                .stream()
                .map(Person::getEmail)
                .limit(10)
                .collect(Collectors.toList());

        emails.forEach(System.out::println);
    }
    public static void indeterminate() throws Exception{
        System.out.println(
                MockData.getCars().stream()
                        .filter(car -> car.getPrice() <10000)
                        .map(price -> price.getPrice())
                        .limit(5)
                        .map(price -> price + (price * .14))
                        .collect(Collectors.toList())

        );
    }

    //simple
    public static void simpleMethod() throws Exception{
        List<Double> amounts = MockData.getCars().stream()
                .filter(car -> car.getPrice() <10000)
                .limit(10)
                .map(car -> car.getPrice() + (car.getPrice()) * .14)
                .collect(Collectors.toList());
        System.out.println(amounts);
    }

    public static void groupMapping() throws Exception{
        Map<String,List<Car>> mappedCars = MockData.getCars().stream()
                .limit(20)
                .collect(Collectors.groupingBy(Car::getMake));
        mappedCars.forEach((s,cars) ->{
            System.out.println("Make" +s);
            cars.forEach(System.out::println);
        });
    }

    public static void groupAndCount() throws Exception{
        List<String> names = List.of(
                "John",
                "John",
                "Mariam",
                "Alex",
                "Mohammado",
                "Mohammado",
                "Vincent",
                "Alex",
                "Alex"
        );
        Map<String,Long> map = names.stream()
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()));
        System.out.println(map);
    }




}
