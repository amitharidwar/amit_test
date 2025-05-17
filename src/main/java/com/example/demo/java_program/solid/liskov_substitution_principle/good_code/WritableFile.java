package com.example.demo.java_program.solid.liskov_substitution_principle.good_code;

public class WritableFile extends ReadableFile implements Writable {

    @Override
    public void write() {
        System.out.println("Writing to a file ...");
    }
}
