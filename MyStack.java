public class MyStack<T> {
    private Node<T> head;

    // construction
    public MyStack(){
        head = null;
    }

    // hàm kiểm tra stack trống
    public boolean isEmpty(){
        return(head==null);
    }

    // hàm push node mới vào stack
    public void push(Node<T> node){
        node.setNext(this.head);
        this.head = node;
    }

    // hàm đọc thông tin head trong stack
    public Node<T> peek() {
        if(isEmpty()){
            return null;        // nếu stack trống trả về null
        }else {
            return this.head;
        }
    }

    // hàm pop phần tử khỏi stack
    public Node<T> pop() {
        if(isEmpty()){
            return null;        // nếu stack trống trả về null
        }else {
            Node<T> node = this.head;
            this.head = this.head.getNext();
            return (node);
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
