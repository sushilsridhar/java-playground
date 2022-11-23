package threads;

import java.util.Vector;

public class Synchronisation {

    public static void main(String[] args) {
        SyncTasks tasks1 = new SyncTasks(1);
        SyncTasks tasks2 = new SyncTasks(2);
        tasks1.doTask();
        tasks2.doTask();


        /*
            TODO lock monitor sync mutex
            syncWait1 and syncWait2 acquires a lock on task1 object, they can't run parallely
            syncWait1 and syncWait2 acquires a lock on task2 object, they can't run parallely

            syncWait1 and syncWait1 of task1 and task2 object can run parallely, because they acquired lock on different objects

            this is called monitor,
            try an example with Vector

            experiment with static methods and static classes

            https://stackoverflow.com/questions/3362303/whats-a-monitor-in-java
            https://stackoverflow.com/questions/15438727/if-i-synchronized-two-methods-on-the-same-class-can-they-run-simultaneously

            try with arraylist, why it is not thread safe

         */
    }
}

class SyncTasks {
    int id;

    public SyncTasks(int id) {
        this.id = id;
    }
    //        final Object lock1 = new Object();
//        final Object lock2 = new Object();

    synchronized void syncWait1() {
        System.out.println("obj:" + id + "  method-1 start: "+ Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("obj:" + id + "  method-1 end: "+ Thread.currentThread().getName());
    }

    synchronized void syncWait2() {
        System.out.println("obj:" + id + "  method-2 start: "+ Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("obj:" + id + "  method-2 end: "+ Thread.currentThread().getName());
    }

    void doTask() {

        Thread t1 = new Thread(this::syncWait1);
        Thread t2 = new Thread(this::syncWait2);

        t1.start();
        t2.start();


    }

}
