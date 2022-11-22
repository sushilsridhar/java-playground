package threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class UsingExecutors {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5); // different types are pools are there

        Runnable printThreadName = () -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("count :" + i + " \t" + Thread.currentThread().getName());
            }
        };

        for (int i = 0; i < 20; i++) {
            executorService.submit(printThreadName); // 1 task is printing 0-5, 20 tasks are given to 5 threads
        }

        executorService.awaitTermination(2, TimeUnit.SECONDS); // need to shutdown, otherwise threads stay alive after completion of task
        executorService.shutdown(); //--> does not wait for the previously submitted task to complete, use awaitTermination to wait then shutdown
    }
}
