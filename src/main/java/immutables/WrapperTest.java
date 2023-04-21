package immutables;

public class WrapperTest {
    public static void main(String[] args) {
        Foo foo1 = new Foo();
        Foo foo2 = new Foo();
        Foo foo3 = new Foo();
        Integer i = new Integer(1);
        foo1.setI(i);
        ++i;
        foo2.setI(i);
        ++i;
        foo3.setI(i);

        System.out.println(foo1.getI());
        System.out.println(foo2.getI());
        System.out.println(foo3.getI());

        foo1.print();
        foo2.print();

        foo3.print();
        System.out.println(foo1.toString());


        //Math.
    }
}
class Foo {
    Integer i;
    public Integer getI() { return i; }
    public void setI(Integer i) { this.i = i; }

    void print() {
        System.out.println(this);
    }
}
