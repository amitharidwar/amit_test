//package com.example.demo.serialization_deserialization_ex;
//
//
//import java.io.*;
//
//class Parent {
//    private String parentField;
//
//    public Parent() {
//        System.out.println("Parent constructor called");
//    }
//
//    public Parent(String parentField) {
//        this.parentField = parentField;
//    }
//
//    public String getParentField() {
//        return parentField;
//    }
//
//    public void setParentField(String parentField) {
//        this.parentField = parentField;
//    }
//}
//
//class Child extends Parent implements Serializable {
//    private String childField;
//
//    public Child(String parentField, String childField) {
//        super(parentField);
//        this.childField = childField;
//    }
//
//    public String getChildField() {
//        return childField;
//    }
//
//    public void setChildField(String childField) {
//        this.childField = childField;
//    }
//}
//
//public class SerializationEx1 {
//    public static void main(String[] args) {
//        Child child = new Child("p_value", "c_value");
//
//        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("child.ser"))) {
//            oos.writeObject(child);
//            System.out.println("Serialization done");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("child.ser"))) {
//            Child d_child = (Child) ois.readObject();
//            System.out.println("De-Serialization done");
//            System.out.println("parent field: " + d_child.getParentField());
//            System.out.println("child field: " + d_child.getChildField());
//        } catch (IOException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
