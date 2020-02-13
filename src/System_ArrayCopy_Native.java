/*
Testing code from stackoverflow

output:

for loop: 30
System.arrayCopy: 13
for loop: 25
System.arrayCopy: 13
for loop: 25
System.arrayCopy: 14


Arraylist, Strings.copyValueOf(), .toCharArray(), Arrays.copyOf(), all internally uses System.arrayCopy ( which is native implementation,
implemented in a library provided by JVM) ( probably memcpy, memove)

System.arrayCopy Time complexity: o(n), where n is number of elements to be copied, source array's size

Copying an array would still depend on the size since bytes need to be moved around, so even System.arraycopy() would be O(n)
probably just faster by some factor due to the missing loop.

*/

public class System_ArrayCopy_Native {
    public static void main(String []args) {

        for (int count = 0; count < 3; count++) {
            int size = 0x00ffffff;
            long start, end;
            Integer[] integers = new Integer[size];
            Integer[] loopCopy = new Integer[size];
            Integer[] systemCopy = new Integer[size];

            for (int i = 0; i < size; i++) {
                integers[i] = i;
            }

            start = System.currentTimeMillis();
            for (int i = 0; i < size; i++) {
                loopCopy[i] = integers[i];
            }
            end = System.currentTimeMillis();
            System.out.println("for loop: " + (end - start));

            start = System.currentTimeMillis();
            System.arraycopy(integers, 0, systemCopy, 0, size);
            end = System.currentTimeMillis();
            System.out.println("System.arrayCopy: " + (end - start));
        }
    }
}
