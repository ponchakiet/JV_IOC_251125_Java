package bt4;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        OrderManager manager = new OrderManager();

        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Thêm sản phẩm");
            System.out.println("2. Cập nhật khách hàng");
            System.out.println("3. Tạo đơn hàng");
            System.out.println("4. Hiển thị tất cả đơn hàng");
            System.out.println("5. Tìm đơn theo khách hàng");
            System.out.println("0. Thoát");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Tên sản phẩm: ");
                    String name = sc.nextLine();
                    System.out.print("Giá: ");
                    double price = Double.parseDouble(sc.nextLine());
                    manager.addProduct(new Product(name, price));
                    break;

                case 2:
                    System.out.print("ID khách hàng: ");
                    int id = Integer.parseInt(sc.nextLine());
                    System.out.print("Tên mới: ");
                    String cname = sc.nextLine();
                    System.out.print("Email mới: ");
                    String email = sc.nextLine();
                    manager.updateCustomer(id, new Customer(cname, email));
                    break;

                case 3:
                    manager.createOrder();
                    break;

                case 4:
                    manager.listAllOrders();
                    break;

                case 5:
                    System.out.print("ID khách hàng: ");
                    int cid = Integer.parseInt(sc.nextLine());
                    manager.getOrdersByCustomer(cid);
                    break;

                case 0:
                    return;
            }
        }
    }
}