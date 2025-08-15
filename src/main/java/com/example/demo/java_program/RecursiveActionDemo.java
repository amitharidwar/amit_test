package java_program;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class RecursiveActionDemo {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);

        Task task = new Task(numbers);
        ForkJoinPool pool = new ForkJoinPool(); // means that when you call the no-argument constructor of ForkJoinPool,
        // Java automatically creates a number of worker threads equal to the number of available CPU cores (or logical processors) on your machine.
        pool.invoke(task);

        System.out.println("Sum = " + task.getSum());
    }

    private static class Task extends RecursiveAction {
        private final List<Integer> numbers;
        private int sum; // result holder

        public Task(List<Integer> numbers) {
            this.numbers = numbers;
        }

        @Override
        protected void compute() {
            if (numbers.size() <= 1) {
                sum = numbers.isEmpty() ? 0 : numbers.get(0);
            }else {
                int mid = numbers.size() / 2;
                Task t1 = new Task(numbers.subList(0, mid));
                Task t2 = new Task(numbers.subList(mid, numbers.size()));

                t1.fork();// Fork the first task. fork() schedules t1 to run asynchronously in the ForkJoinPool
                t2.compute();//Compute the second task directly.
                t1.join();// Wait for the first task to complete

                sum = t1.sum + t2.sum;
            }
        }

        public int getSum() {
            return sum;
        }
    }
}
