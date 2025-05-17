package com.example.demo.java_program;

import java.util.*;
import java.util.stream.Collectors;

public class Test44 {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Charlie", "Alice", "David", "Bob");

        // Sort in natural order
        List<String> sortedNames = names.stream()
                                        .sorted(Comparator.reverseOrder())
                                        .toList();
                //.collect(Collectors.toList());

        System.out.println(sortedNames);


        Map<String, Integer> unsortedMap = new HashMap<>();
        unsortedMap.put("Charlie", 3);
        unsortedMap.put("Alice", 1);
        unsortedMap.put("David", 4);
        unsortedMap.put("Bob", 2);
        unsortedMap.put("Alice", 100);

        // Sort the map by keys
        Map<String, Integer> sortedMap = unsortedMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, // Merge function
                        LinkedHashMap::new // Maintain insertion order
                ));

        System.out.println("Sorted Map: " + sortedMap);

        List<String> words = List.of("hello", "world");
        List<Character> result = words.stream()
                .flatMap(word -> word.chars().mapToObj(c -> (char) c))
                .toList();
        System.out.println(result); // [h, e, l, l, o, w, o, r, l, d]


        Map<String, List<Integer>> map = new HashMap<>();

        // Add values to the list for a specific key
        map.computeIfAbsent("numbers", k -> new ArrayList<>()).add(1);
        map.computeIfAbsent("numbers", k -> new ArrayList<>()).add(2);
        map.computeIfAbsent("numbers", k -> new ArrayList<>()).add(3);

        System.out.println(map);
    }
}
