package com.example.demo.java_program;


import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class ThreadCodeEx1 {

    private final static int MAX = 100;

    private final static int NUM_THREADS = 3;

    private static final AtomicInteger counter = new AtomicInteger(1);

    private static final Object lock = new Object();

    private static final Map<String, String> map = Map.of("T1", "A", "T2", "B", "T3", "C");

    private static final Supplier<String> threadName = () -> Thread.currentThread().getName();


    public static void main(String[] args) {
        IntStream.rangeClosed(1, NUM_THREADS)
                  .forEach(i -> new Thread(new Task(i), "T" + i).start());

    }

    private static class Task implements Runnable {

        private final int threadId;

        public Task(int threadId) {
            this.threadId = threadId;
        }

        @Override
        public void run() {
            while (counter.get() <= MAX) {
                synchronized (lock) {
                    if (counter.get() % NUM_THREADS == threadId % NUM_THREADS) {
                        System.out.println(threadName.get() + " prints " + map.get(threadName.get()));
                        counter.incrementAndGet();
                        lock.notifyAll();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
