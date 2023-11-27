package com.example.firstcrud.LearnStreams.Examples;

import com.example.firstcrud.LearnStreams.EntityDtos.Car;
import com.example.firstcrud.LearnStreams.EntityDtos.Person;
import com.example.firstcrud.LearnStreams.MockData.MockData;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;

public class CountingAndStats {
    public static void main(String[] args) throws Exception {
        CountingAndStats.getCount();
        CountingAndStats.range();
//        CountingAndStats.minCars();
//        CountingAndStats.averageCars();
//        CountingAndStats.statistics();
//        CountingAndStats.loadSum();
    }
//    private List<Car> carList = MockData.getCars();

    public static void getCount() throws Exception{
        List<Car> cars = MockData.getCars();
                long count = cars.stream()
                .filter(car -> car.getPrice() <10000 && car.getYear() > 2010)
                .count();
        System.out.println("Count is");
        System.out.println(count);
    }

    public static void minCars() throws Exception{
        List<Car> cars = MockData.getCars();
        double min = cars.stream()
                .mapToDouble(Car::getPrice)
                .min()
                .orElse(0);
        System.out.println(min);
    }

    public static void averageCars() throws Exception{
        List<Car> cars = MockData.getCars();
        double average = cars.stream()
                .filter(car -> car.getYear() > 2010)
                .mapToDouble(Car::getPrice)
                .average()
                .orElse(0);
        System.out.println("Average is----");
        System.out.println(average);
    }
    public static void num() throws Exception{
        List<Car> cars = MockData.getCars();
        int count = (int) cars.stream()
                .filter(car -> car.getYear() >2010)
                .filter(car -> car.getPrice() < 10000)
                .count();
        System.out.println(count);
    }

    public static void statistics() throws Exception{
        List<Car> cars = MockData.getCars();
        DoubleSummaryStatistics stats = cars.stream()
                .mapToDouble(Car::getPrice)
                .summaryStatistics();
        System.out.println("average"+stats.getAverage());
        System.out.println("sum---"+stats.getSum());
        System.out.println("count---"+stats.getCount());
        System.out.println("max----"+stats.getMax());

    }

    public static void loadSum(){
        int[]  ints= {1,4,11,24,43,4};
        int sum = Arrays.stream(ints).reduce(0,Integer::sum);
        int sub = Arrays.stream(ints).reduce(0,(a,b) -> a-b);
        System.out.println("sum---" +sum +"Sub" +sub);
    }
//todo IntStream and IntUnaryOperator
//    public static void readIteratingList() throws Exception{
//        List<Person> cars = MockData.getPeople();
//        IntStream.iterate(0,cars.size())
//                .forEach(
//                        index -> System.out.println(cars)
//                );
//    }

    public static void range(){
        for(int i=0; i<= 10; i++){
//            System.out.println(i);
        }
        IntStream.range(0,10).forEach(System.out::println);
        IntStream.iterate(0,value -> value +1)
                .limit(11)
                .forEach(System.out::println);
    }
}
