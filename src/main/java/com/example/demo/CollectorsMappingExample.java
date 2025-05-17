package com.example.demo;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CollectorsMappingExample {
    public static void main(String[] args) {
        List<String> words = List.of("apple", "banana", "cherry", "date", "fig", "grape");

        // Group strings by their length
        Map<Integer, List<String>> map1 = words.stream()
                .collect(Collectors.groupingBy(String::length));


        System.out.println(map1); // {3=[fig], 4=[date], 5=[apple, grape], 6=[banana, cherry]}




        // Group strings by their length and collect only the first character of each string
        Map<Integer, List<Character>> map2 = words.stream()
                .collect(Collectors.groupingBy(String::length, Collectors.mapping(word -> word.charAt(0), Collectors.toList())));

        System.out.println(map2);  // {3=[f], 4=[d], 5=[a, g], 6=[b, c]}




        // Group strings by their length and flatten their characters
        Map<Integer, List<Character>> map3 = words.stream()
                .collect(Collectors.groupingBy(String::length, Collectors.flatMapping(word -> word.chars().mapToObj(c -> (char) c), Collectors.toList())));

        System.out.println(map3); // {3=[f, i, g], 4=[d, a, t, e], 5=[a, p, p, l, e, g, r, a, p, e], 6=[b, a, n, a, n, a, c, h, e, r, r, y]}
    }
}
