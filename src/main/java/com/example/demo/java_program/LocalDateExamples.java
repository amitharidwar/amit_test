package com.example.demo.java_program;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LocalDateExamples {
    public static void main(String[] args) {
        // 1. Using LocalDate.now()
        LocalDate today = LocalDate.now();
        System.out.println("Current Date: " + today);

        // 2. Using LocalDate.of()
        LocalDate specificDate = LocalDate.of(2023, 10, 15);
        System.out.println("Specific Date: " + specificDate);

        // 3. Using LocalDate.parse()
        LocalDate parsedDate = LocalDate.parse("2023-10-15");
        System.out.println("Parsed Date: " + parsedDate);

        // 4. Using LocalDate.ofEpochDay()
        LocalDate epochDate = LocalDate.ofEpochDay(0); // 1970-01-01 he EPOCH_DAY is a simple incrementing count of days where day 0 is 1970-01-01. Negative numbers represent earlier days.
        System.out.println("Epoch Date: " + epochDate);

        // 5. Using LocalDate.ofYearDay()
        LocalDate yearDayDate = LocalDate.ofYearDay(2023, 100); // 100th day of 2023
        System.out.println("Year Day Date: " + yearDayDate);

        System.out.println("-----------------------------------------------------------");



        // 1. Using LocalDateTime.now()
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Current DateTime: " + now);

        // 2. Using LocalDateTime.of()
        //LocalDateTime specificDateTime = LocalDateTime.of(2023, 10, 5, 14, 30);
        LocalDateTime specificDateTime = LocalDateTime.of(2023, 10, 15, 14, 30, 12);
        System.out.println("Specific DateTime: " + specificDateTime);


        // 3. Using LocalDateTime.of() with LocalDate and LocalTime
        LocalDate date = LocalDate.of(2023, 10, 15);
        LocalTime time = LocalTime.of(14, 30);
        LocalDateTime dateTimeFromDateAndTime = LocalDateTime.of(date, time);
        System.out.println("DateTime from LocalDate and LocalTime: " + dateTimeFromDateAndTime);



        // 4. Using LocalDateTime.parse()
        LocalDateTime parsedDateTime = LocalDateTime.parse("2023-10-05T14:30");
        System.out.println("Parsed DateTime: " + parsedDateTime);

        // 5. Using LocalDateTime.parse() with custom formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime customParsedDateTime = LocalDateTime.parse("2023-10-05 14:30", formatter);
        System.out.println("Custom Parsed DateTime: " + customParsedDateTime);

        // 6. Using LocalDateTime.ofEpochSecond()
        LocalDateTime epochDateTime = LocalDateTime.ofEpochSecond(0, 0, java.time.ZoneOffset.UTC);
        System.out.println("Epoch DateTime: " + epochDateTime);
    }
}
