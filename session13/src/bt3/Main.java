package bt3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InvoiceManager manager = new InvoiceManager();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- MENU QUẢN LÝ HÓA ĐƠN ---");
            System.out.println("1. Thêm hóa đơn");
            System.out.println("2. Sửa hóa đơn");
            System.out.println("3. Xóa hóa đơn");
            System.out.println("4. Hiển thị danh sách");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> {
                    System.out.print("Nhập mã hóa đơn: ");
                    String code = sc.nextLine();
                    System.out.print("Nhập số tiền: ");
                    double amount = Double.parseDouble(sc.nextLine());
                    manager.add(new Invoice(code, amount));
                }
                case 2 -> {
                    manager.display();
                    System.out.print("Nhập vị trí cần sửa: ");
                    int index = Integer.parseInt(sc.nextLine());
                    System.out.print("Nhập mã mới: ");
                    String code = sc.nextLine();
                    System.out.print("Nhập số tiền mới: ");
                    double amount = Double.parseDouble(sc.nextLine());
                    manager.update(index, new Invoice(code, amount));
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
