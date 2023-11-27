package com.example.firstcrud.LearnStreams.Examples;

import com.example.firstcrud.LearnStreams.EntityDtos.Car;
import com.example.firstcrud.LearnStreams.EntityDtos.Person;
import com.example.firstcrud.LearnStreams.MockData.MockData;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortingStreams {
    //psvm
    //sorting aligorithms

    public static void main(String[] args) throws Exception {
//        SortingStreams.topTenExpensiveCars();
        SortingStreams.sortingStreamObjects();
    }
//    public static void traditionalSort() throws Exception{
//        List<Person> cars = MockData.getPeople();
//        List<Car> sortedCArs = cars.stream()
//                .map(Person::getFirstName)
//                .re
//                .collect(Collectors.toList());
//
//    }
    public static void topTenExpensiveCars() throws Exception{
        List<Car> cars = MockData.getCars();
        List<Car> topTen = cars.stream()
                .filter(car -> car.getYear() > 2010)
                .sorted(Comparator.comparing(Car::getPrice).reversed())
                .limit(10)
                .collect(Collectors.toList());
        topTen.forEach(System.out::println);
    }

    public static void sortingStreamObjects() throws Exception{
        List<Person> people = MockData.getPeople();
        Comparator<Person> comparator = Comparator
                .comparing(Person::getEmail)
                .reversed()
                .thenComparing(Person::getFirstName);
        List<Person> sort = people.stream()
                .sorted(comparator)
                .limit(10)
                .collect(Collectors.toList());
        sort.forEach(System.out::println);
    }
}
