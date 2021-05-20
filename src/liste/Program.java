package liste;

import liste.doublyLinkedList.DoublyLinkedList;

public class Program {

    public static void main(String[] args){

        DoublyLinkedList<Object> doublyLinkedList = new DoublyLinkedList<>();

        doublyLinkedList.addFirst(1);
        doublyLinkedList.addLast(2);
        doublyLinkedList.addFirst(3);
        doublyLinkedList.add(1,4);

        for(Object s : doublyLinkedList){
            System.out.println(s);
        }




        Object x = doublyLinkedList.getFirst().toString() + doublyLinkedList.getLast().toString();
        System.out.println(x);


        System.out.println("\nBrisanje sa pocetka");
        doublyLinkedList.deleteFromStart();
        System.out.println("Nova lista: ");
        for(Object s : doublyLinkedList){
            System.out.println(s);
        }

        System.out.println("\nBrisanje sa kraja");
        doublyLinkedList.deleteFromEnd();
        System.out.println("Nova lista: ");
        for(Object s : doublyLinkedList){
            System.out.println(s);
        }


    }
}
