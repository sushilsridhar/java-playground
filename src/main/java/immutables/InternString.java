package immutables;

public class InternString {
    public static void main(String[] args) {

        String t1 = "hello";
        String t2 = "hello";
        String t3 = new String("hello");
        String t4 = new String("hello").intern();
        String t5 = new String("hello");
        String t6 = new String("hello");

        System.out.println("t: "+ t1==t2);
        System.out.println("t: "+ t1==t4);

        System.out.println("t1: "+ System.identityHashCode(t1));
        System.out.println("t2: "+ System.identityHashCode(t2));
        System.out.println("t3: "+ System.identityHashCode(t3));
        System.out.println("t4: "+ System.identityHashCode(t4));
        System.out.println("t5: "+ System.identityHashCode(t5));
        System.out.println("t6: "+ System.identityHashCode(t6));

        /*
            t1: 1627674070
            t2: 1627674070
            t3: 1360875712
            t4: 1627674070
            t5: 1625635731
            t6: 1580066828
         */

        //Collections.sort();
    }
}
