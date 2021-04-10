import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T>{


    private ListNode<T> head;
    private ListNode<T> tail;
    private int currentSize = 0;


    public MyLinkedList(){
    }

    public MyLinkedList(T data){
        this.head = new ListNode<>(data);
        this.tail = this.head;
        currentSize++;
    }

    private static class ListNode<T>{
        private T data;
        private ListNode<T> next;
        private ListNode<T> prev;

        private ListNode(){
        }
        private ListNode(T data){
            this.data = data;
        }
    }

    public void add(T data){
        if(this.head != null){
            this.tail.next = new ListNode<>(data);
            this.tail.next.prev = this.tail;
            this.tail = this.tail.next;
        }
        else{
            ListNode<T> newNode = new ListNode<>(data);
            this.head = this.tail = newNode;
        }
        currentSize++;
    }

    public void add(MyLinkedList<T> linkedList) {
        linkedList.head.prev = this.tail ;
        this.tail.next = linkedList.head;
        this.tail = this.lastNode();
        this.currentSize = this.currentSize + linkedList.currentSize;
    }

    public T removeFirst(){
        if(this.head != null){
            T removed = this.head.data;

            if(this.head.next== null){
                this.head = this.tail = null;
            }
            else{
                this.head = this.head.next;
                this.head.prev = null;
            }
            this.currentSize--;
            return removed;
        }
        return null;
    }

    public T removeLast(){
        if(currentSize>0){
            T removed = this.tail.data;
            this.tail = this.tail.prev;
            this.tail.next = null;

            this.currentSize--;
            return removed;
        }
        return null;
    }

    public T removeElement(T obj){
        //ListNode<T> prev = null;
        ListNode<T> current = this.head;
        while(current != null){
            if(((Comparable<T>)current.data).compareTo(obj)==0){
                if(current == this.head){
                    return removeFirst();
                    /*                    this.head = this.head.next;*/
                }
                else if(current == this.tail){
                    return removeLast();
                    /*this.tail = prev;
                    prev.next = null;*/
                }
                else{
                    current.prev.next = current.next;
                    //prev.next = current.next;
                }
                this.currentSize--;
                return current.data;
            }
            current.prev = current;
            current = current.next;
        }
        return null;
    }

    public boolean contains(T obj){
        //ListNode<T> prev = null;
        ListNode<T> current = this.head;

        while(current != null){
            if(((Comparable<T>)current.data).compareTo(obj)==0){
                return true;
            }
            current.prev = current;
            current = current.next;
        }
        return false;
    }

    public void insertFirst(T data){
        ListNode<T> newNode = new ListNode<>(data);
        if(this.head == null){
            this.tail = newNode;
        }
        if(this.head != null){
            newNode.next = this.head;
            this.head.prev = newNode;
        }
        this.head = newNode;
        currentSize++;
    }
    public void insertLast(T data){
        ListNode<T> newNode = new ListNode<>(data);

        if(this.head == null){
            this.head = newNode;
            this.tail = newNode;
        }
        else{
            newNode.prev = this.tail;
            this.tail.next = newNode;
            this.tail = this.tail.next;
        }
        currentSize++;
    }

    public void insertAt(T data, int position){
        //TODO
        if(position == 0){
            insertFirst(data);
            return;
        }
        else if(position == this.currentSize+1){
            insertLast(data);
            return;
        }
        else if(position >= this.currentSize+2){
            throw new IllegalArgumentException("position is out of range");
        }
        else{
            ListNode<T> newNode = new ListNode<>(data);
            ListNode<T> previousNode = this.head;
            int index = 0;
            while(index<position-1){
                previousNode = previousNode.next;
                index++;
            }
            newNode.next = previousNode.next;
            newNode.prev = previousNode;
            previousNode.next.prev = newNode;
            previousNode.next = newNode;
            //this.tail = this.lastNode();
            currentSize++;
        }
    }

    public T peakFirst(){
        if(this.head == null){
            return null;
        }
        return this.head.data;
    }

    public T peakLast(){
        if(this.tail == null){
            return null;
        }
        return this.tail.data;
    }

    private ListNode<T> lastNode(){
        ListNode<T> lastNode = this.head;
        while(lastNode.next != null){
            lastNode = lastNode.next;
        }
        return lastNode;
    }

    public void showAll() {
        ListNode<T> currentNode = this.head;

        while(currentNode != null){
            System.out.println(currentNode.data);
            currentNode = currentNode.next;
        }

    }

    public int getLength() {
        int length = 0;
        ListNode<T> currentNode = this.head;
        if(currentNode.data == null){
            return length;
        }
        while(currentNode != null){
            length++;
            currentNode = currentNode.next;
        }
        return length;
    }

    public Iterator<T> iterator() {
        return new IteratorHelper();
    }

    private class IteratorHelper implements Iterator<T> {

        private ListNode<T> index;

        public IteratorHelper() {
            this.index = head;
        }

        @Override
        public boolean hasNext() {
            return (this.index != null);
        }

        @Override
        public T next() {
            if (hasNext()) {
                T value = this.index.data;
                this.index = this.index.next;
                return value;
            }
            throw new NoSuchElementException();
        }
    }
}
