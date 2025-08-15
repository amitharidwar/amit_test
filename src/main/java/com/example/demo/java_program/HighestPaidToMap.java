package java_program;

import java.util.*;
import java.util.stream.*;


record Employee(String name, String dept, double salary) {

}

public class HighestPaidToMap {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", "IT", 80000),
                new Employee("Bob", "IT", 95000),
                new Employee("Charlie", "HR", 60000),
                new Employee("Eve", "IT", 70000),
                new Employee("Frank", "Finance", 90000),
                new Employee("Amit", "Finance", 90000),
                new Employee("Grace", "Finance", 85000),
                new Employee("Diana", "HR", 75000)
        );

        Map<String, Employee> highestPaidByDept =
                employees.stream()
                        .collect(Collectors.toMap(
                                e -> e.dept(),
                                e -> e,
                                (e1, e2) -> e1.salary() > e2.salary() ? e1 : e2
                        ));



        Map<String, List<Employee>> highestPaidByDept1 = employees.stream()
                .collect(Collectors.groupingBy(Employee::dept))   // group by dept
                .entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> {
                            double maxSalary = entry.getValue().stream()
                                    .mapToDouble(Employee::salary)
                                    .max()
                                    .orElse(0);
                            return entry.getValue().stream()
                                    .filter(e -> e.salary() == maxSalary)
                                    .toList();
                        }
                ));

        highestPaidByDept.forEach((dept, emp) -> System.out.println(dept + " -> " + emp));

        highestPaidByDept1.forEach((dept, emp) -> System.out.println(dept + " -> " + emp));
    }
}
