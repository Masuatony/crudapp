package com.example.firstcrud.LearnStreams.Examples;

import java.util.List;
import java.util.stream.Collectors;

public class JoiningStrings {
    public static void joiningTraditional(){
        List<String> names = List.of("anna", "john", "marcos", "helena", "yasmin");
        StringBuilder join = new StringBuilder();
        for (String name: names) {
            join.append(name.substring(0,1).toLowerCase())
                    .append(name.substring(1))
                    .append(",");
        }
        System.out.println(join);
        System.out.println(join.substring(0,join.length() -2));
        }

        public static void joiningWithStreams(){
            List<String> names = List.of("anna", "john", "marcos", "helena", "yasmin");
            String join = names.stream()
                    .map(name -> name.substring(0,1).toUpperCase() + name.substring(1))
                    .collect(Collectors.joining("|"));
            System.out.println(join);
        }

    public static void main(String[] args) {
        JoiningStrings.joiningTraditional();
        JoiningStrings.joiningWithStreams();
    }
    }

