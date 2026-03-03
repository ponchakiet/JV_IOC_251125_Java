package ra.ponchakiet.presentation;

import java.util.Scanner;

public class AdminView {
    public static void showAdminMenu(Scanner sc) {
        while (true) {
            System.out.println("\n========= MENU ADMIN =========");
            System.out.println("1. Quản lý khóa học");
            System.out.println("2. Quản lý học viên");
            System.out.println("3. Quản lý đăng ký học");
            System.out.println("4. Thống kê học viên theo khóa học");
            System.out.println("5. Đăng xuất");
            System.out.println("=================================");

            System.out.print("Nhập lựa chọn: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
//                    courseView.menu(); // Nhay sang lop quan ly khoa hoc
                    break;
                case 2:
//                    studentManagerView.menu(); // Nhay sang lop quan ly hoc vien
                    break;
                case 3:
//                    statisticView.menu(); // Nhay sang lop thong ke
                    break;
                case 4:
//                    changePassword();
                    break;
                case 5:
                    System.out.println("Dang dang xuat...");
                    return; // Thoat vong lap, quay ve LoginView
                default:
                    System.out.println("Lua chon khong hop le, vui long chon lai!");
            }
        }
    }
}
