package ra.ponchakiet.presentation;

import ra.ponchakiet.model.Admin;
import ra.ponchakiet.model.Student;
import ra.ponchakiet.service.IAdminService;
import ra.ponchakiet.service.IStudentService;
import ra.ponchakiet.service.impl.AdminServiceImpl;
import ra.ponchakiet.service.impl.StudentServiceImpl;
import ra.ponchakiet.utils.Colors;

import java.util.Scanner;

public class LoginView {
    private static final Colors color = new Colors();
    private static final IAdminService adminService = new AdminServiceImpl();
    private static final IStudentService studentService = new StudentServiceImpl();

    public static void showMenuLogin(Scanner sc) {
        while (true) {
            System.out.println("\n========= HỆ THỐNG QUẢN LÝ ĐÀO TẠO =========");
            System.out.println("1. Đăng nhập với tư cách Quản trị viên");
            System.out.println("2. Đăng nhập với tư cách Học Viên");
            System.out.println("3. Thoát");
            System.out.println("==============================================");
            System.out.print("Nhập lựa chọn: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    loginAdmin(sc);
                    break;
                case 2:
                    loginStudent(sc);
                    break;
                case 3:
                    System.out.println(color.GREEN + "Thoát chương trình" + color.RESET);
                    System.exit(0);
                default:
                    System.out.println(color.RED + "Lựa chọn không đúng!" + color.RESET);
            }
        }
    }

    private static void loginAdmin(Scanner sc) {
        System.out.print("Nhập username: ");
        String username = sc.nextLine();
        System.out.print("Nhập password: ");
        String password = sc.nextLine();

        Admin admin = adminService.login(username, password);
        if (admin != null) {
            System.out.println("Đăng nhập thành công");
            AdminView.showAdminMenu(sc);
        } else {
            System.out.println(color.RED + "Sai thông tin tài khoản hoặc mật khẩu. Vui lòng đăng nhập lại!" + color.RESET);
            loginAdmin(sc);
        }
    }

    private static void loginStudent(Scanner sc) {
        System.out.print("Nhập email: ");
        String email = sc.nextLine();
        System.out.print("Nhập password: ");
        String password = sc.nextLine();

        Student student = studentService.login(email, password);
        if (student != null) {
            System.out.println("Đăng nhập thành công");
            StudentView.showStudentMenu(sc);
        } else {
            System.out.println(color.RED + "Sai thông tin tài khoản hoặc mật khẩu. Vui lòng đăng nhập lại!" + color.RESET);
            loginStudent(sc);
        }
    }
}