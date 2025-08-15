package com.example.demo.java_program.solid.open_closed_principle.good_code;

public class CreditCard implements PaymentMethod{

    @Override
    public void pay(double amount) {
        System.out.println("Making Payment via Credit Card" + amount);
    }
}