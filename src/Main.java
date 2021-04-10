import java.util.Iterator;

public class Main {

    public static void main(String[] args) {

        MyLinkedList<Integer> first = new MyLinkedList<>();

        first.removeElement(0);
        first.add(1);
        first.add(2);
        first.add(3);
        first.removeFirst();
        first.insertFirst(1);
        first.removeLast();
        first.insertLast(3);
        first.insertAt(0,0);
        first.insertAt(11,1);
        first.insertAt(3,3);
        first.insertAt(6,6);
        first.insertAt(5,6);
        first.removeElement(0);
        first.removeElement(6);
        first.removeLast();

        Iterator<Integer> it = first.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
        for (Iterator<Integer> iter = first.iterator(); iter.hasNext(); ) {
            int i = iter.next();
            System.out.println(i);
        }

    }
};
