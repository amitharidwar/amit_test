package com.example.demo.java_program;

import java.util.Collections;
import java.util.List;

public class Test12 {
    public static void main(String[] args) {
        List<Integer> list = List.of(); // Immutable list

       // Integer sum = list.stream().reduce((a,b) -> a+b).get();  // NoSuchElementexception in case no value present

        int sum1 = list.stream().reduce(0, Integer::sum); // Best approach and it returns int

        int sum2 = list.stream().mapToInt(Integer::intValue).sum();


        //System.out.println(sum);
        System.out.println(sum1);
        System.out.println(sum2);


        List<Integer> list1 = Collections.emptyList(); // Returns an immutable empty list.
        list1.add(34); // UnSupportedException

    }
}

/*
        | Alternative                     | Safe for empty list? | Returns   |
        | ------------------------------- | -------------------- | --------- |
        | `reduce((a, b) -> a + b).get()` | ❌ No                 | `Integer` |
        | `reduce(0, Integer::sum)`       | ✅ Yes                | `int`     |
        | `mapToInt(...).sum()`           | ✅ Yes                | `int`     |*/
