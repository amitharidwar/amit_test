import java.util.stream.IntStream;

public class ThreadOrderWithTask {

    private static final Object lock = new Object();
    private static volatile int flag = 1; // which task should run next

    public static void main(String[] args) {

        // ✅ Start all threads together

        IntStream.rangeClosed(1, 10).forEach(i -> new Thread(new Task("T" + i, i)).start());
        // This will start T1, T2, and T3 in parallel, but they will run in order


    }

    // ================== Task class ==================
    private static class Task implements Runnable {

        private final String name;
        private final int order; // which task this is (1, 2, 3...)

        public Task(String name, int order) {
            this.name = name;
            this.order = order;
        }

        @Override
        public void run() {
            synchronized (lock) {
                while (flag != order) { // wait until it's this task's turn
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // ✅ Do the actual work
                System.out.println(name + " started");
                try {
                    Thread.sleep(1000); // simulate work
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(name + " finished");

                // ✅ Signal next thread
                flag++;
                lock.notifyAll();
            }
        }
    }
}

