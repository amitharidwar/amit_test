package java_program;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class SumUsingThreads {

    private static final int N = 5;

    public static void main(String[] args) {
        int[] arr = new int[1000];
        Arrays.setAll(arr, i -> i + 1); // Fill array with 1 to 1000

        int length = arr.length;
        int chunkSize = length / N;

        // Create List<SumTask>
        List<Task> tasks = IntStream.range(0, N)
                                        .mapToObj(i -> {
                                            int start = i * chunkSize;
                                            int end = (i == N - 1) ? length : start + chunkSize;
                                            return new Task(arr, start, end);
                                        })
                                        .toList();

        // Create List<Thread>
        List<Thread> threads = tasks.stream().map(Thread::new).toList();

        // Start all threads
        threads.forEach(Thread::start);

        // Join all threads
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Sum all partial results
        long totalSum = tasks.stream().mapToLong(Task::getPartialSum).sum();

        System.out.println("Total sum: " + totalSum);

    }

    private static class Task implements Runnable {

        private final int[] arr;
        private final int start;
        private final int end;
        private long partialSum;

        public Task(int[] arr, int start, int end) {
            this.arr = arr;
            this.start = start;
            this.end = end;
        }

        public long getPartialSum() {
            return partialSum;
        }

        @Override
        public void run() {
            partialSum = IntStream.range(start, end).mapToLong(i -> arr[i]).sum();
        }
    }
}

