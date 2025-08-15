package com.example.demo.java_program.solid.liskov_substitution_principle.good_code;

public class ReadableFile implements Readable{
    public void read(){
        System.out.println("Reading from a file");
    }
}
