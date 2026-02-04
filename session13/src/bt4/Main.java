package bt4;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        OrderManager manager = new OrderManager();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- MENU QUẢN LÝ ĐƠN HÀNG ---");
            System.out.println("1. Thêm đơn hàng");
            System.out.println("2. Sửa đơn hàng");
            System.out.println("3. Xóa đơn hàng");
            System.out.println("4. Hiển thị danh sách");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> {
                    System.out.print("Nhập mã đơn hàng: ");
                    String id = sc.nextLine();
                    System.out.print("Nhập tên khách hàng: ");
                    String name = sc.nextLine();
                    manager.add(new Order(id, name));
                }
                case 2 -> {
                    manager.display();
                    System.out.print("Nhập vị trí cần sửa: ");
                    int index = Integer.parseInt(sc.nextLine());
                    System.out.print("Nhập mã đơn hàng mới: ");
                    String id = sc.nextLine();
                    System.out.print("Nhập tên khách hàng mới: ");
                    String name = sc.nextLine();
                    manager.update(index, new Order(id, name));
                }
                case 3 -> {
                    manager.display();
                    System.out.print("Nhập vị trí cần xóa: ");
                    int index = Integer.parseInt(sc.nextLine());
                    manager.delete(index);
                }
                case 4 -> manager.display();
                case 0 -> System.out.println("Thoát chương trình");
                default -> System.out.println("Chọn sai!");
            }
        } while (choice != 0);
    }
}
