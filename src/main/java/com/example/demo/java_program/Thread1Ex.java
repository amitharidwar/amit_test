package com.example.demo.java_program;


import java.util.concurrent.TimeUnit;

class Task implements Runnable {

    //private final Object lock = new Object();
    private static final Object lock = new Object();

    @Override
    public void run() {
        synchronized (lock) {
            System.out.println(Thread.currentThread().getName() + " enters");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " exits");
        }
    }
}

public class Thread1Ex {
    public static void main(String[] args) {
        Task t = new Task();

        new Thread(t, "T1").start();
        new Thread(t, "T2").start();
        new Thread(t, "T3").start();

        new Thread(new Task(), "T4").start();
        new Thread(new Task(), "T5").start();
        new Thread(new Task(), "T6").start();
    }
}
