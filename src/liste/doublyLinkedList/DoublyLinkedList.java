package liste.doublyLinkedList;

import java.util.Iterator;

public class DoublyLinkedList<T> implements Iterable<T> {

    ListNode<T> head, tail = null;
    private int listSize;

    ListNode<T> getHead(){
        return head;
    }

    public void addFirst(T element) {

        ListNode<T> newNode = new ListNode<T>(element);

        if(head == null) {
            head = tail = newNode;
            head.previous = null;
            tail.next = null;
            listSize++;
        }
        else {
            head.previous = newNode;
            newNode.next = head;
            newNode.previous = null;
            head = newNode;
            listSize++;
        }
    }
    public void addLast(T element) {
        ListNode<T> newNode = new ListNode<T>(element);
        if(head == null) {
            head = tail = newNode;
            head.previous = null;
            tail.next = null;
            listSize++;
        }
        else {
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
            tail.next = null;
            listSize++;
        }
    }
    public void add(T element) {

        ListNode<T> temp = new ListNode<T>(element);

        if (head == null) {
            head = temp;
            tail = head;
        } else {
            tail.next = temp;
            temp.previous = tail;
            tail = temp;
        }

        listSize++;

    }
    public void deleteFromEnd() {
        if(head == null) {
            return;
        }
        else {
            if(head != tail) {
                tail = tail.previous;
                tail.next = null;
                listSize--;
            }
            else {
                head = tail = null;
            }
        }
    }
    public void deleteFromStart() {
        if(head == null) {
            return;
        }
        else {
            if(head != tail) {
                head = head.next;
                head.previous = null;
                listSize--;
            }
            else {
                head = tail = null;
            }
        }
    }
    public void deleteAt(int position){
        ListNode<T> temp = head;
        for(int i = 0; i < position; i++){
            temp = temp.getNext();
        }
        temp.getPrevious().setNext(temp.getNext());
        temp.getNext().setPrevious(temp.getPrevious());
    }
    public T getFirst(){
        if(head != null){
            return head.getElement();
        }
        else{
            throw new RuntimeException("Prazna lista");
        }
    }
    public T getLast(){
        if(tail != null){
            return tail.getElement();
        }
        else{
            throw new RuntimeException("Prazna lista");
        }
    }
    public T get(int index){
        if(index < 0 || index >= listSize){
            throw new IndexOutOfBoundsException("Index out of bounds!");
        }
        int i = 0;
        ListNode<T> currentNode = head;

        while (currentNode != null){
            if(index == i){
                return currentNode.getElement();
            }

            currentNode = currentNode.getNext();
            i++;
        }

        return null;
    }
    public int size(){
        return listSize;
    }

    public boolean isEmpty() {
        if(listSize == 0){
            return true;
        }
        return false;
    }

    public void sortListOfIntegers() {
        ListNode<Integer> current = null, index = null;
        int temp;
        if(head == null) {
            return;
        }
        else {
            for(current = (ListNode<Integer>) head; current.next != null; current = current.next) {
                for(index = current.next; index != null; index = index.next) {
                    if(current.element > index.element) {
                        temp = current.element;
                        current.element = index.element;
                        index.element = temp;
                    }
                }
            }
        }
    }


    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator<T>(this);
    }

}
