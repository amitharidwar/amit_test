package java_program;

import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

public class ReorderMap {
    public static void main(String[] args) {
        LinkedHashMap<String, List<String>> originalMap = new LinkedHashMap<>();
        originalMap.put("DOG", List.of("bark"));
        originalMap.put("CAT", List.of("meow", "purr"));
        originalMap.put("RAT", List.of("squeak"));
        originalMap.put("MAT", List.of("flat"));
        originalMap.put("BAT", List.of("fly"));
        originalMap.put("ZAT", List.of("unknown"));

        List<String> priority = List.of("CAT", "MAT", "ZAT", "SOME");

        LinkedHashMap<String, List<String>> reordered = Stream.concat(priority.stream().filter(originalMap::containsKey),
                                                                      originalMap.keySet().stream().filter(k -> !priority.contains(k)))
                                                        .collect(Collectors.toMap(k -> k, originalMap::get, (e1, e2) -> e1, LinkedHashMap::new));

        reordered.forEach((k, v) -> System.out.println(k + " = " + v));

        String s1 = "List en";
        String s2 = "Silent";

        Function<String, List<Integer>> normalize =
                s -> s.replaceAll("\\s", "")
                        .toLowerCase()
                        .chars()
                        .sorted()
                        .boxed()
                        .toList();

        boolean isAnagram = normalize.apply(s1).equals(normalize.apply(s2));

        System.out.println(isAnagram); // true

        List<Integer> list = new ArrayList<>(List.of(1, 2, 3, 4, 5));

        list.removeIf(a -> a % 2 == 0); // Remove even numbers
        System.out.println(list); // [1, 3, 5]

        List<String> words = List.of("apple", "banana", "apple", "orange", "banana", "apple", "kiwi", "banana", "kiwi", "kiwi", "kiwi");

        Map<String, Long> results = words.stream().collect(Collectors.groupingBy(e -> e, LinkedHashMap :: new, Collectors.counting()))
                                       .entrySet().stream()
                        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                  .collect(Collectors.toMap(Map.Entry :: getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));


        System.out.println(results); // [apple, banana]
    }
}

