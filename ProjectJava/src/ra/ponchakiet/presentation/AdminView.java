package ra.ponchakiet.presentation;

import ra.ponchakiet.service.ICourseService;
import ra.ponchakiet.service.IEnrollmentService;
import ra.ponchakiet.service.IStudentService;
import ra.ponchakiet.service.impl.CourseServiceImpl;
import ra.ponchakiet.service.impl.EnrollmentServiceImpl;
import ra.ponchakiet.service.impl.StudentServiceImpl;
import ra.ponchakiet.utils.Colors;
import ra.ponchakiet.utils.InputMethods;

public class AdminView {
    public static void showAdminMenu() {
        while (true) {
            System.out.println("\n========= MENU ADMIN =========");
            System.out.println("1. Quản lý khóa học");
            System.out.println("2. Quản lý học viên");
            System.out.println("3. Quản lý đăng ký học");
            System.out.println("4. Thống kê học viên theo khóa học");
            System.out.println("5. Đăng xuất");
            System.out.println("=================================");

            System.out.print("Nhập lựa chọn: ");
            int choice = InputMethods.getInteger();

            switch (choice) {
                case 1:
                    CourseView.showCourseMenu();
                    break;
                case 2:
                    StudentAdminView.showStudentMenu();
                    break;
                case 3:
                    EnrollmentView.showEnrollmentMenu();
                    break;
                case 4:
                    StatisticsView.showStatisticsMenu();
                    break;
                case 5:
                    System.out.println(Colors.GREEN + "Đã đăng xuất" + Colors.RESET);
                    LoginView.showMenuLogin();
                default:
                    System.out.println(Colors.RED + "Lựa chọn không đúng!" + Colors.RESET);
            }
        }
    }
}