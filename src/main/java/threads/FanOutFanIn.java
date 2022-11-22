package threads;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * We have an array of 50 objects
 * Print 1-10 objects sequentially in 1 thread,
 * then print 11-40 objects using N parallel threads,
 * then print 41-50 objects sequentially in 1 thread
 *
 * -> do this using only threads (no executor, no forkjoinpool etc)
 */
public class FanOutFanIn {
    public static void main(String[] args) throws InterruptedException {
        //new FanOutFanInJob(50, 4).run();

        ArrayList<Integer> job = new ArrayList<>();
        for(int i=0; i<=50; i++) {
            job.add(i);
        }
        UsingExecutorsForJob usingExecutorsForJob = new UsingExecutorsForJob(job);
        usingExecutorsForJob.doJob();
    }
}

class UsingExecutorsForJob {

    ArrayList<Integer> job;
    int i=1;

    UsingExecutorsForJob(ArrayList<Integer> job) {
        this.job = job;
    }

    public void doJob() throws InterruptedException {
        Runnable task = () -> {
            printNext(); // cant pass variable inside run method, hence separate method
        };

        ExecutorService executor = Executors.newFixedThreadPool(5);

        while(i<=10) {
            printNext();
        }

        int j = 11; //  using separate variable because,
        // submitting task doesn't guarantee execution of task or updation of i variable inside task,
        // there is delay from Executors, so i is not updated, while loop keeps going

        while(j<=40) { // creating j number of tasks, executor executes all of them parallely
            executor.submit(task); // submitting task doesn't guarantee execution of task immediately
            j++;
        }

        executor.awaitTermination(1, TimeUnit.SECONDS); // otherwise the main thread and executor threads may run parallely
        // or remove dependency of i from below while loop
        executor.shutdown(); // wait for submitted task to complete and shutdown executor

        while(i<=50) {
            printNext();
        }
    }

    private synchronized void printNext() { // synchronized keyword used, so that 5 threads does not miss out updation of i value
        System.out.println(Thread.currentThread().getName()+" : "+ i + ": "+ job.get(i));
        i++;
    }
}

class UsingThreadsForJob {
    ArrayList<Integer> items;
    ArrayList<Thread> threads;
    int i;

    public UsingThreadsForJob(int count, int threads) {
        this.items = new ArrayList<Integer>(count);
        this.threads = new ArrayList<>(4);

        for (int i = 1; i <= count; i++) {
            this.items.add(i);
        }
    }

    private synchronized void printNext() {
        printItem(i);
        i++;
        notify();
    }

    private void printItem(int index) {
        System.out.println(Thread.currentThread().getName() + "  :  " + index);
    }

    void run() throws InterruptedException {
        i = 0;
        while (i <= 10) {
            printNext();
        }
        /**
         * Current Problem: some numbers get printed twice
         * Assignment: fix that.
         */

        // print 11 to 40 using 4 threads
        for (int j = 0; j < 4; j++) {
            threads.add(new Thread(() -> {
                while (i <= 40) {
                    printNext();
                }
            }));
        }

        threads.forEach(Thread::start);
        for (Thread thread : threads) {
            thread.join();
        }

        // print 41-50 using single thread again
        while (i <= 50) {
            printNext();
        }
    }
}
