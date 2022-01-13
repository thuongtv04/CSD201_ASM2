public class Node <T>{
    private T info;         // biến lưu thông tin của Node
    private Node<T> next;   // biến Node tiếp theo

    // construction
    public Node(){}

    public Node(T info, Node<T> next){
        this.info = info;
        this.next = null;
    }

    // getter and setter------------------------------------
    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
    // end getter and setter---------------------------------

    @Override
    public String toString(){
        return getInfo().toString();
    }
}
