

/* From wiki
    In computing, the modulo operation finds the remainder after division of one number by another
    (called the modulus of the operation).
    Given two positive numbers, a and n, a modulo n (abbreviated as a mod n) is the remainder of the
    Euclidean division of a by n, where a is the dividend and n is the divisor.

    when a/b ,

    a = b * q + r
*/

public class Mod_Operator_Internal_Working {

    public static void main(String [] args) {

        // 1%10 =1 how ?

        System.out.println(1/10); // --> 0 in java , 0.1 in javascript

        // cast one number to double, int is default data type for all numbers in java and (int/int gives int)

        System.out.println("casting: "+ (double)1/10); // --> 0.1 in java
        System.out.println("casting: "+ 1.0/10);       // --> 0.1 in java



        System.out.println(1%10); // --> 1 in java , 1 in javascript

        int a = 1;
        int b = 10;
        int q = 0;
        int r = 0;

        q = a/b; // --> 0
        r = a - b * q;


        System.out.println("Euclidean division: "+r);

    }
}
