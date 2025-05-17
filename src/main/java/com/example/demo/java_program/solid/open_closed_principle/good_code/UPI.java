package com.example.demo.java_program.solid.open_closed_principle.good_code;

public class UPI implements PaymentMethod{

    @Override
    public void pay(double amount) {
        System.out.println("Making payment via UPI " +amount);
    }
}
