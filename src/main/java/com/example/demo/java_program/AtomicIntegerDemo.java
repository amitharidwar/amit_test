package java_program;

import java.util.stream.IntStream;

public class AtomicIntegerDemo {

    private static int counter = 0;

    private final Object lock = new Object();
    public static void main(String[] args) {

        Task task = new AtomicIntegerDemo().new Task();
        IntStream.rangeClosed(1, 3)
                .mapToObj(i -> new Thread(task, "Thread-" + i))
                .peek(Thread::start)
                .forEach(t -> {
                    try { t.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });


        System.out.println("Final Counter Value: " + counter);
    }


    private  class Task implements Runnable {
        @Override
        public void run() {
            IntStream.rangeClosed(1, 10).forEach(i -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                //counter++;
//                synchronized (Task.class){
//                    counter++;
//                }

//                synchronized (this){
//                    counter++;
//                }

                synchronized (lock){
                    counter++;
                }
                System.out.println(Thread.currentThread().getName() + " - Counter: " + counter);
            });
        }
    }
}
