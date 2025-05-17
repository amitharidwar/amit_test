package com.example.demo.serialization_deserialization_ex;


import java.io.*;

class Emp implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private Integer age;
    private Double salary;

    public Emp(String name, Integer age, Double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }
}

public class SerializationEx {
    public static void main(String[] args) {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("emp.ser"))) {
            oos.writeObject(new Emp("amit", 43, 90000d));
            System.out.println("Object serialized.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("emp.ser"))) {
            Emp e = (Emp) ois.readObject();
            System.out.println("Object de-serialized.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
