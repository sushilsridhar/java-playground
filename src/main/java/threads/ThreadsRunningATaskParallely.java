package threads;

import java.util.ArrayList;

public class ThreadsRunningATaskParallely {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Runnable printThreadName = () -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("count :" + i + " \t" + Thread.currentThread().getName());
            }
        };

        ArrayList<Thread> threadList = new ArrayList<>(20);
        for (int i = 0; i < 20; i++) {
            threadList.add(new Thread(printThreadName));
        }

        threadList.forEach(Thread::start);
    }
}
