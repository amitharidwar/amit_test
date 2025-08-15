import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class CountDownLatchDemo {

    private static final Supplier<String> tName = () -> Thread.currentThread().getName();

    public static void main(String[] args) {

        final CountDownLatch latch = new CountDownLatch(3); // Wait for 3 tasks to finish

        List<String> services = List.of("cache-service", "alert-service", "validation-service");

        IntStream.rangeClosed(1, 3).forEach(i -> new Thread(new Task(latch), services.get(i-1)).start());
        // Main thread will wait until all tasks are done

        try {
            latch.await(); // Wait until all tasks are done
            System.out.println("All tasks completed. Proceeding with the next step.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class Task implements Runnable {

        private final CountDownLatch latch;

        private Task(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            System.out.println(tName.get() + " is ready");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(tName.get() + " finished its task");
            latch.countDown();
        }
    }
}
