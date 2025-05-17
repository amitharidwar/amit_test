package com.example.demo.java_program;

import java.util.*;
import java.util.stream.IntStream;

public class SynchronizedMapIssue {
    public static void main(String[] args) throws InterruptedException {
        //Map<String, String> map = new HashMap<>(); // ❌ Not thread-safe
        Map<String, String> map = Collections.synchronizedMap(new HashMap<>()); // ✅ Safer basic ops

        Runnable writer = () -> {
            IntStream.rangeClosed(1, 100)
                    .forEach(i -> map.put(Thread.currentThread().getName() + "_" + i, Thread.currentThread().getName()));
        };
        Thread t1 = new Thread(writer, "T1");
        Thread t2 = new Thread(writer, "T2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();


        System.out.println("Final map size: " + map.size()); // always produce correct list size 5000 in case of Collections.synchronizedMap

        System.out.println(map);


        List<String> list = Collections.synchronizedList(new ArrayList<>()); // ✅ Safer basic ops
        //List<String> list = new ArrayList<>(); // ❌ Not thread-safe

        Runnable r = () -> {
            IntStream.rangeClosed(1, 100)
                    .forEach(i -> list.add(Thread.currentThread().getName() + "_" + i));
        };

        Thread t4 = new Thread(r, "T4");
        Thread t5 = new Thread(r, "T5");
        t4.start();
        t5.start();
        t4.join();
        t5.join();

        System.out.println("Final list size: " + list.size()); // always produce correct list size 6000 in case of Collections.synchronizedList


        Set<String> set = Collections.synchronizedSet(new HashSet<>());
        // Set<String> set = new HashSet<>(); // ❌ Not thread-safe

        Runnable r2 = () -> {
            IntStream.rangeClosed(1, 100)
                    .forEach(i -> set.add(Thread.currentThread().getName() + "_" + i));
        };

        Thread t6 = new Thread(r2, "T6");
        Thread t7 = new Thread(r2, "T7");
        t6.start();
        t7.start();
        t6.join();
        t7.join();

        System.out.println("Final Set size: " + set.size()); // always produce correct list size 6000 in case of Collections.synchronizedList
    }
}