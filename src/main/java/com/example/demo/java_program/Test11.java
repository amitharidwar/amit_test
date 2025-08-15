package com.example.demo.java_program;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Test11 {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>();

//        list.add("amit");
//        list.add("amit2");
//        list.add("amit3");
//        list.add("amit4");
//        list.add("amit5");

       String last1 = list.stream()
                          .reduce((first, second) -> second)
                          .orElse(null);




        String last = list.stream()
                          .skip(list.isEmpty() ? 0 : list.size() - 1) // ensures we never pass a negative number to skip(), even if the list is empty.
                          .findFirst()
                          .orElse(null);


        System.out.println(last);

        System.out.println(last1);


        Supplier<String> s = () -> "Ram";

        //Optional<String> name = Optional.of("John");

       Optional<String> name = Optional.empty();

        String result = name.orElse(s.get()); // Eager Evaluation: The fallback value is always evaluated immediately, regardless of whether the Optional contains a value or not.

        String result1 = name.orElseGet(s); // Lazy Evaluation: The fallback value is evaluated only if the Optional is empty.


        String result2 = name.orElseThrow(() -> new IllegalArgumentException("Value is required")); // Lazy (evaluates only if absent)


        System.out.println(result);
        System.out.println(result1);

        System.out.println(result2);


        Optional<String> name2 = Optional.ofNullable("ram");

        String result3 = name2.orElseThrow();

        System.out.println(result3);



//        ✅ Optional.of():
//        Does not allow null values.
//        If you pass null, it will throw a NullPointerException.
//        Use it when you are certain that the value you're passing is not null.


//        ✅ Optional.ofNullable():
//        Allows null values.
//        If you pass null, it returns an empty Optional (i.e., Optional.empty()).
//        Use it when the value may be null or when you are unsure if the value will be present.
    }
}
