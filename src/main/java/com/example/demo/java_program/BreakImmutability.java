package com.example.demo.java_program;

import java.lang.reflect.Field;

//final class Person {
//    private final String name;
//
//    public Person(String name) {
//        this.name = name;
//    }
//
//    public String getName() {
//        return name;
//    }
//}

record Person(String name) {
}


public class BreakImmutability {
    public static void main(String[] args) {
        Person person = new Person("Alice");

        System.out.println("Original name: " + person.name());

        // Break immutability

        try {
            Field field = Person.class.getDeclaredField("name");
            field.setAccessible(true);
            field.set(person, "Hacked"); // will throw java.lang.IllegalAccessException: Can not set final if you are using record
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }


        System.out.println("Modified name: " + person.name());
    }
}


// we can Prevent Reflection Using SecurityManager but it is deprecated since java17 and removed in Java 21+