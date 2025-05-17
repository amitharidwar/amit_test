package com.example.demo.java_program;

public class EnumTest {
    private enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;
    }

    public static void main(String[] args) {
        double a = 10.0;
        double b = 5.0;

        for (Operation op : Operation.values()) {
            System.out.println(op + ": " + op.apply(a, b));
        }


        Day today = Day.SATURDAY;

        String message = switch (today) {
            case MONDAY -> "Start of work week.";
            case FRIDAY -> "Last work day!";
            case SATURDAY, SUNDAY -> "It's weekend!";
            case TUESDAY, WEDNESDAY, THURSDAY -> "Midweek days.";
        };

        System.out.println(message);



        int day = 6;
        String type = switch (day) {
            case 1, 2, 3, 4, 5 -> "Weekday";
            case 6, 7 -> "Weekend";
            default -> throw new IllegalArgumentException("Invalid day: " + day);
        };

        System.out.println(type);

        int age = 17;
        String category = switch (age) {
            case 0, 1, 2, 3, 4, 5 -> "Toddler";
            case 6, 7, 8, 9, 10, 11, 12 -> "Child";
            case 13, 14, 15, 16, 17, 18 -> {
                System.out.println("Teenager detected.");
                yield "Teen";
            }
            default -> {
                if (age < 0) yield "Invalid age";
                else yield "Adult";
            }
        };

        System.out.println("Category: " + category);



        String signal = "YELLOW";
        String action = switch (signal) {
            case "RED" -> {
                System.out.println("Stopping traffic...");
                yield "Stop";
            }
            case "GREEN" -> "Go";
            case "YELLOW" -> {
                System.out.println("Caution: prepare to stop.");
                yield "Slow Down";
            }
            default -> "Unknown Signal";
        };

        System.out.println("Action: " + action);
    }
}

// Pattern matching in switch is a preview feature in Java 17; you must enable it with --enable-preview.
// In Java 21, pattern matching for switch is fully standardized and no longer a preview feature.
// EX: pattern matching

/*switch (obj) {
        case String s -> System.out.println("It's a String: " + s);
        case Integer i -> System.out.println("It's an Integer: " + i);
        default -> System.out.println("Unknown type");
}*/

// null can be matched directly using a case null

/*
switch (obj) {
        case null -> System.out.println("It's null");
        case String s -> System.out.println("It's a non-null String");
        default -> System.out.println("Other type");
}
*/

// Guarded patterns (when clauses):

/*switch (obj) {
        case String s when s.length() > 5 -> System.out.println("Long string: " + s);
        case String s -> System.out.println("Short string: " + s);
        default -> System.out.println("Not a string");
}*/
