import java.io.*;
import java.util.Scanner;

public class OperationToProduct {
    Scanner input = new Scanner(System.in);
    private static int index;

    /**------------------------------------------------------------------------
     * Hàm đọc file từ dữ liệu có sẵn.
     * @param fileName tên file cần đọc.
     * @param list danh sách lưu sản phẩm được đọc từ file.
     */
    public void getAllItemsFromFile(String fileName, MyList<Product> list){
        // khởi tạo đối tượng file
        File file = new File(fileName);
        try {
            // tạo một dòng chuẩn bị cho việc đọc file
            InputStream inputStream = new FileInputStream(file);
            // tạo một đối tượng đọc file với dòng trên
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            // tạo bộ đệm (đảm bảo file vẫn có thể đọc tiếp nếu chương trình kết thúc quá nhanh)
            BufferedReader reader = new BufferedReader(inputStreamReader);

            String line;
            Product product = new Product();    // tạo đối tượng lưu thông tin sản phẩm
            int count = 0;
            // lặp đọc file
            while ((line = reader.readLine()) != null) {
                count++;
                //lưu thông tin từng dòng vào đối tượng tương ứng
                switch (count) {
                    case 1:
                        product.setbCode(line);
                        break;
                    case 2:
                        product.setTittle(line);
                        break;
                    case 3:
                        product.setQuantity(Integer.parseInt(line));
                        break;
                    case 4:
                        product.setPrice(Double.parseDouble(line));
                        break;
                }
                if (count == 4) {
                    list.insertToTail(product); // thêm đối tượng vào cuối dnah sách liên kết
                    product = new Product();    // tạo lại đối tượng
                    count = 0;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        displayAll(list);                       // hiển thị danh sách các sản phẩm đọc từ file
    }

    /**------------------------------------------------------------------------
     * Hàm tìm kiếm vị trí sản phẩm trong danh sách
     * @param list danh sách chứa sản phẩm cần tìm.
     * @return vị trí sản phẩm muốn tìm
     */
    public int index(MyList<Product> list){
        String code = input.next();             // Nhập ID cần tìm từ bàn phím
        int count = 0;                          // Biến đếm chỉ số
        Node<Product> current = list.getHead();
        // Duyệt tìm kiếm
        while (current != null){
            count++;
            if(current.getInfo().getbCode().equals(code)){
                return count;                   // trả về chỉ số vị trí sản phẩm tìm được
            }
            current =current.getNext();
        }
        return -1;                              // trả về -1 nếu không tìm thấy sản phẩm
    }

    /**------------------------------------------------------------------------
     * Hàm nhập thông tin sản phẩm mới
     * @return trả về sản phẩm với thông tin vừa nhập.
     */
    public Product createProduct(){
        Product product = new Product();
        System.out.print("Input new ID: ");
        input.nextLine();
        product.setbCode(input.nextLine());
        System.out.print("Input Product's Name: ");
        product.setTittle(input.nextLine());
        System.out.print("Input Product's quantity: ");
        product.setQuantity(input.nextInt());
        System.out.print("Input Product's price: ");
        product.setPrice(input.nextDouble());

        return product;
    }

    /**------------------------------------------------------------------------
     * hàm thêm sản phẩm mới vào cuối danh sách.
     * @param list danh sách thêm sản phẩm.
     */
    public void addLast(MyList<Product> list){
        System.out.print("How many new Product? ");
        int num = input.nextInt();
        for(int i = 1; i <= num; i++) {
            System.out.println("\nProduct: "+i);

            Product item = createProduct(); // gọi hàm tạo sản phẩm mới
            list.insertToTail(item);        // thêm sản phẩm mới tạo vào cuối danh sách
        }
    }

    /**------------------------------------------------------------------------
     * Hàm hiển thị toàn bộ sản phẩm trong danh sách.
     * @param list danh sách muốn hiển thị.
     */
    public void displayAll(MyList<Product> list){
        // hiển thị sản phẩm ra màn hình.
        System.out.println(list.toString());
        // ghi kết quả vào file
        writeResult("console_output.txt",list.toString());
    }

    /**------------------------------------------------------------------------
     * Hàm cập nhật danh sách sản phẩm
     * @param fileName tên file lưu sản phẩm.
     * @param list danh sách sản phẩm cần cập nhật.
     */
    public void writeAllItemToFile(String fileName, MyList<Product> list){
        File file = new File(fileName);
        Node<Product> current = list.getHead();
        try {
            OutputStream outputStream = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(outputStream);
            // cập nhật lại sản phẩm theo linked list
            while (current != null) {
                osw.write(current.getInfo().getbCode() + "\n");
                osw.write(current.getInfo().getTittle() + "\n");
                osw.write(current.getInfo().getQuantity() + "\n");
                osw.write(current.getInfo().getPrice() + "\n");
                current = current.getNext();
            }
            // kết thúc ghi cập nhật.
            osw.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**------------------------------------------------------------------------
     * Hàm tìm kiếm sản phẩm theo ID.
     * @param list danh sách chứa sản phẩm muốn tìm.
     */
    public void searchByCode(MyList<Product> list){
        System.out.print("Input the ID to search ");
        index = index(list);                // gọi hàm tìm kiếm và trả về chỉ số vị trí của sản phẩm.

        Node<Product> current = list.getHead();
        String result = "";
        int count = 0;
        if(index > 0){
            // duyệt sanh sách tìm kiếm sản phẩm
            while (current != null){
                count++;
                // hiển thị sản phẩm tìm thấy
                if(count == index) {
                    System.out.printf("%s %20s %20s %20s", " ID", "TITTLE ", " QUANTITY", " PRICE");
                    System.out.println();
                    System.out.println(current.getInfo());
                    result = current.getInfo().toString();
                }
                current = current.getNext();
            }
        // in ra -1 khi không tìm thấy sản phẩm
        }else {
            System.out.println(index);
            result += index;
        }
        // ghi kết quả ra file out_put
        writeResult("console_output.txt", result);
    }

    /**------------------------------------------------------------------------
     * Hàm xoá sản phẩm theo id nhập vào từ bàn phím.
     * @param list danh sách sản phẩm.
     */
    public void deleteByCode(MyList<Product> list){
        System.out.print("Input the bar code to delete ");

        index = index(list);                        // Gọi hàm tìm kiếm vị trí sản phẩm trong danh sách

        Node<Product> current = list.getHead();     // tạo Node hiện tại
        Node<Product> previous = null;              // tạo Node đứng trước

        // trường hợp chỉ số tìm thấy là phần tử đầu tiên
        if(index == 1 && current != null){
            list.setHead(current.getNext());        // chuyển head thành Node tiếp theo
            current = null;                         // xoá phần tử đầu tiên
            return;
        }
        // Duyệt tìm kiếm và so sánh chỉ số
        int count = 0;
        while (count <= index && index > 1) {
            count++;
            if (count == index){
                previous.setNext(current.getNext()); // xoá Node tại vị trí tìm thấy
            }else{
                previous = current;                  // dịch chuyển node phía trước lên node hiện tại
            }
            current = current.getNext();             // dịch chuyển node hiện tại lên node tiếp theo
        }
        writeResult("console_output.txt", "Deleted!");
    }


    /**------------------------------------------------------------------------
     * Hàm sắp xếp sản phẩm theo ID.
     * @param list danh sách sản phẩm cần sắp xếp.
     */
    public void sortByCode(MyList<Product> list){
        Node<Product> cur = list.getHead();
        Product temp;
        // duyệt danh sách
        while (cur.getNext() != null){
            // so sánh ID 2 sản phẩm cạnh nhau
            if(cur.getInfo().getbCode().compareTo(cur.getNext().getInfo().getbCode())>0){
                // thực hiện đổi chỗ sản phẩm
                temp = cur.getInfo();
                cur.setInfo(cur.getNext().getInfo());
                cur.getNext().setInfo(temp);

                sortByCode(list);       // sử dụng đệ quy gọi lại hàm sắp xếp
            }
            cur = cur.getNext();
        }
    }

    /*------------------------------------------------------------------------
     * length: biến lưu độ dài của mảng trả về.
     * arr   : mảng lưu phần dư sau mỗi lần thực hiện phép chia.
     * array : mảng lưu giá trị trả về.
     */
    private int length = 0;
    private int[] arr = new int[10];
    private int[] array;
    /**
     * hàm chuyển số lượng sản sang hệ số nhị phân sử dụng đệ quy.
     * @param value giá trị cần chuyển hệ đếm.
     */
    public int[] convertToBinary(int value) {
        length++;
        // điều kiện kết thúc đệ quy
        if(value/2 == 0){
            arr[length - 1] = value % 2;    // lưu giá trị vào mảng arr
            array = new int[length];        // khởi tạo mảng array với kích thước = length
            for(int j = 0; j < length; j++){
                array[j] = arr[j];          // copy phần tử từ mảng arr
            }
        }else{
            arr[length-1] = value % 2;      // lưu giá trị vào mảng arr
            convertToBinary(value/2); // Gọi hàm đệ quy
        }
        return array;
    }

    /**------------------------------------------------------------------------
     * Hàm đọc dữ liệu từ file và lưu vào stack.
     * @param fileName file chứa dữ liệu cần đọc.
     * @param stack nơi lưu sản phẩm đọc từ file.
     */
    public void getAllItemsFromFile(String fileName, MyStack<Product> stack) {
        // khởi tạo đối tượng file
        File file = new File(fileName);
        try {
            // tạo một dòng chuẩn bị cho việc đọc file
            InputStream inputStream = new FileInputStream(file);
            // tạo một đối tượng đọc file với dòng trên
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            // tạo bộ đệm
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line;
            Product product = new Product();    // tạo đối tượng lưu thông tin sản phẩm
            int count = 0;
            // lặp đọc file
            while ((line = reader.readLine()) != null) {
                count++;
                //lưu thông tin từng dòng vào đối tượng tương ứng
                switch (count) {
                    case 1:
                        product.setbCode(line);
                        break;
                    case 2:
                        product.setTittle(line);
                        break;
                    case 3:
                        product.setQuantity(Integer.parseInt(line));
                        break;
                    case 4:
                        product.setPrice(Double.parseDouble(line));
                        break;
                }
                if (count == 4) {
                    Node<Product> newNode = new Node<>();
                    newNode.setInfo(product);
                    stack.push(newNode);        // thêm đối tượng vào cuối dnah sách liên kết
                    product = new Product();    // tạo lại đối tượng
                    count = 0;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        // ghi kết quả vào file out_put
        writeResult("console_output.txt", stack.toString());
    }

    /**------------------------------------------------------------------------
     * Đọc dữ liệu từ file và lưu vào Queue.
     * @param fileName file chứa dữ liệu cần đọc
     * @param queue nơi chứa sản phẩm đọc từ file.
     */
    public void getAllItemsFromFile(String fileName, MyQueue<Product> queue) {
        // khởi tạo đối tượng file
        File file = new File(fileName);
        try {
            // tạo một dòng chuẩn bị cho việc đọc file
            InputStream inputStream = new FileInputStream(file);
            // tạo một đối tượng đọc file với dòng trên
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            // tạo bộ đệm
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line;
            Product product = new Product();    // tạo đối tượng lưu thông tin sản phẩm
            int count = 0;
            // lặp đọc file
            while ((line = reader.readLine()) != null) {
                count++;
                //lưu thông tin từng dòng vào đối tượng tương ứng
                switch (count) {
                    case 1:
                        product.setbCode(line);
                        break;
                    case 2:
                        product.setTittle(line);
                        break;
                    case 3:
                        product.setQuantity(Integer.parseInt(line));
                        break;
                    case 4:
                        product.setPrice(Double.parseDouble(line));
                        break;
                }
                if (count == 4) {
                    Node<Product> newNode = new Node<>();
                    newNode.setInfo(product);   // thêm đối tượng vào node mới.
                    queue.enqueue(newNode);     // thêm node mới vào Queue.
                    product = new Product();    // tạo lại đối tượng.
                    count = 0;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        // ghi kết quả vào file out_put
        queue.dequeue();
        writeResult("console_output.txt", queue.toString());

    }

    /**------------------------------------------------------------------------
     * hàm ghi kết quả out_put vào file
     */
    public void writeResult(String fileName, String result){
        File file = new File(fileName);
        try {
            // tạo dòng chuẩn bị cho việc ghi file. tham số true: chấp nhận việc ghi nối dữ liệu.
            OutputStream outputStream = new FileOutputStream(file, true);
            OutputStreamWriter osw = new OutputStreamWriter(outputStream);
            osw.write(result);
            osw.write("\n\n");
            osw.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
