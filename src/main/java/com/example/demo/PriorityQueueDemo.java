import org.apache.logging.log4j.util.PropertySource;

import java.util.Comparator;

public class PriorityQueueDemo {
    public static void main(String[] args) {
        // Create a priority queue
//        java.util.PriorityQueue<String> priorityQueue = new java.util.PriorityQueue<>(Comparator.naturalOrder());
//
//        // Add elements to the priority queue
//        priorityQueue.add("Apple");
//        priorityQueue.add("Banana");
//        priorityQueue.add("Cherry");
//        priorityQueue.add("Date");
//        priorityQueue.add("Fig");
//        priorityQueue.add("Ele");
//
//        // Display the elements in the priority queue
//        System.out.println("Priority Queue: " + priorityQueue);
//
//        // Remove elements from the priority queue
//        while (!priorityQueue.isEmpty()) {
//            System.out.println("Removed: " + priorityQueue.poll());
//        }
//
//        // Display the empty queue
//        System.out.println("Priority Queue after removal: " + priorityQueue);
        // Create a priority queue with custom objects
        java.util.PriorityQueue<Employee> employeeQueue = new java.util.PriorityQueue<>(Comparator.comparing(Employee::sal, Comparator.reverseOrder()));
        // Add custom objects to the priority queue
        employeeQueue.add(new Employee("Alice", 30, 50000d));
        employeeQueue.add(new Employee("Bob", 25, 60000d));
        employeeQueue.add(new Employee("Charlie", 35, 70000d));
        employeeQueue.add(new Employee("David", 28, 55000d));

        // Display the custom objects in the priority queue
      //  System.out.println("Employee Priority Queue: " + employeeQueue);
        //        // Remove elements from the priority queue
        while (!employeeQueue.isEmpty()) {
            System.out.println("Removed: " + employeeQueue.poll());
        }


    }
}

record Employee(String name, int age, double sal) {}
