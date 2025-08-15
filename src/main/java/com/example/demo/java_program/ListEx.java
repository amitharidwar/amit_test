package com.example.demo.java_program;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ListEx {
    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(4,5,6);// null is allowed here
      //  list.add(100); // will throw java.lang.UnsupportedOperationException
     //   list.remove(1); // will throw java.lang.UnsupportedOperationException
        list.set(0, 500); // Allowed

        Collections.sort(list); // It will work but not work if list have null
        System.out.println(list);



        List<Integer> originalList = List.of(4,10,30);

        // will throw java.lang.UnsupportedOperationException in all below cases
          /*
          originalList.add(100);
          originalList.remove(1);
          originalList.set(0,400);
          */

        // Collections.sort(originalList);// will throw java.lang.UnsupportedOperationException


        List<Integer> sortedList = originalList.stream().sorted().toList(); // Earlier in place of toList() we used as use .collect(Collectors.toList())

        List<Employee> employees = List.of(
                new Employee("John", 30, 40000d),
                new Employee("Alice", 25, 54000d),
                new Employee("Bob", 26, 75000d),
                new Employee("amit", 26, 95000d),
                new Employee("sumit", 26, 85000d)
        );

        // Sort by age ascending
        List<Employee> sortedEmployees = employees.stream()
                                                  .sorted(Comparator.comparingInt(Employee::age)
                                                                    .thenComparing(Comparator.comparingDouble(Employee::salary).reversed()))
                                                  .toList();

        List<Employee> sortedEmployees1 = employees.stream().sorted(Comparator.comparingInt(Employee::age)).collect(Collectors.toList());

        System.out.println(sortedEmployees);
        System.out.println(sortedEmployees1);
    }
}

record Employee(String name, int age, Double salary) {
}
