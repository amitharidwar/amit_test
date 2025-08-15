import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ThreadOrderDynamicSemaphore {

    public static void main(String[] args) {

        int NUM_THREADS = 5; // Number of threads

        List<Semaphore> semaphores = IntStream.rangeClosed(1, NUM_THREADS)
                                              .mapToObj(i -> new Semaphore(i == 1 ? 1 : 0))
                                              .toList();

        // ✅ Create and start threads dynamically
        IntStream.rangeClosed(1, NUM_THREADS)
                .forEach(i -> new Thread(new Task("T" + i,
                        (i == 1) ? null : semaphores.get(i - 1),
                        (i == NUM_THREADS) ? null : semaphores.get(i))).start());
    }

    // ================== Task class ==================
    private static class Task implements Runnable {

        private final String name;
        private final Semaphore waitOn;
        private final Semaphore signalNext;

        public Task(String name, Semaphore waitOn, Semaphore signalNext) {
            this.name = name;
            this.waitOn = waitOn;
            this.signalNext = signalNext;
        }

        @Override
        public void run() {
            try {
                // ✅ Wait for the previous task (except first task)
                if (waitOn != null) {
                    waitOn.acquire();
                }

                // ✅ Do the actual work
                System.out.println(name + " started");
                Thread.sleep(500); // simulate some work
                System.out.println(name + " finished");

                // ✅ Signal next task
                if (signalNext != null) {
                    signalNext.release();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
