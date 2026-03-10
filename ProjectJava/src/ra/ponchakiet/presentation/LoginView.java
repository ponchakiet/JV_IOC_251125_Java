package ra.ponchakiet.presentation;

import ra.ponchakiet.model.Admin;
import ra.ponchakiet.model.Student;
import ra.ponchakiet.service.IAdminService;
import ra.ponchakiet.service.IStudentService;
import ra.ponchakiet.service.impl.AdminServiceImpl;
import ra.ponchakiet.service.impl.StudentServiceImpl;
import ra.ponchakiet.utils.Colors;
import ra.ponchakiet.utils.InputMethods;

import java.awt.*;
import java.util.Scanner;

public class LoginView {
    public static Student studentLogin = null;
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
            int choice = InputMethods.getInteger();

            switch (choice) {
                case 1:
                    loginAdmin(sc);
                    break;
                case 2:
                    loginStudent(sc);
                    break;
                case 3:
                    System.out.println(Colors.GREEN + "Thoát chương trình" + Colors.RESET);
                    System.exit(0);
                default:
                    System.out.println(Colors.RED + "Lựa chọn không đúng!" + Colors.RESET);
            }
        }
    }

    private static void loginAdmin(Scanner sc) {
        System.out.print("Nhập username: ");
        String username = InputMethods.getString();
        System.out.print("Nhập password: ");
        String password = InputMethods.getString();

        Admin admin = adminService.login(username, password);
        if (admin != null) {
            System.out.println(Colors.GREEN + "Đăng nhập thành công" + Colors.RESET);
            AdminView.showAdminMenu(sc);
        } else {
            System.out.println(Colors.RED + "Sai thông tin tài khoản hoặc mật khẩu. Vui lòng đăng nhập lại!" + Colors.RESET);
            loginAdmin(sc);
        }
    }

    private static void loginStudent(Scanner sc) {
        System.out.print("Nhập email: ");
        String email = InputMethods.getString();
        System.out.print("Nhập password: ");
        String password = InputMethods.getString();

        Student student = studentService.login(email, password);
        if (student != null) {
            System.out.println(Colors.GREEN + "Đăng nhập thành công" + Colors.RESET);
            studentLogin = student;
            StudentView.showStudentMenu(sc);
        } else {
            System.out.println(Colors.RED + "Sai thông tin tài khoản hoặc mật khẩu. Vui lòng đăng nhập lại!" + Colors.RESET);
            loginStudent(sc);
        }
    }
}