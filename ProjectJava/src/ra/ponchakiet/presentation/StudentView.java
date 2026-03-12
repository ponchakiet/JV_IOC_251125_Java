package ra.ponchakiet.presentation;

import org.mindrot.jbcrypt.BCrypt;
import ra.ponchakiet.model.CoursesEnrollment;
import ra.ponchakiet.model.Enrollment;
import ra.ponchakiet.model.EnrollmentStatus;
import ra.ponchakiet.model.Student;
import ra.ponchakiet.service.ICourseService;
import ra.ponchakiet.service.IEnrollmentService;
import ra.ponchakiet.service.IStudentService;
import ra.ponchakiet.service.impl.CourseServiceImpl;
import ra.ponchakiet.service.impl.EnrollmentServiceImpl;
import ra.ponchakiet.service.impl.StudentServiceImpl;
import ra.ponchakiet.utils.Colors;
import ra.ponchakiet.utils.InputMethods;

import java.util.List;

public class StudentView {
    private static final IEnrollmentService enrollmentService = new EnrollmentServiceImpl();
    private static final IStudentService studentService = new StudentServiceImpl();
    private static final ICourseService courseService = new CourseServiceImpl();
    public static void showStudentMenu() {
        while (true) {
            System.out.println("\n========= MENU HỌC VIÊN =========");
            System.out.println("1. Xem danh sách khóa học");
            System.out.println("2. Đăng ký khóa học");
            System.out.println("3. Xem khóc học đã đăng ký");
            System.out.println("4. Hủy đăng ký (nếu chưa bắt đầu)");
            System.out.println("5. Đổi mật khẩu");
            System.out.println("6. Đăng xuất");
            System.out.println("===================================");

            System.out.print("Nhập lựa chọn: ");
            int choice = InputMethods.getInteger();

            switch (choice) {
                case 1:
                    showCourseMenu();
                    break;
                case 2:
                    registerCourse();
                    break;
                case 3:
                    coursesRegisted();
                    break;
                case 4:
                    cancelCourse();
                    break;
                case 5:
                    changePassword();
                    break;
                case 6:
                    System.out.println(Colors.GREEN + "Đã đăng xuất" + Colors.RESET);
                    LoginView.studentLogin = null;
                    LoginView.showMenuLogin();
                default:
                    System.out.println(Colors.RED + "Lựa chọn không đúng!" + Colors.RESET);
            }
        }
    }

    private static void showCourseMenu() {
        while (true) {
            System.out.println("\n1. Xem danh sách khóa học đang có");
            System.out.println("2. Tìm kiếm khóa học theo tên");
            System.out.println("3. Quay về menu chính");

            System.out.print("Nhập lựa chọn: ");
            int choice = InputMethods.getInteger();

            switch (choice) {
                case 1:
                    AdminView.showListCourse();
                    break;
                case 2:
                    AdminView.findCourseByName();
                    break;
                case 3:
                    showStudentMenu();
                    break;
                default:
                    System.out.println(Colors.RED + "Lựa chọn không đúng!" + Colors.RESET);
            }
        }
    }

    private static void registerCourse() {
        AdminView.showListCourse();
        Enrollment enrollment = new Enrollment();
        boolean isValid = false;
        while (!isValid) {
            System.out.println("\nNhập ID khóa học bạn muốn đăng ký: ");
            int courseId = InputMethods.getInteger();
            if (courseId == 0) showStudentMenu();
            enrollment.setCourseId(courseId);
            enrollment.setStudentId(LoginView.studentLogin.getId());
            if (!courseService.isIdExist(courseId)) {
                System.out.println(Colors.RED + "ID khóa học không tồn tại!" + Colors.RESET);
            } else if (enrollmentService.isExistEnrollment(enrollment.getStudentId(), courseId)) {
                System.out.println(Colors.RED + "Bạn không được phép đăng ký khóa học này!" + Colors.RESET);
            } else {
                isValid = true;
            }
        }
        enrollmentService.registerCourse(enrollment);
        System.out.println(Colors.GREEN + "Đăng ký khóa học thành công! Vui lòng chờ Admin duyệt." + Colors.RESET);
    }

    private static void coursesRegisted() {
        while (true) {
            System.out.println("\n1. Xem khóa học đã đăng ký");
            System.out.println("2. Sắp xếp khóa học (theo tên/ngày đăng ký - tăng dần/giảm dần)");
            System.out.println("3. Quay về menu chính");

            System.out.print("Nhập lựa chọn: ");
            int choice = InputMethods.getInteger();

            switch (choice) {
                case 1:
                    showCoursesRegisted();
                    break;
                case 2:
                    sort();
                    break;
                case 3:
                    showStudentMenu();
                    break;
                default:
                    System.out.println(Colors.RED + "Lựa chọn không đúng!" + Colors.RESET);
            }
        }
    }

    private static void showCoursesRegisted() {
        List<CoursesEnrollment> list = enrollmentService.coursesEnrollment(LoginView.studentLogin.getId());
        if (list.isEmpty()) {
            System.out.println(Colors.RED + "Danh sách trống" + Colors.RESET);
        } else {
            System.out.printf("\n%sDANH SÁCH MÔN HỌC ĐÃ ĐĂNG KÝ%s\n", "-".repeat(57), "-".repeat(57));

            for (CoursesEnrollment ce : list) {
                ce.displayData();
                System.out.printf("%s\n", "-".repeat(142));
            }
        }
    }

    private static void sort() {
        System.out.println("\n-------Sắp xếp-------");
        System.out.println("1. Sắp xếp theo tên tăng dần");
        System.out.println("2. Sắp xếp theo tên giảm dần");
        System.out.println("3. Sắp xếp theo ngày đăng ký tăng dần");
        System.out.println("4. Sắp xếp theo ngày đăng ký giảm dần");
        System.out.println("5. Quay về");

        System.out.println("Nhập lựa chọn: ");
        int choice = InputMethods.getInteger();
        switch (choice) {
            case 1:
                enrollmentService.sort(1, 1);
                break;
            case 2:
                enrollmentService.sort(1, 2);
                break;
            case 3:
                enrollmentService.sort(2, 1);
                break;
            case 4:
                enrollmentService.sort(2, 2);
                break;
            case 5:
                coursesRegisted();
                break;
            default:
                System.out.println(Colors.RED + "Lựa chọn không đúng!" + Colors.RESET);
        }
    }

    private static void cancelCourse() {
        showCoursesRegisted();
        int courseId = 0;
        boolean isValid = false;
        while (!isValid) {
            System.out.println("\nNhập ID khóa học bạn muốn hủy: ");
            courseId = InputMethods.getInteger();
            if (!enrollmentService.isExistEnrollment(LoginView.studentLogin.getId(), courseId)) {
                System.out.println(Colors.RED + "Khóa học bạn chọn chưa đăng ký hoặc không có!" + Colors.RESET);
            } else {
                isValid = true;
            }
        }
        CoursesEnrollment ce = enrollmentService.findEnrollment(LoginView.studentLogin.getId(), courseId);
        if (ce.getStatus().equals("WAITING")) {
            System.out.println("Bạn có chắc chắn muốn hủy không? (Y/N): ");
            String choice = InputMethods.getString();
            if (choice.equalsIgnoreCase("Y")) {
                enrollmentService.cancel(LoginView.studentLogin.getId(), courseId);
                System.out.println(Colors.GREEN + "Hủy khóa học thành công!" + Colors.RESET);
            } else {
                System.out.println(Colors.GREEN + "Hủy thao tác hủy" + Colors.RESET);
            }
        } else {
            System.out.println(Colors.RED + "Khóa học bạn chọn không thể hủy!" + Colors.RESET);
        }
    }

    private static void changePassword() {
        String email = LoginView.studentLogin.getEmail();
        String phone = LoginView.studentLogin.getPhone();
        System.out.print("Xác nhận Email: ");
        String currentEmail = InputMethods.getString();
        System.out.print("Xác nhận SĐT: ");
        String currentPhone = InputMethods.getString();
        System.out.print("Nhập mật khẩu cũ: ");
        String oldPass = InputMethods.getString();
        if(email.equals(currentEmail) && phone.equals(currentPhone) && BCrypt.checkpw(oldPass, LoginView.studentLogin.getPassword())) {
            System.out.print("Nhập mật khẩu mới: ");
            String newPass = InputMethods.getString();
            studentService.changePassword(LoginView.studentLogin.getId(), BCrypt.hashpw(newPass,BCrypt.gensalt(12)));
            System.out.println(Colors.GREEN + "Đổi mật khẩu thành công! Vui lòng đăng nhập lại." + Colors.RESET);
            LoginView.studentLogin = null;
            LoginView.showMenuLogin();
        } else {
            System.out.println(Colors.RED + "Email hoặc SĐT hoặc Mật khẩu cũ không chính xác!" + Colors.RESET);
        }
    }
}
