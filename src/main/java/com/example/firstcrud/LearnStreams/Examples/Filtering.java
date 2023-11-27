package com.example.firstcrud.LearnStreams.Examples;

import com.example.firstcrud.LearnStreams.EntityDtos.Car;
import com.example.firstcrud.LearnStreams.EntityDtos.Person;
import com.example.firstcrud.LearnStreams.MockData.MockData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Filtering {
    //todo find even number
    public static void dropWhile() throws Exception{
//        Stream.of(2,4,5,8,11,6).filter(
//                n -> n % 2 == 0
//        ).forEach(n -> System.out.println(n + " "));
        Stream.of(2,4,5,8,11,6).dropWhile(
                //skips values until the condition is met ....skips the non-even number
                n -> n % 2 == 0
        ).forEach(n -> System.out.println(n + " "));
    }

    //returns
    public static void takeWhile(){
//        Stream.of(3,2,5,17,10,8).filter(n -> n % 2 == 1)
//                .forEach(System.out::println);
        Stream.of(3,9,5,4,5,9,11,15).takeWhile(n -> n % 2 == 1)
                .forEach(System.out::println);
        //returns when condition becomes false when condition is met
    }

    public static void findFirst(){
        int[] number = {3,2,3,5,5,4,9,4};
        int result = Arrays.stream(number)
                .filter(n -> n == 5)
                .findFirst()
                .orElse(-1);
        System.out.println(result);
    }

    public static void findAny(){
        int[] number = {3,2,3,5,5,4,9,4};
        int result = Arrays.stream(number)
                .filter(n -> n == 5)
                .findAny()
                .orElse(-1);
        System.out.println(result);
    }
    //todo allMatch,anyMatch

    public static void filterCars() throws Exception{
        List<Car> cars = MockData.getCars();
        Predicate<Car> carPredicate = car -> car.getPrice() < 20_0000.00;
        Predicate<Car> yelllow = car -> car.getColor().equals("Yellow");


        List<Car> filtredLessAndYellow = cars.stream()
                .filter(carPredicate)
                .filter(yelllow)
                .collect(Collectors.toList());
//        filtredLessAndYellow.forEach(System.out::println);
        List<Car> fallOut = cars.stream()
                .filter(car -> car.getPrice() < 20_000.00)
                .filter(car -> car.getColor().equals("Yellow"))
                .collect(Collectors.toList());
        fallOut.forEach(System.out::println);
    }
    public static void filtredPerson() throws Exception{
        List<Person> people = MockData.getPeople();
        List filteredPeople = people.stream()
                .filter(person -> person.getGender().equals("Male"))
                .filter(person -> person.getAge() > 20)
                .collect(Collectors.toList());
        filteredPeople.forEach(System.out::println);
    }

    //imperative approach to filter
    public static void imperativeFilter() throws IOException {
        try {
            List<Person> people = MockData.getPeople();
            List<Person> youngPeople = new ArrayList<>();
            int limit = 10;
            int youngeAge = 18;
            int counter = 0;
            for(Person person: people){
               if(person.getAge() <= youngeAge){
                   youngPeople.add(person);
                   counter++;
               }
               if(counter == limit){
                   break;
               }
            }
            youngPeople.forEach(System.out::println);
            youngPeople.stream().count();
//            youngPeople.size();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //declarative approach
    public static void declarativeApproach() throws Exception{
        List<Person> people = MockData.getPeople();
        List<Person> youth = people.stream()
                .filter(person -> person.getAge() <= 18)
                .limit(10)
                .collect(Collectors.toList());
        youth.stream().count();
        youth.forEach(System.out::println);

    }


    public static void main(String[] args) throws Exception {
//        Filtering.dropWhile();
//        Filtering.takeWhile();
//        Filtering.findFirst();
//        Filtering.findAny();
//        Filtering.filterCars();
//        Filtering.filtredPerson();
        Filtering.declarativeApproach();
    }

}
