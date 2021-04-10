import java.util.Iterator;

public class Main {

    public static void main(String[] args) {

        MyLinkedList<Integer> first = new MyLinkedList<>();

        // empty
        //System.out.println(first.peakLast());


        // 1 element
        first.add(1);
        //System.out.println(first.peakLast());

        // middle
        first.add(2);
        first.add(3);
        first.add(4);
        first.add(5);
        //System.out.println(first.peakLast());
        for (Iterator<Integer> it = first.iterator(); it.hasNext(); ) {
            int i = it.next();
            System.out.println(i);

        }


        // beginning element
        //System.out.println(first.peakLast());

        //last element
        //System.out.println(first.peakLast());


    }
};
