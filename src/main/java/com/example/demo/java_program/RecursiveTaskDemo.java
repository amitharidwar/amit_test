package java_program;

import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

public class RecursiveTaskDemo {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);

        ForkJoinPool pool = new ForkJoinPool();
        int result = pool.invoke(new Task(numbers));

        System.out.println("Sum = " + result);
    }


    private static class Task extends RecursiveTask<Integer> {
        private final List<Integer> numbers;

        public Task(List<Integer> numbers) {
            this.numbers = numbers;
        }

        @Override
        protected Integer compute() {
            if (numbers.size() <= 1) {
                return numbers.isEmpty() ? 0 : numbers.get(0);
            }
            int mid = numbers.size() / 2;
            Task t1 = new Task(numbers.subList(0, mid));
            Task t2 = new Task(numbers.subList(mid, numbers.size()));

            t1.fork();// fork() schedules t1 to run asynchronously in the ForkJoinPool

            return t2.compute() + t1.join(); // t2.compute() runs the right half synchronously in the current thread;
                                            // t1.join() waits for the left half to complete and retrieves its result.
            // If t1 already finished, join() returns its result immediately. Otherwise join() blocks  until t1 completes.

        }
    }
}

