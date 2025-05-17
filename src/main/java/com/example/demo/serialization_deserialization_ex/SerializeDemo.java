//package com.example.demo.serialization_deserialization_ex;
//
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.ObjectOutputStream;
//import java.io.Serializable;
//
//public class SerializeDemo {
//    public static void main(String[] args) {
//        Employee emp = new Employee("Alice", 50000, 50);
//        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("employee_record.ser"))) {
//            out.writeObject(emp);
//            System.out.println("Employee record serialized.");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
//
//
//
//
