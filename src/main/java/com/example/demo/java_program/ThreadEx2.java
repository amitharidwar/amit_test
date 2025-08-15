package com.example.demo.java_program;


import java.util.concurrent.TimeUnit;

class Taskk implements Runnable {

    @Override
    public void run() {
        try {
            if ("T1".equals(Thread.currentThread().getName())) {
                m1();
            } else {
                m2();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private synchronized void m1() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " enteres into m1 method");
        TimeUnit.SECONDS.sleep(5);
        System.out.println(Thread.currentThread().getName() + " exits into m1 method");
    }

    private synchronized void m2() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " enteres into m2 method");
        TimeUnit.SECONDS.sleep(5);
        System.out.println(Thread.currentThread().getName() + " exits into m1 method");
    }
}


public class ThreadEx2 {
    public static void main(String[] args) {
        Taskk obj = new Taskk();

        new Thread(obj, "T1").start();
        new Thread(obj, "T2").start();
    }
}
