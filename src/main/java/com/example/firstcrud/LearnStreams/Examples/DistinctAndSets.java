package com.example.firstcrud.LearnStreams.Examples;

import com.google.common.annotations.VisibleForTesting;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DistinctAndSets {
    public void distinct() throws Exception{
        List<Integer> numbers = List.of(1,1,1,2,4,4,4,5,7,8,9);
        List<Integer> filtred = numbers.stream().distinct()
                .collect(Collectors.toList());
        System.out.println(filtred);
    }
    public static void distinctBySets() throws Exception{
        List<Integer> numbersNow = List.of(1,1,1,2,4,4,4,5,7,8,9);
        Set<Integer> filtredSet = numbersNow.stream().collect(Collectors.toSet());
        var values = filtredSet.stream().count();
//        List<Integer> command = (List<Integer>) numbersNow.stream().filter(val -> val>2);
//        assert
        System.out.println("----set" +filtredSet +"------" +values);
    }

    public static void main(String[] args) throws Exception {
        DistinctAndSets distinctAndSets = new DistinctAndSets();
       distinctAndSets.distinct();
       DistinctAndSets.distinctBySets();
    }

}
