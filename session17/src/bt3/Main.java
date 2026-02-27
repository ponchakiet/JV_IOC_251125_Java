package bt3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BookManager manager = new BookManager();

        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Thêm sách");
            System.out.println("2. Cập nhật sách");
            System.out.println("3. Xóa sách");
            System.out.println("4. Tìm theo tác giả");
            System.out.println("5. Hiển thị tất cả");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Vui lòng nhập số!");
                continue;
            }

            switch (choice) {
                case 1:
                    try {
                        System.out.print("Tiêu đề: ");
                        String title = sc.nextLine();
                        System.out.print("Tác giả: ");
                        String author = sc.nextLine();
                        System.out.print("Năm XB: ");
                        int year = Integer.parseInt(sc.nextLine());
                        System.out.print("Giá: ");
                        double price = Double.parseDouble(sc.nextLine());

                        manager.addBook(new Book(title, author, year, price));
                    } catch (Exception e) {
                        System.out.println("Dữ liệu không hợp lệ!");
                    }
                    break;

                case 2:
                    System.out.print("ID cần cập nhật: ");
                    int idUpdate = Integer.parseInt(sc.nextLine());
                    System.out.print("Tiêu đề mới: ");
                    String title = sc.nextLine();
                    System.out.print("Tác giả mới: ");
                    String author = sc.nextLine();
                    System.out.print("Năm mới: ");
                    int year = Integer.parseInt(sc.nextLine());
                    System.out.print("Giá mới: ");
                    double price = Double.parseDouble(sc.nextLine());

                    manager.updateBook(idUpdate, new Book(title, author, year, price));
                    break;

                case 3:
                    System.out.print("ID cần xóa: ");
                    int idDelete = Integer.parseInt(sc.nextLine());
                    manager.deleteBook(idDelete);
                    break;

                case 4:
                    System.out.print("Nhập tên tác giả: ");
                    String authorSearch = sc.nextLine();
                    manager.findBooksByAuthor(authorSearch);
                    break;

                case 5:
                    manager.listAllBooks();
                    break;

                case 0:
                    System.out.println("Thoát chương trình.");
                    return;

                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
}