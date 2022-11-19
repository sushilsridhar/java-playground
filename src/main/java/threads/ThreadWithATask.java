package threads;

public class ThreadWithATask {
    public static void main(String[] args) {

        System.out.println(Thread.currentThread().getName()+" hello world");
        Task task = new Task();
        Thread t = new Thread(task);
        t.start();
        System.out.println(Thread.currentThread().getName()+" hello world");
    }
}

class Task implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" hello world");
    }
}
