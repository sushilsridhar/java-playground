package immutables;

public class String_Length_Problem {

    public static void main(String [] args) {
        String a = "\u0041"; // --> Code point is how one represent characters


        String b = "\uD835\uDD38"; // -->  Mathematical double-struck capital A

        System.out.println(a);
        System.out.println(b);

        System.out.println(a.length());
        System.out.println(b.length()); // returns 2, should be 1

        System.out.println(a.codePointAt(0));

        System.out.println(b.codePointAt(1));
        System.out.println(b.codePointCount(0, b.length())); // returns correct length
    }

}
