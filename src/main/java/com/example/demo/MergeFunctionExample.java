package com.example.demo;

import java.util.*;
import java.util.stream.Collectors;

public class MergeFunctionExample {
    public static void main(String[] args) {
        List<Map.Entry<String, Integer>> entries = Arrays.asList(
                new AbstractMap.SimpleEntry<>("Alice", 1),
                new AbstractMap.SimpleEntry<>("Bob", 2),
                new AbstractMap.SimpleEntry<>("Alice", 100) // Duplicate key
        );

        // Using (oldValue, newValue) -> newValue
        Map<String, Integer> newValueMap = entries.stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> newValue
                ));
        System.out.println("Keep new value: " + newValueMap); // {Alice=100, Bob=2}

        // Using (oldValue, newValue) -> oldValue
        Map<String, Integer> oldValueMap = entries.stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue
                ));
        System.out.println("Keep old value: " + oldValueMap); // {Alice=1, Bob=2}

        List<String> names = Arrays.asList("Alice",  "Ram", "Charlie", "Bob", "David");

        names.sort(Comparator.reverseOrder());

       // Collections.sort(names, Comparator.reverseOrder());
        System.out.println(names);


        List<String> sortedNames = names.stream()
                .sorted(Comparator.naturalOrder())
                .toList();
        System.out.println(sortedNames);


    }
}