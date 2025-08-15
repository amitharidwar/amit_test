package com.example.demo.java_program;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class ThreadCodeEx1_Adv {

    private static final int MAX = 100;
    private static final int NUM_THREADS = 3;
    private static final AtomicInteger counter = new AtomicInteger(1);

    private static final Lock lock = new ReentrantLock();
    private static final Condition condition = lock.newCondition();

    private static final Map<String, String> map = Map.of("T1", "A", "T2", "B", "T3", "C");

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
                lock.lock();
                try {
                    if (counter.get() % NUM_THREADS == threadId % NUM_THREADS) {
                        System.out.println(Thread.currentThread().getName() + " prints " + map.get(Thread.currentThread().getName()));
                        counter.incrementAndGet();
                        condition.signalAll();
                    } else {
                        condition.await(); // Wait for the condition to be signaled
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Restore interrupted status
                    break;
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}