package com.example.demo.java_program;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public class TestEx {
    public static void main(String[] args) throws InterruptedException {
        Map<String, String> map = new WeakHashMap<>();
        Map<String, String> map1 = new HashMap<>();

        String k = new String("A");
        String k1 = new String("B");

        map.put(k, "amit");

        map1.put(k1, "amit");

        k = null;
        k1 = null;



        System.gc();

        Thread.sleep(5000);
        Runtime.getRuntime().gc();

        System.out.println(map.size());
        System.out.println(map1.size());
    }
}
