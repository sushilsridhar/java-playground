package threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UsingExecutors {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5); // different types are pools are there

        Runnable printThreadName = () -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("count :" + i + " \t" + Thread.currentThread().getName());
            }
        };

        for (int i = 0; i < 20; i++) {
            executorService.submit(printThreadName);
        }

        executorService.shutdown(); // need to shutdown, otherwise threads stay alive after completion of task
    }
}
