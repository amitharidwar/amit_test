import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class CyclicBarrierDemo {

    private static final Supplier<String> tName = () -> Thread.currentThread().getName();
    private static final int N = 4; // Number of players

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(N, () ->  System.out.println("All players are ready! Game starts..."));

        Task task = new Task(barrier);
        IntStream.rangeClosed(1, N).forEach(i -> new Thread(task, "Player-" + i).start());

    }

    private static class Task implements Runnable {

        private final CyclicBarrier barrier;

        private Task(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                System.out.println(tName.get() + " is ready");
                barrier.await();  // Wait at the barrier
                System.out.println(tName.get() + " starts playing");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
