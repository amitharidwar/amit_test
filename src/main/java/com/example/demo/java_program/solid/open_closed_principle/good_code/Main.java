package com.example.demo.java_program.solid.open_closed_principle.good_code;

public class Main {
    public static void main(String[] args) {

        PaymentProcessor processor = new PaymentProcessor();

        PaymentMethod creditCard = new CreditCard();
        PaymentMethod upi = new UPI();

        processor.processPayment(creditCard, 100);
        processor.processPayment(upi, 120);
    }
}

/*
🧠 Open/Closed Principle (OCP)
A class should be open for extension but closed for modification.*/
// This means we can add new functionality without changing existing code


/*
🧱 S – Single Responsibility Principle (SRP)


🧱 O – Open/Closed Principle (OCP)
       A class should be open for extension but closed for modification.


🧱 L – Liskov Substitution Principle (LSP)
       Objects of a superclass should be replaceable with objects of its subclasses without breaking the application.
       It means : If a class A is a parent class, and B is a subclass of A, then you should be able to use B wherever A is expected — without causing incorrect behavior.

🧱 I – Interface Segregation Principle (ISP)


🧱 D – Dependency Inversion Principle (DIP)



*/
