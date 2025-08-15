package java_program;

import java.util.Queue;
import java.util.function.Supplier;

public class PC_Wait_Notify {

    private static final int MAX = 10;

    private static final Supplier<String> tName = () -> Thread.currentThread().getName();

    public static void main(String[] args) {

        Queue<String> queue = new java.util.LinkedList<>();
        new Thread(new Producer(queue), "Producer_Thread").start();
        new Thread(new Consumer(queue), "Consumer_Thread").start();
    }


    private static class Producer implements Runnable {
        private final Queue<String> queue;

        private Producer(Queue<String> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (queue) {
                    if (queue.size() == MAX) {
                        try {
                            System.out.println("Queue is full, " + tName.get()  +"waiting...");
                            queue.wait(); // Wait if the queue is full
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            System.err.println("Producer interrupted: " + e.getMessage());
                        }
                    } else {
                        String item = "Item " + (queue.size() + 1);
                        queue.add(item);
                        System.out.println(tName.get() + "is producing: " + item);
                        queue.notify(); // Notify consumer that an item is produced
                    }
                }
            }
        }
    }

    private static class Consumer implements Runnable {
        private final Queue<String> queue;

        private Consumer(Queue<String> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (queue) {
                    if (queue.isEmpty()) {
                        try {
                            System.out.println("Queue is empty,"  + tName.get()  + " waiting...");
                            queue.wait(); // Wait if the queue is empty
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            System.err.println("Consumer interrupted: " + e.getMessage());
                        }
                    } else {
                        System.out.println(tName.get() + " is consuming: " + queue.poll());
                        queue.notify(); // Notify producer that an item is consumed
                    }
                }
            }
        }
    }
}
