package com.example.demo.serialization_deserialization_ex;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DeserializeDemo {
    public static void main(String[] args) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("employee_record.ser"))) {
            Employee emp = (Employee) in.readObject();
            System.out.println("Employee name: " + emp.getName());
            System.out.println("Employee salary: " + emp.getSalary());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
