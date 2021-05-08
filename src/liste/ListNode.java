//package liste;
//
//
//public class ListNode <T> {
//    private T element; // T je object
//    private ListNode<T> next; // next ce biti instanca klase
//    private ListNode<T> previous;
//
//    public ListNode(T element){ // koristi se za poslednji element kad nam odgovara da je taj element null
//        this.element = element;
//    }
//
//    public ListNode(ListNode<T> previous, T element, ListNode<T> next){
//        this.previous = previous;
//        this.element = element;
//        this.next = next;
//    }
//
//    public T getElement(){
//        return this.element;
//    }
//
//    public ListNode<T> getNext(){
//        return this.next;
//    }
//
//    public void setElement(T element){
//        this.element = element;
//    }
//
//    public void setNext(ListNode<T> node){
//        this.next = node;
//    }
//
//    public ListNode<T> getPrevious() {
//        return previous;
//    }
//
//    public void setPrevious(ListNode<T> previous) {
//        this.previous = previous;
//    }
//}