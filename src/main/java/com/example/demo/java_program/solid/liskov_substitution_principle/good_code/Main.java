package com.example.demo.java_program.solid.liskov_substitution_principle.good_code;

public class Main {
    public static void readAnyFile(ReadableFile file){
        file.read();
    }

    public static void main(String[] args) {
        ReadOnlyFile readableFile = new ReadOnlyFile();
        readableFile.read();

        WritableFile writableFile = new WritableFile();
        writableFile.read();
        writableFile.write();

        readAnyFile(readableFile);
        readAnyFile(writableFile);
    }
}

/*
🧱 L – Liskov Substitution Principle (LSP)
Objects of a superclass should be replaceable with objects of its subclasses without breaking the application.
It means : If a class A is a parent class, and B is a subclass of A, then you should be able to use B wherever A is expected — without causing incorrect behavior.*/
