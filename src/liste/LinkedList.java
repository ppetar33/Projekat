package liste;

import java.util.Iterator;

public class LinkedList<T> implements Iterable<T>{
    private ListNode<T> head;
    private ListNode<T> tail;
    private int listSize;

    ListNode<T> getHead(){
        return this.head;
    }

    public void addFirst(T element){
        ListNode<T> newNode = new ListNode<T>(element, head);
        head = newNode;
        if(listSize == 0){
            tail = head;
        }
        listSize++;
    }

    public void addLast(T element){
        ListNode<T> newNode = new ListNode<T>(element);
        if(tail == null){
            head = newNode;
            tail = head;
            listSize++;
        }
        else{
            tail.setNext(newNode);
            tail = newNode;
            listSize++;
        }
    }

    public void add(int index, T element){

    }

    public T getFirst(){
        if(head != null){
            return head.getElement();
        }
        else{
            throw new RuntimeException("List is empty!");
        }

    }

    public T getLast(){
        if(tail != null){
            return tail.getElement();
        }
        else{
            throw new RuntimeException("List is empty!");
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

    public void set(int index, T element){

    }

    public void removeFirst(){
        if(head != null){
            head = head.getNext();
            listSize--;
        }else{
            throw new RuntimeException("List is empty!");
        }
    }

    public void removeLast(){
        if(head == null){
            throw new RuntimeException("List is empty!");
        }
        ListNode<T> previousNode = null;
        ListNode<T> currentNode = head;

        while(currentNode.getNext() != null){
            previousNode = currentNode;
            currentNode = currentNode.getNext();
        }

        if(previousNode != null){
            previousNode.setNext(null);
            tail = previousNode;
        }
        else{
            head = null;
            tail = null;
        }

        listSize--;
    }

    public void remove(int index){

    }

    public void remove(T element){

    }

    public int size(){
        return listSize;
    }

    private ListNode<T> getLastNode() {
        if(head == null){
            return null;
        }
        ListNode<T> currentNode = head;

        while(currentNode.getNext() != null){
            currentNode = currentNode.getNext();
        }
        return currentNode;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator<T>(this);
    }
}