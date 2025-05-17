package com.example.demo.java_program;

public class TrafficLightExample {
    enum TrafficLight {
        RED("Stop"),
        YELLOW("Caution"),
        GREEN("Go");

        private final String action;

        // Constructor
        TrafficLight(String action) {
            this.action = action;
        }

        // Method
        public String getAction() {
            return action;
        }
    }

    public static void main(String[] args) {
        for (TrafficLight light : TrafficLight.values()) {
            System.out.println(light + " means: " + light.getAction());
        }
    }
}

// Enum constants are implicitly public static final
// Enum constructors are always private,   You cannot declare an enum constructor as public or protected.
// Enums can have fields and methods
// Enums can implement interfaces. But they cannot extend classes, as enums already extend java.lang.Enum
// Enums are type-safe
// constructor of an enum is called once for each enum constant when the enum class is loaded.