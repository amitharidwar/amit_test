package com.example.demo.java_program;

import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FrequencyEx {
    public static void main(String[] args) {
        String word = "programmring";

        System.out.println("Repeated characters:");

        word.chars() // stream of int (unicode values)
            .mapToObj(c -> (char) c) // convert int to Character
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
            .entrySet().stream()
            .filter(entry -> entry.getValue() > 1)
            .forEach(entry -> System.out.println(entry.getKey() + " appears " + entry.getValue() + " times"));




// Find duplicate elements in a List using Streams.
        List<String> list = List.of("amit", "sumit", "ram", "amit", "mohan", "sumit", "rohan", "anand");
        List<String> duplicates = list.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .toList();

        System.out.println(duplicates);

// Finding the First Non-Repeated Character
        String word1 = "programming";

        // Stylish one-liner approach using Java 8 streams
        word1.chars()
             .mapToObj(c -> (char) c)  // Convert int to Character
             .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))  // Group and count occurrences
             .entrySet().stream()  // Stream of Map.Entry
             .filter(entry -> entry.getValue() == 1)  // Filter non-repeated characters
             .map(Map.Entry::getKey)  // Extract character
             .findFirst()  // Get the first non-repeated character
             .ifPresentOrElse(System.out::println, () -> System.out.println("No non-repeated character found"));

    }
}
