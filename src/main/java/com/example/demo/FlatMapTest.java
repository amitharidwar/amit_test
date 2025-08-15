package com.example.demo;

import java.util.List;
import java.util.stream.Collectors;



public class FlatMapTest {
    public static void main(String[] args) {
        List<List<String>> nestedList = List.of(
                List.of("a", "b"),
                List.of("c", "d"),
                List.of("e")
        );

        List<String> flatList = nestedList.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

        System.out.println(flatList); // [a, b, c, d, e]


        List<String> result = List.of("Tom", "Jerry", "Spike")
                .stream()
                .filter(name -> name.length() > 3)
                .map(String::toUpperCase).
             //   .toList();  // Java 16+
                 collect(Collectors.toList());


        result.add("amit"); // UnSupportedOperationException as toList() Returns an unmodifiable list

          // Returns a mutable ArrayList in case of collect(Collectors.toList());


    }
}
