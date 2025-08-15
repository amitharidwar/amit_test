package com.example.demo.java_program.solid.open_closed_principle.good_code;


// This class is closed for modification.

public class PaymentProcessor {
    public void processPayment(PaymentMethod paymentMethod,double amount){
        paymentMethod.pay(amount);
    }
}