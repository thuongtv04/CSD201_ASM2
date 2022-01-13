public class MyQueue<T> {
    private Node<T> head;
    private Node<T> tail;

    // construction
    public MyQueue(){
        head = tail = null;
    }
    public MyQueue(Node<T> head, Node<T> tail) {
        this.head = head;
        this.tail = tail;
    }

    // hàm kiểm tra Queue trống
    public boolean isEmpty(){
        return this.head == null;
    }

    // hàm Enqueue
    public void enqueue(Node<T> node){
        if(isEmpty()){
            this.head = this.tail = node;
        }else{
            this.tail.setNext(node);
            this.tail = node;
        }
    }

    // hàm Dequeue
    public Node<T> dequeue(){
        if(isEmpty()){
            return null;                // nếu queue trống trả về null
        }else{
            Node<T> node = this.head;
            this.head = this.head.getNext();
            this.tail = null;
            return node;
        }
    }

    @Override
    public String toString(){
        Node<T> node = this.head;
        String result = String.format("%5s %18s %22s %22s", " ID", "TITTLE ", " QUANTITY", " PRICE");
        result += "\n+-------------------------------------------------------------------------------+\n";
        while (node != null){
            result += node.getInfo()+"\n";
            node = node.getNext();
        }
        result += "+-------------------------------------------------------------------------------+";
        return result;

    }
}
