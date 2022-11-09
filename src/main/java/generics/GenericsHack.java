package generics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GenericsHack {
        public static void main(String[] args) {

            ArrayList<String> strList = new ArrayList<>();
            strList.add("asdsa");
            strList.add("gdndgn");
            ArrayList<Object> objList = new ArrayList<>();
            objList.add(10);
            objList.add(true);
            objList.add(new MyClass());

            Collection<? extends String> a = (List) objList;

            ArrayList<String> b = (ArrayList) objList;

            for(Object x: b) {
                System.out.println(x);
            }

            strList.addAll((List)objList);

           // System.out.println(strList.size());

            Object[] a1 = strList.toArray();
            for(int i=0; i<a1.length; i++) {
                //System.out.println(a1[i]);
            }
        }

        static class MyClass {
        }
}
