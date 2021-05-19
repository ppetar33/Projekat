package liste;


public class ListNode <T> {
    private T element;
    private ListNode<T> next;

    public ListNode(T element) {
        this.element = element;
    }

    public ListNode(T element, ListNode<T> next){
        this.element = element;
        this.next = next;
    }

    public T getElement(){
        return this.element;
    }

    public ListNode<T> getNext(){
        return this.next;
    }

    public void setElement(T element){
        this.element = element;
    }

    public void setNext(ListNode<T> node){
        this.next = node;
    }
}