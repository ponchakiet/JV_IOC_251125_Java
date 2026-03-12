package ra.ponchakiet.presentation;

import ra.ponchakiet.model.Enrollment;
import ra.ponchakiet.model.EnrollmentDetail;
import ra.ponchakiet.model.StudentCourse;
import ra.ponchakiet.service.IEnrollmentService;
import ra.ponchakiet.service.impl.EnrollmentServiceImpl;
import ra.ponchakiet.utils.Colors;
import ra.ponchakiet.utils.InputMethods;

import java.time.LocalDateTime;
import java.util.List;

public class EnrollmentView {
    private static final IEnrollmentService enrollmentService = new EnrollmentServiceImpl();
    public static void showEnrollmentMenu() {
        while (true) {
            System.out.println("\n1. Hiển thị học viên theo từng khóa học");
            System.out.println("2. Duyệt học viên vào khóa học");
            System.out.println("3. Xóa học viên khỏi khóa học");
            System.out.println("4. Quay về menu chính");

            System.out.print("Nhập lựa chọn: ");
            int choice = InputMethods.getInteger();

            switch (choice) {
                case 1:
                    showStudentsByCourse();
                    break;
                case 2:
                    updateEnrollment();
                    break;
                case 3:
                    deleteEnrollment();
                    break;
                case 4:
                    AdminView.showAdminMenu();
                    break;
                default:
                    System.out.println(Colors.RED + "Lựa chọn không đúng!" + Colors.RESET);
            }
        }
    }

    private static void showStudentsByCourse() {
        int currentPage = 1;
        while (true) {
            List<StudentCourse> list = enrollmentService.displayStudentCourse(currentPage);
            if (list.isEmpty()) {
                if (currentPage == 1) {
                    System.out.println(Colors.RED + "Danh sách trống" + Colors.RESET);
                    break;
                } else {
                    System.out.println("Bạn đang ở trang cuối cùng!");
                    currentPage--;
                }
            } else {
                String courseName = list.get(0).getCourseName();
                System.out.printf("-".repeat(49));
                System.out.printf("\n| KHÓA HỌC: %-35s |\n", courseName.toUpperCase());
                System.out.printf("-".repeat(49));
                System.out.println();
                for (StudentCourse studentCourse : list) {
                    studentCourse.display();
                }
                System.out.printf("-".repeat(49));
                System.out.println();
                System.out.println("--- TRANG " + currentPage + " ---");
            }

            System.out.println("\n[N]: Khóa tiếp | [B]: Khóa trước | [E]: Thoát");
            System.out.print("Nhập lựa chọn: ");
            String choice = InputMethods.getString().toUpperCase();

            if (choice.equalsIgnoreCase("N")) {
                currentPage++;
            } else if (choice.equalsIgnoreCase("B")) {
                if (currentPage > 1) currentPage--;
                else System.out.println("Bạn đang ở trang đầu tiên!");
            } else if (choice.equalsIgnoreCase("E")) {
                break;
            }
        }
    }

    private static void updateEnrollment() {
        List<EnrollmentDetail> list = enrollmentService.getEnrollmentDetailsWaiting();
        if (list.isEmpty()) {
            System.out.println(Colors.RED + "Danh sách trống" + Colors.RESET);
        } else {
            System.out.printf("\n%sDANH SÁCH HỌC VIÊN CHỜ DUYỆT%s\n", "-".repeat(67), "-".repeat(67));

            for (EnrollmentDetail ed : list) {
                ed.displayData();
                System.out.printf("%s\n", "-".repeat(163));
            }
        }
        System.out.print("Chọn ID bạn muốn duyệt vào khóa học: ");
        int id = InputMethods.getInteger();
        Enrollment enrollment = enrollmentService.findEnrollmentByIdWaiting(id);
        if (enrollment == null) {
            System.out.println(Colors.RED + "Không tìm thấy ID này!" + Colors.RESET);
        } else {
            while (true) {
                System.out.println("1. Thêm học viên vào khóa học");
                System.out.println("2. Từ chối học viên vào khóa học");
                System.out.println("3. Quay về");
                System.out.println("Nhập lựa chọn: ");
                int choice = InputMethods.getInteger();
                switch (choice) {
                    case 1:
                        enrollment.setRegisteredAt(LocalDateTime.now());
                        enrollmentService.updateStatus(enrollment, "CONFIRM");
                        System.out.println(Colors.GREEN + "Thêm học viên vào khóa học thành công!" + Colors.RESET);
                        showEnrollmentMenu();
                        break;
                    case 2:
                        enrollment.setRegisteredAt(LocalDateTime.now());
                        enrollmentService.updateStatus(enrollment, "DENIED");
                        System.out.println(Colors.GREEN + "Từ chối học viên vào khóa học thành công!" + Colors.RESET);
                        showEnrollmentMenu();
                        break;
                    case 3:
                        showEnrollmentMenu();
                        break;
                    default:
                        System.out.println(Colors.RED + "Lựa chọn không đúng!" + Colors.RESET);
                }
            }
        }
    }

    private static void deleteEnrollment() {
        List<EnrollmentDetail> list = enrollmentService.getEnrollmentDetailsConfirm();
        if (list.isEmpty()) {
            System.out.println(Colors.RED + "Danh sách trống" + Colors.RESET);
        } else {
            System.out.printf("\n%sDANH SÁCH HỌC VIÊN ĐANG HỌC%s\n", "-".repeat(68), "-".repeat(68));

            for (EnrollmentDetail ed : list) {
                ed.displayData();
                System.out.printf("%s\n", "-".repeat(163));
            }
        }
        System.out.print("Chọn ID bạn muốn xóa khỏi khóa học: ");
        int id = InputMethods.getInteger();
        Enrollment enrollment = enrollmentService.findEnrollmentByIdConfirm(id);
        if (enrollment == null) {
            System.out.println(Colors.RED + "Không tìm thấy ID này!" + Colors.RESET);
        } else {

            enrollmentService.updateStatus(enrollment, "DELETED");
            System.out.println(Colors.GREEN + "Xóa học viên khỏi khóa học thành công!" + Colors.RESET);
        }
    }
}
