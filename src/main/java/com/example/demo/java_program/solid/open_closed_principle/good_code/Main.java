package java_program.solid.open_closed_principle.good_code;

public class Main {
    public static void main(String[] args) {
//
//        PaymentProcessor processor = new PaymentProcessor();
//
//        PaymentMethod creditCard = new CreditCard();
//        PaymentMethod debitCard = new DebitCard();
//        PaymentMethod upi = new UPI();
//
//        processor.processPayment(creditCard, 100);
//        processor.processPayment(debitCard, 400);
//        processor.processPayment(upi, 120);
    }
}

/*
🧠 Open/Closed Principle (OCP)
A class should be open for extension but closed for modification.*/
// This means we can add new functionality without changing existing code


/*
🧱 S – Single Responsibility Principle (SRP)
         A class should have only one reason to change, meaning it should have only one job or responsibility.
         In other words, a class should not be overloaded with multiple responsibilities that can change for different reasons.

🧱 O – Open/Closed Principle (OCP)
       Software entities like classes, modules, functions etc should be open for extension but closed for modification.This means we can add new functionality without changing existing code


🧱 L – Liskov Substitution Principle (LSP)
       Objects of a superclass should be replaceable with objects of its subclasses without breaking the application.
       It means : If a class A is a parent class, and B is a subclass of A, then you should be able to use B wherever A is expected — without causing incorrect behavior.

🧱 I – Interface Segregation Principle (ISP)
        Clients should not be forced to depend on interfaces they do not use.In simple terms, an interface should have only those methods that are relevant
        to the specific client using it. If a class is forced to implement methods it doesn’t need, that’s a violation of ISP.

🧱 D – Dependency Inversion Principle (DIP)
       High-level modules should not depend on low-level modules and both should depend on abstractions.
       Abstractions should not depend on details. Details should depend on abstractions.


*/
