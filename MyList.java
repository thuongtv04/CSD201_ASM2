public class MyList<T> {
    private Node<Product> head;
    private Node<Product> tail;

    // construction
    public MyList(){
        head = tail = null;
    }

    //getter and setter------------------------
    public void setHead(Node<Product> head) {
        this.head = head;
    }

    public void setTail(Node<Product> tail) {
        this.tail = tail;
    }

    public Node<Product> getHead() {
        return head;
    }

    public Node<Product> getTail() {
        return tail;
    }
    //end getter and setter-------------------------

    // hàm kiểm tra danh sách trống
    boolean isEmpty(){
        return this.head == null;
    }

    // hàm trả về dộ dài của danh sách
    public int length(){
        Node<Product> current = this.head;
        int length = 0;
        while (current != null){
            length++;
            current = current.getNext();
        }
        return length;
    }

    // xoá toàn bộ danh sách
    public void clear(){
        head = tail = null;
    }

    // Hàm thêm phần tử vào đầu danh sách
    public void insertToHead(T item){
        Node<Product> newNode = new Node(item, this.head);
        newNode.setNext(this.head);
        this.head = newNode;
    }

    // Hàm thêm phần tử vào cuốidanh sách
    public void insertToTail(T item){
        Node<Product> newNode = new Node(item, this.head);
        //kiem tra nếu danh sách trống thì thêm phần tử vào đầu danh sách.
        if(isEmpty()){
            newNode.setNext(this.head);
            this.head = this.tail =newNode;
        // thêm phần tử vào cuối danh sách.
        }else {
            this.tail.setNext(newNode);
            this.tail = newNode;
        }
    }

    @Override
    public String toString(){
        Node<Product> current = this.head;
        String result = String.format("%5s %18s %22s %22s", " ID", "TITTLE ", " QUANTITY", " PRICE");
        result += "\n+-------------------------------------------------------------------------------+";
        while (current != null){
            result += "\n"+current.getInfo();
            current = current.getNext();
        }
        result += "\n+-------------------------------------------------------------------------------+";
        return  result;
    }
}
