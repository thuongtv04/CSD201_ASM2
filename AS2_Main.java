import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class AS2_Main {
    // hàm hiển thị menu lựa chọn
    static void showMenu() {
        System.out.println("+------------------------------------+");
        System.out.printf("%-35s %2s %n", "| Choose one of this options:", "|");

        System.out.printf("%-35s %2s %n", "| Product list:", "|");

        System.out.printf("%-35s %s %n", "| 1. Load data from file and display", "|");

        System.out.printf("%-35s %2s %n", "| 2. Input & add to the end.", "|");

        System.out.printf("%-35s %2s %n", "| 3. Display data", "|");

        System.out.printf("%-35s %2s %n", "| 4. Save product list to file.", "|");

        System.out.printf("%-35s %2s %n", "| 5. Search by ID", "|");

        System.out.printf("%-35s %2s %n", "| 6. Delete by ID", "|");

        System.out.printf("%-35s %2s %n", "| 7. Sort by ID.", "|");

        System.out.printf("%-35s %2s %n", "| 8. Convert to Binary", "|");

        System.out.printf("%-35s %2s %n", "| 9. Load to stack and display", "|");

        System.out.printf("%-35s %2s %n", "| 10. Load to queue and display.", "|");

        System.out.printf("%-35s %2s %n", "| 0. Exit", "|");
        System.out.println("+------------------------------------+");
    }
    // hàm Main điều khiển luồng chương trình
    public static void main(String[] args) {

    // ghi san thong tin cu vao file data------------------------------------------
//        ArrayList<Product> arraylist = new ArrayList<>();
//        Product p1 = new Product("P03", "Sugar",12, 25.1);
//        Product p2 = new Product("P01", "Miliket",10, 5.2);
//        Product p3 = new Product("P02", "Apple",5, 4.3);
//        Product p4 = new Product("P05", "Rose",7, 15.4);
//        Product p5 = new Product("P07", "Beer",11, 12.2);
//        Product p6 = new Product("P04", "Book",9, 5.2);
//        arraylist.add(p1);
//        arraylist.add(p2);
//        arraylist.add(p3);
//        arraylist.add(p4);
//        arraylist.add(p5);
//        arraylist.add(p6);
//
//        File file = new File("data.txt");
//        try{
//            OutputStream outputStream = new FileOutputStream(file);
//            OutputStreamWriter osw = new OutputStreamWriter(outputStream);
//            for(Product p : arraylist){
//                osw.write(p.getbCode()+"\n");
//                osw.write(p.getTittle()+"\n");
//                osw.write(p.getQuantity()+"\n");
//                osw.write(p.getPrice()+"\n");
//            }
//            osw.close();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
    //-------------------ket thuc ghi thong tin cu----------------------------

        Scanner input = new Scanner(System.in);
        MyList<Product> list = new  MyList<>();
        MyStack<Product> stack = new MyStack<>();
        MyQueue<Product> queue = new MyQueue<>();
        OperationToProduct operation = new OperationToProduct();
        int choice = 0;
        System.out.println();

        while (true){
            // hiển thị menu lựa chọn
            showMenu();
            // kiểm tra giá trị nhập vào
            try {
                choice = input.nextInt();
            }catch (Exception e){
                System.out.println("You enter not a number!");
                e.printStackTrace();
            }
            if(choice == 0){
                    System.out.println("\nThanks!!!");
                    break;
            }
            // lệnh rẽ nhánh cho phép lựa chọn chức năng.
            switch (choice) {

                case 1:
                    operation.getAllItemsFromFile("data.txt", list);
                    System.out.println("Successfully!\n");
                    break;
                case 2:
                    operation.addLast(list);
                    break;
                case 3:
                    operation.displayAll(list);
                    break;
                case 4:
                    operation.writeAllItemToFile("data.txt",list);
                    System.out.println("Successfully!");
                    operation.writeResult("console_output.txt", "Successfully!");
                    break;
                case 5:
                    operation.searchByCode(list);
                    break;
                case 6:
                    operation.deleteByCode(list);
                    System.out.println("Deleted!\n");
                    break;
                case 7:
                    operation.sortByCode(list);
                    System.out.println("Successfully!");
                    operation.writeResult("console_output.txt", "Successfully!");
                    break;
                case 8:
                    int quantity = list.getHead().getInfo().getQuantity();
                    String result = "Quantity = " + quantity + "=> ";
                    System.out.print(result);
                    int[] arr = operation.convertToBinary(quantity);
                    // in kết quả từ cuối mảng trả về
                    for (int i = arr.length - 1; i >= 0; i--) {
                        System.out.print(arr[i]);
                        result += arr[i];
                    }
                    System.out.println();
                    // ghi kết quả vào file out_put
                    operation.writeResult("console_output.txt", result);
                    break;
                case 9:
                    operation.getAllItemsFromFile("data.txt", stack );
                    System.out.println(stack);
                    System.out.println("Successfully!\n");
                    break;
                case 10:
                    operation.getAllItemsFromFile("data.txt", queue);
                    System.out.println(queue);
                    System.out.println("Successfully!\n");
                    break;
                default:
                    System.out.println("Input number is wrong!\n");
                    break;
            }
        }
    }
}




