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

        ArrayList<Integer> job = new ArrayList<>();
        for(int i=0; i<=50; i++) {
            job.add(i);
        }
        UsingExecutorsForJob usingExecutorsForJob = new UsingExecutorsForJob(job);
        //usingExecutorsForJob.doJob();

        UsingThreadsForJob usingThreadsForJob = new UsingThreadsForJob(job);
        usingThreadsForJob.doJob();
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
    ArrayList<Integer> job;
    ArrayList<Thread> threads = new ArrayList<>();
    int i=1;

    public UsingThreadsForJob(ArrayList<Integer> job) {
        this.job = job;
    }

    private synchronized void printNext() {
        if(i<=40 || Thread.currentThread().getName().equals("main")) {
            /*  this is called double checked locking,
                first condition is checked outside the synchronized block, then again
                condition is checked inside the synchronized block
             */
            System.out.println(Thread.currentThread().getName()+" : "+ i + ": "+ job.get(i));
            i++;
        }
    }

    public void doJob() throws InterruptedException {
        Runnable task = () -> {
            while(i<=40) { // multiple threads passed this condition, before updation of i
                printNext(); // only this is synchronised, multiple threads came to this line and waiting, before i is updated
            }
        };

        while (i <= 10) {
            printNext();
        }

        for(int j=1; j<=5; j++) { // create 5 threads
           Thread t = new Thread(task);
           threads.add(t);
        }

        for(Thread t: threads) {
            t.start(); // this is forking multiple threads
        }
        for (Thread thread : threads) {
            thread.join(); // waiting for results of all threads, waiting for all threads to complete
        }

        // print 41-50 using single thread again
        while (i <= 50) {
            printNext();
        }
    }
}
