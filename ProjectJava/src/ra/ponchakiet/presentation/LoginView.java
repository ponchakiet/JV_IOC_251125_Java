package ra.ponchakiet.presentation;

import ra.ponchakiet.model.Admin;
import ra.ponchakiet.model.Student;
import ra.ponchakiet.service.IAdminService;
import ra.ponchakiet.service.IStudentService;
import ra.ponchakiet.service.impl.AdminServiceImpl;
import ra.ponchakiet.service.impl.StudentServiceImpl;
import ra.ponchakiet.utils.Colors;
import ra.ponchakiet.utils.InputMethods;
import ra.ponchakiet.utils.Validate;

import java.awt.*;

public class LoginView {
    public static Student studentLogin = null;
    private static final IAdminService adminService = new AdminServiceImpl();
    private static final IStudentService studentService = new StudentServiceImpl();

    public static void showMenuLogin() {
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
                    loginAdmin();
                    break;
                case 2:
                    loginStudent();
                    break;
                case 3:
                    System.out.println(Colors.GREEN + "Thoát chương trình" + Colors.RESET);
                    System.exit(0);
                default:
                    System.out.println(Colors.RED + "Lựa chọn không đúng!" + Colors.RESET);
            }
        }
    }

    private static void loginAdmin() {
        System.out.print("Nhập username: ");
        String username = InputMethods.getString();
        System.out.print("Nhập password: ");
        String password = InputMethods.getString();

        Admin admin = adminService.login(username, password);
        if (admin != null) {
            System.out.println(Colors.GREEN + "Đăng nhập thành công" + Colors.RESET);
            AdminView.showAdminMenu();
        } else {
            System.out.println(Colors.RED + "Sai thông tin tài khoản hoặc mật khẩu. Vui lòng đăng nhập lại!" + Colors.RESET);
            loginAdmin();
        }
    }

    private static void loginStudent() {
        String email;
        while (true) {
            System.out.print("Nhập email : ");
            String emailInput = InputMethods.getString();
            if (Validate.isValidEmail(emailInput)) {
                email = emailInput;
                break;
            }
            System.out.println(Colors.RED + "Lỗi: Email không đúng định dạng (VD: example@gmail.com)!" + Colors.RESET);
        }
        System.out.print("Nhập password: ");
        String password = InputMethods.getString();

        Student student = studentService.login(email, password);
        if (student != null) {
            System.out.println(Colors.GREEN + "Đăng nhập thành công" + Colors.RESET);
            studentLogin = student;
            StudentView.showStudentMenu();
        } else {
            System.out.println(Colors.RED + "Sai thông tin tài khoản hoặc mật khẩu. Vui lòng đăng nhập lại!" + Colors.RESET);
            loginStudent();
        }
    }
}