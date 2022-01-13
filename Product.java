public class Product {
    private String bCode;   // biến lưu ID sản phẩm
    private String tittle;  // biến lưu tên sản phẩm
    private int quantity;   // biến lưu số lượng sản phẩm
    private double price;   // biến lưu giá thành sản phẩm

    // constructions
    public Product(){}

    public Product(String bCode, String title, int quantity, double price){
        this.bCode = bCode;
        this.tittle = title;
        this.quantity = quantity;
        this.price = price;
    }

    // getter and setter--------------------------------------------
    public String getbCode() {
        return bCode;
    }

    public void setbCode(String bCode) {
        this.bCode = bCode;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    // end getter and setter------------------------------------------
    @Override
    public String toString(){
        return String.format("|%5s |%20s  | |%20d  | |%20s  |", bCode, tittle, quantity, price);
    }
}
