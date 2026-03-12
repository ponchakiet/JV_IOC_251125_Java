package ra.ponchakiet.presentation;

import ra.ponchakiet.model.StudentCourse;
import ra.ponchakiet.service.ICourseService;
import ra.ponchakiet.service.IEnrollmentService;
import ra.ponchakiet.service.IStudentService;
import ra.ponchakiet.service.impl.CourseServiceImpl;
import ra.ponchakiet.service.impl.EnrollmentServiceImpl;
import ra.ponchakiet.service.impl.StudentServiceImpl;
import ra.ponchakiet.utils.Colors;
import ra.ponchakiet.utils.InputMethods;

import java.util.List;

public class StatisticsView {
    private static final ICourseService courseService = new CourseServiceImpl();
    private static final IStudentService studentService = new StudentServiceImpl();
    private static final IEnrollmentService enrollmentService = new EnrollmentServiceImpl();
    public static void showStatisticsMenu() {
        while (true) {
            System.out.println("\n1. Thống kê tổng số lượng khóa học và học viên");
            System.out.println("2. Thống kê học viên theo từng khóa học");
            System.out.println("3. Top 5 khóa học đông học viên nhất");
            System.out.println("4. Liệt kê khóa học có trên 10 học viên");
            System.out.println("5. Quay về menu chính");

            System.out.print("Nhập lựa chọn: ");
            int choice = InputMethods.getInteger();

            switch (choice) {
                case 1:
                    statisticsCoursesAndStudents();
                    break;
                case 2:
                    statisticsStudentsByCourse();
                    break;
                case 3:
                    display5CoursesByStudent();
                    break;
                case 4:
                    displayCoursesByStudentOver10();
                    break;
                case 5:
                    AdminView.showAdminMenu();
                    break;
                default:
                    System.out.println(Colors.RED + "Lựa chọn không đúng!" + Colors.RESET);
            }
        }
    }

    private static void statisticsCoursesAndStudents() {
        System.out.printf("-".repeat(32));
        System.out.printf("\n| Tổng số lượng khóa học: %-4d |\n", courseService.findAll().size());
        System.out.printf("-".repeat(32));
        System.out.printf("\n| Tổng số lượng học viên: %-4d |\n", studentService.findAll().size());
        System.out.printf("-".repeat(32));
        System.out.println();
    }

    private static void statisticsStudentsByCourse() {
        List<StudentCourse> list = enrollmentService.statisticsStudentsByCourse();
        if (list.isEmpty()) {
            System.out.println(Colors.RED + "Danh sách trống" + Colors.RESET);
        } else {
            System.out.printf("-".repeat(75));
            for (StudentCourse studentCourse : list) {
                System.out.printf("\n| KHÓA HỌC: %-35s | SỐ LƯỢNG HỌC VIÊN: %-4s |\n", studentCourse.getCourseName().toUpperCase(), studentCourse.getStudentName());
                System.out.printf("-".repeat(75));
            }
            System.out.println();
        }
    }

    private static void display5CoursesByStudent() {
        List<StudentCourse> list = enrollmentService.get5CoursesByStudent();
        if (list.isEmpty()) {
            System.out.println(Colors.RED + "Danh sách trống" + Colors.RESET);
        } else {
            System.out.printf("-".repeat(75));
            for (StudentCourse studentCourse : list) {
                System.out.printf("\n| KHÓA HỌC: %-35s | SỐ LƯỢNG HỌC VIÊN: %-4s |\n", studentCourse.getCourseName().toUpperCase(), studentCourse.getStudentName());
                System.out.printf("-".repeat(75));
            }
            System.out.println();
        }
    }

    private static void displayCoursesByStudentOver10() {
        List<StudentCourse> list = enrollmentService.getCoursesByStudentOver10();
        if (list.isEmpty()) {
            System.out.println(Colors.RED + "Danh sách trống" + Colors.RESET);
        } else {
            System.out.printf("-".repeat(75));
            for (StudentCourse studentCourse : list) {
                System.out.printf("\n| KHÓA HỌC: %-35s | SỐ LƯỢNG HỌC VIÊN: %-4s |\n", studentCourse.getCourseName().toUpperCase(), studentCourse.getStudentName());
                System.out.printf("-".repeat(75));
            }
            System.out.println();
        }
    }
}
