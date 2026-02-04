package bt2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AttendanceManager manager = new AttendanceManager();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- MENU ĐIỂM DANH ---");
            System.out.println("1. Thêm sinh viên");
            System.out.println("2. Sửa sinh viên");
            System.out.println("3. Xóa sinh viên");
            System.out.println("4. Hiển thị danh sách");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> {
                    System.out.print("Nhập tên sinh viên: ");
                    String name = sc.nextLine();
                    manager.add(new Student(name));
                }
                case 2 -> {
                    manager.display();
                    System.out.print("Nhập vị trí cần sửa: ");
                    int index = Integer.parseInt(sc.nextLine());
                    System.out.print("Nhập tên mới: ");
                    String name = sc.nextLine();
                    manager.update(index, new Student(name));
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
