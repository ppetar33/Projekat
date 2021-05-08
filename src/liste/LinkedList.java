//package liste;
//
//import java.util.Iterator;
//import java.util.List;
//
//public class LinkedList<T> implements Iterable<T>{
//    private ListNode<T> head; // head pokazuje na prvi element
//    private ListNode<T> tail;
//    private int listSize; // imamo atribut za koji cemo da cuvamo velicinu liste i kada dodamo novi element uvecacemo listSize za 1, a kada brisemo smanjicemo za 1
//
//    ListNode<T> getHead(){
//        return this.head;
//    }
//
//    public void addFirst(T element){
//        ListNode<T> newNode = new ListNode<T>(null,element, head); // kreiramo novi element
//        head = newNode;
//        if(listSize == 0){
//            tail = head;
//        }
//        listSize++;
//    }
//
//    public void addLast(T element){
//        ListNode<T> newNode = new ListNode<T>(element); //konstruktor sa 1 parametrom jer je next null
//        if(tail == null){
//            head = newNode;
//            tail = head;
//            listSize++;
//        }
//        else{
//            tail.setNext(newNode);
//            tail = newNode;
//            listSize++;
//        }
//    }
//
//    public void add(int index, T element){
//        // ako je prazna lista listSize=0 ako hocu da dodam novi element dobicu 0 = 0 pa cu dobiti exception
//
//        if (index == 0 && listSize == 0){
//            addFirst(element);
//            return;
//        }
//
//        if (index < 0 || index >= listSize){
//            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + listSize);
//        }
//
//        if (index == listSize - 1){
//            addLast(element);
//            return;
//        }
//
//        ListNode currentNode = head;
//
//        for (int i = 1; i < index; i++) {
//            currentNode = currentNode.getNext();
//        }
//
//        ListNode newNode = new ListNode(currentNode.getPrevious(),element,currentNode.getNext());
//        currentNode.setNext(newNode);
//        listSize++;
//    }
//
//    public T getFirst(){
//        if(head != null){
//            return head.getElement();
//        }
//        else{
//            throw new RuntimeException("List is empty!");
//        }
//
//    }
//
//    public T getLast(){
//        if(tail != null){
//            return tail.getElement();
//        }
//        else{
//            throw new RuntimeException("List is empty!");
//        }
//
//    }
//
//    public T get(int index){
//        if(index < 0 || index >= listSize){
//            throw new IndexOutOfBoundsException("Index out of bounds!");
//        }
//        int i = 0;
//        ListNode<T> currentNode = head;
//
//        while (currentNode != null){
//            if(index == i){
//                return currentNode.getElement();
//            }
//
//            currentNode = currentNode.getNext();
//            i++;
//        }
//
//        return null;
//    }
//
//    public void set(int index, T element){
//
//    }
//
//    public void removeFirst(){
//        if(head != null){
//            head = head.getNext();
//            listSize--;
//        }else{
//            throw new RuntimeException("List is empty!");
//        }
//    }
//
//    public void removeLast(){
//        if(head == null){
//            throw new RuntimeException("List is empty!");
//        }
//        ListNode<T> previousNode = null;
//        ListNode<T> currentNode = head;
//
//        while(currentNode.getNext() != null){
//            previousNode = currentNode;
//            currentNode = currentNode.getNext();
//        }
//
//        if(previousNode != null){
//            previousNode.setNext(null);
//            tail = previousNode;
//        }
//        else{
//            head = null;
//            tail = null;
//        }
//
//        listSize--;
//    }
//
//    public void remove(int index){
//
//    }
//
//    public void remove(T element){
//
//    }
//
//    public void clear(){
//
//
//    }
//
//    public int size(){
//        return listSize;
//    }
//
//    private ListNode<T> getLastNode() {
//        if(head == null){
//            return null;
//        }
//        ListNode<T> currentNode = head;
//
//        while(currentNode.getNext() != null){
//            currentNode = currentNode.getNext();
//        }
//        return currentNode;
//    }
//
//    @Override
//    public Iterator<T> iterator() {
//        return new LinkedListIterator<T>(this);
//    }
//}
