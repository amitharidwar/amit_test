package com.example.demo.java_program;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class WaitVsSleepExample {
    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {

        Runnable r1 = (() -> {
            synchronized (lock) {
                try {
                    System.out.println("Thread 1: Waiting for notification...");
                    lock.wait(); // Releases the lock and waits
                    System.out.println("Thread 1: Notified and resumed!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Runnable r2 = (() -> {
            synchronized (lock) {
                try {
                    System.out.println("Thread 2: Sleeping for 5 seconds...");
                    Thread.sleep(5000); // Pauses for 2 seconds but keeps the lock
                    System.out.println("Thread 2: Woke up and notifying...");
                    lock.notify(); // Notifies the waiting thread
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(r1);
        Thread.sleep(2000); // Ensure Thread 1 starts waiting before Thread 2
        executor.submit(r2);
        executor.shutdown();

        // Using Thread class

//        new Thread(r1).start();
//        Thread.sleep(2000); // Ensure Thread 1 starts waiting before Thread 2
//        new Thread(r2).start();
    }
}