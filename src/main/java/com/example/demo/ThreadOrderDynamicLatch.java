import java.util.concurrent.CountDownLatch;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ThreadOrderDynamicLatch {

    public static void main(String[] args) {

        int NUM_THREADS = 7; // Number of threads

        List<CountDownLatch> latches = IntStream.rangeClosed(1, NUM_THREADS)
                                                .mapToObj(i -> new CountDownLatch(1))
                                                .toList();


        // ✅ Create and start threads dynamically
        IntStream.rangeClosed(1, NUM_THREADS)
                 .forEach(i -> new Thread(new Task(i == 1 ? null : latches.get(i - 1), i == NUM_THREADS ? null : latches.get(i)), "T" + i).start());

    }

    // ================== Task class ==================
    private static class Task implements Runnable {

//private final String name;
        private final CountDownLatch waitOn;
        private final CountDownLatch signalNext;

        public Task(CountDownLatch waitOn, CountDownLatch signalNext) {
          //  this.name = name;
            this.waitOn = waitOn;
            this.signalNext = signalNext;
        }

        @Override
        public void run() {
            try {
                // ✅ Wait for previous task to finish (except for first task)
                if (waitOn != null) {
                    waitOn.await();
                }

                // ✅ Do the actual work
             //   System.out.println(name + " started");
                Thread.sleep(500); // simulate some work
            //    System.out.println(name + " finished");

                // ✅ Signal next task
                if (signalNext != null) {
                    signalNext.countDown();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

