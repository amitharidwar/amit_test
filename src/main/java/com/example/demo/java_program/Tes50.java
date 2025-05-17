package com.example.demo.java_program;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Tes50 {
    public static void main(String[] args) {

        String s[] = {"a", "b", "c", "d", "e"};

        List<String> list = Arrays.asList(s);

        List<String> list1 = new ArrayList<>(Arrays.asList(s));

        s[0] = "Amit";

        System.out.println(list1);

        list1.add("sumt");
        System.out.println(list1);


        System.out.println(list);

        List<Integer> numbers = IntStream.rangeClosed(1, 25).boxed().toList();

        System.out.println(numbers.get(0));


    }
}
