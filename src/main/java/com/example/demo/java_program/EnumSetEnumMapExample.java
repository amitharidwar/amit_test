package com.example.demo.java_program;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;

public class EnumSetEnumMapExample {

    enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }

    enum Status {
        NEW, IN_PROGRESS, RESOLVED, COMPLETED, CLOSED
    }

    public static void main(String[] args) {

        // === EnumSet Example ===
        EnumSet<Day> weekdays = EnumSet.range(Day.MONDAY, Day.FRIDAY);
        EnumSet<Day> weekend = EnumSet.of(Day.SATURDAY, Day.SUNDAY);

        weekdays.stream().forEach(System.out::println);


        // === EnumMap Example ===
        EnumMap<Status, String> statusDescriptions = new EnumMap<>(Status.class);

        statusDescriptions.put(Status.NEW, "Task is new");
        statusDescriptions.put(Status.IN_PROGRESS, "Task is ongoing");
        statusDescriptions.put(Status.COMPLETED, "Task is completed");
        statusDescriptions.put(Status.CLOSED, "Task is closed");

        statusDescriptions.forEach((status, value) -> System.out.println(status + " => " + value));


    }
}
