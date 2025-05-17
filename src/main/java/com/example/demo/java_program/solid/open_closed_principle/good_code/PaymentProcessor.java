package com.example.demo.java_program.solid.open_closed_principle.good_code;

public class PaymentProcessor {
    public void processPayment(PaymentMethod paymentMethod,double amount){
        paymentMethod.pay(amount);
    }
}