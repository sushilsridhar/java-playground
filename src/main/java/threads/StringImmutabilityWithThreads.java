package threads;

import java.util.ArrayList;

public class StringImmutabilityWithThreads {

    public static void main(String[] args) {
        
        Runnable r = new Runnable() {
            @Override
            public void run() {
                String t1 = "hello";
                String t2 = new String("hello").intern();
                String t3 = new String("hello");

                int a = 10;
                Integer aWrapper = new Integer(10);
                ArrayList<Integer> l = new ArrayList<>();
                int[] lint = new int[2];
                System.out.println(Thread.currentThread().getName()+" "+
                        "t1:"+ System.identityHashCode(t1) +" "+
                        "t2:"+ System.identityHashCode(t2) +" "+
                        "t3:"+ System.identityHashCode(t3) +" "+

                        "a:"+ System.identityHashCode(a)  +" "+
                        "aWrapper:"+ System.identityHashCode(aWrapper)+ " "+
                        "l:"+ System.identityHashCode(l) +" "+
                        "intl:"+ System.identityHashCode(lint));
            }
        };

        Thread t = new Thread(r);
        Thread t1 = new Thread(r);

        t.start();
        t1.start();
    }
}
