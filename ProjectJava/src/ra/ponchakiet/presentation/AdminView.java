package ra.ponchakiet.presentation;

import org.mindrot.jbcrypt.BCrypt;
import ra.ponchakiet.model.*;
import ra.ponchakiet.service.ICourseService;
import ra.ponchakiet.service.IEnrollmentService;
import ra.ponchakiet.service.IStudentService;
import ra.ponchakiet.service.impl.CourseServiceImpl;
import ra.ponchakiet.service.impl.EnrollmentServiceImpl;
import ra.ponchakiet.service.impl.StudentServiceImpl;
import ra.ponchakiet.utils.Colors;
import ra.ponchakiet.utils.InputMethods;
import ra.ponchakiet.utils.Validate;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AdminView {
    private static final ICourseService courseService = new CourseServiceImpl();
    private static final IStudentService studentService = new StudentServiceImpl();
    private static final IEnrollmentService enrollmentService = new EnrollmentServiceImpl();

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
                    showCourseMenu();
                    break;
                case 2:
                    showStudentMenu();
                    break;
                case 3:
                    showEnrollmentMenu();
                    break;
                case 4:
//                    changePassword();
                    break;
                case 5:
                    System.out.println(Colors.GREEN + "Đã đăng xuất" + Colors.RESET);
                    LoginView.showMenuLogin();
                default:
                    System.out.println(Colors.RED + "Lựa chọn không đúng!" + Colors.RESET);
            }
        }
    }

    private static void showCourseMenu() {
        while (true) {
            System.out.println("\n1. Hiển thị danh sách khóa học");
            System.out.println("2. Thêm mới khóa học");
            System.out.println("3. Chỉnh sửa thông tin khóa học (hiển thị menu chọn thuộc tính cần sửa)");
            System.out.println("4. Xóa khóa học (xác nhận trước khi xóa)");
            System.out.println("5. Tìm kiếm theo tên (tương đối)");
            System.out.println("6. Sắp xếp theo tên hoặc id (tăng/giảm dần)");
            System.out.println("7. Quay về menu chính");

            System.out.print("Nhập lựa chọn: ");
            int choice = InputMethods.getInteger();

            switch (choice) {
                case 1:
                    showListCourse();
                    break;
                case 2:
                    addCourse();
                    break;
                case 3:
                    updateCourse();
                    break;
                case 4:
                    deleteCourse();
                    break;
                case 5:
                    findCourseByName();
                    break;
                case 6:
                    sortCourse();
                    break;
                case 7:
                    showAdminMenu();
                    break;
                default:
                    System.out.println(Colors.RED + "Lựa chọn không đúng!" + Colors.RESET);
            }
        }
    }

    private static void showStudentMenu() {
        while (true) {
            System.out.println("\n1. Hiển thị danh sách học viên");
            System.out.println("2. Thêm mới học viên");
            System.out.println("3. Chỉnh sửa thông tin học viên (hiển thị menu chọn thuộc tính cần sửa)");
            System.out.println("4. Xóa học viên theo id (xác nhận trước khi xóa)");
            System.out.println("5. Tìm kiếm theo tên, email hoặc id (tương đối)");
            System.out.println("6. Sắp xếp theo tên hoặc id (tăng/giảm dần)");
            System.out.println("7. Quay về menu chính");

            System.out.print("Nhập lựa chọn: ");
            int choice = InputMethods.getInteger();

            switch (choice) {
                case 1:
                    showListStudent();
                    break;
                case 2:
                    addStudent();
                    break;
                case 3:
                    updateStudent();
                    break;
                case 4:
                    deleteStudent();
                    break;
                case 5:
                    findStudent();
                    break;
                case 6:
                    sortStudents();
                    break;
                case 7:
                    showAdminMenu();
                    break;
                default:
                    System.out.println(Colors.RED + "Lựa chọn không đúng!" + Colors.RESET);
            }
        }
    }

    private static void showEnrollmentMenu() {
        while (true) {
            System.out.println("\n1. Hiển thị học viên theo từng khóa học");
            System.out.println("2. Duyệt học viên vào khóa học");
            System.out.println("3. Xóa học viên khỏi khóa học");
            System.out.println("4. Quay về menu chính");

            System.out.print("Nhập lựa chọn: ");
            int choice = InputMethods.getInteger();

            switch (choice) {
                case 1:
                    showCoursesByStudent();
                    break;
                case 2:
                    updateEnrollment();
                    break;
                case 3:
                    deleteEnrollment();
                    break;
                case 4:
                    showAdminMenu();
                    break;
                default:
                    System.out.println(Colors.RED + "Lựa chọn không đúng!" + Colors.RESET);
            }
        }
    }

    private static void showCoursesByStudent() {
        List<StudentCourse> list = enrollmentService.displayStudentCourse();
        String currentCourse = "";
        for (StudentCourse studentCourse : list) {
            if (!studentCourse.getCourseName().equalsIgnoreCase(currentCourse)) {
                System.out.printf("-".repeat(49));
                System.out.printf("\n| KHÓA HỌC: %-35s |\n", studentCourse.getCourseName().toUpperCase());
                System.out.printf("-".repeat(49));
                System.out.println();
                currentCourse = studentCourse.getCourseName();
            }
            studentCourse.display();
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
            while(true) {
                System.out.println("1. Thêm học viên vào khóa học");
                System.out.println("2. Từ chối học viên vào khóa học");
                System.out.println("3. Quay về");
                System.out.println("Nhập lựa chọn: ");
                int choice =  InputMethods.getInteger();
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

            enrollmentService.updateStatus(enrollment, "CANCEL");
            System.out.println(Colors.GREEN + "Xóa học viên khỏi khóa học thành công!" + Colors.RESET);
        }
    }

    private static void showListStudent() {
        List<Student> list = studentService.findAll();
        if (list.isEmpty()) {
            System.out.println(Colors.RED + "Danh sách trống" + Colors.RESET);
        } else {
            System.out.printf("\n%sDANH SÁCH HỌC VIÊN%s\n", "-".repeat(62), "-".repeat(62));

            for (Student student : list) {
                student.displayData();
                System.out.printf("%s\n", "-".repeat(142));
            }
        }
    }

    private static void addStudent() {
        Student student = new Student();
        student.inputData();
        while (studentService.isEmailExist(student.getEmail())) {
            System.out.println(Colors.RED + "Tên email đã tồn tại, vui lòng nhập lại!" + Colors.RESET);
            String email;
            while (true) {
                System.out.println("Nhập email học viên: ");
                email = InputMethods.getString();
                if (email.contains("@") && email.contains(".")) {
                    break;
                }
                System.out.println(Colors.RED + "Email không hợp lệ (thiếu @ hoặc dấu .). Thử lại!" + Colors.RESET);
            }
            student.setEmail(email);
        }
        student.setPassword(BCrypt.hashpw(student.getPassword(), BCrypt.gensalt(12)));

        studentService.add(student);
        System.out.println(Colors.GREEN + "Thêm mới học viên thành công" + Colors.RESET);
    }

    private static void updateStudent() {
        showListStudent();
        System.out.println("\nChọn học viên cần cập nhật theo id: ");
        int id = InputMethods.getInteger();

        Student student = studentService.findById(id);
        while (student == null) {
            System.out.println(Colors.RED + "Không có học viên phù hợp, vui lòng nhập lại!!" + Colors.RESET);
            id = InputMethods.getInteger();
            student = studentService.findById(id);
        }
        System.out.printf("\n%sDANH SÁCH HỌC VIÊN%s\n", "-".repeat(62), "-".repeat(62));
        student.displayData();
        System.out.printf("%s\n", "-".repeat(142));
        while (true) {
            System.out.println("\n-------Cập nhật thông tin-------");
            System.out.println("1. Cập nhật tên");
            System.out.println("2. Cập nhật ngày sinh");
            System.out.println("3. Cập nhật email");
            System.out.println("4. Cập nhật giới tính");
            System.out.println("5. Cập nhật số điện thoại");
            System.out.println("6. Cập nhật mật khẩu");
            System.out.println("7. Lưu thay đổi và quay về");

            System.out.println("Nhập lựa chọn: ");
            int choice = InputMethods.getInteger();
            switch (choice) {
                case 1:
                    System.out.println("Nhập tên học viên: ");
                    student.setName(InputMethods.getString());
                    break;
                case 2:
                    while (true) {
                        System.out.println("Nhập ngày sinh (dd/MM/yyyy): ");
                        String dateStr = InputMethods.getString();
                        student.setDob(Validate.parseDate(dateStr));
                        if (student.getDob() != null) {
                            break;
                        }
                        System.out.println(Colors.RED + "Lỗi: Ngày sinh không đúng định dạng dd/MM/yyyy hoặc ngày không tồn tại!" + Colors.RESET);
                    }
                    break;
                case 3:
                    while (true) {
                        System.out.println("Nhập email học viên: ");
                        String emailInput = InputMethods.getString();
                        if (Validate.isValidEmail(emailInput)) {
                            student.setEmail(emailInput);
                            break;
                        }
                        System.out.println(Colors.RED + "Lỗi: Email không đúng định dạng (VD: example@gmail.com)!" + Colors.RESET);
                    }
                    break;
                case 4:
                    System.out.println("Chọn giới tính (1. Nam | 2. Nữ): ");
                    student.setSex((InputMethods.getInteger() == 1));
                    break;
                case 5:
                    while (true) {
                        System.out.println("Nhập số điện thoại (10 số, bắt đầu bằng 0): ");
                        String phoneInput = InputMethods.getString();
                        if (Validate.isValidPhone(phoneInput)) {
                            student.setPhone(phoneInput);
                            break;
                        }
                        System.out.println(Colors.RED + "Lỗi: Số điện thoại phải có 10 chữ số và bắt đầu bằng số 0!" + Colors.RESET);
                    }
                    break;
                case 6:
                    System.out.println("Nhập mật khẩu: ");
                    String password = InputMethods.getString();
                    student.setPassword(BCrypt.hashpw(password, BCrypt.gensalt(12)));
                    break;
                case 7:
                    student.setCreateAt(LocalDate.now());
                    studentService.update(student, id);
                    System.out.println(Colors.GREEN + "Cập nhật thành công" + Colors.RESET);
                    showStudentMenu();
                default:
                    System.out.println(Colors.RED + "Lựa chọn không đúng!" + Colors.RESET);
            }
        }
    }

    private static void deleteStudent() {
        System.out.println("\nChọn học viên cần xóa theo id: ");
        int id = InputMethods.getInteger();

        if (studentService.isIdExist(id)) {
            System.out.println("Bạn có chắc chắn muốn xóa không? (Y/N): ");
            String choice = InputMethods.getString();
            if (choice.equalsIgnoreCase("Y")) {
                studentService.delete(id);
                System.out.println(Colors.GREEN + "Xóa thành công!" + Colors.RESET);
            } else {
                System.out.println(Colors.GREEN + "Hủy thao tác xóa" + Colors.RESET);
            }
        } else {
            System.out.println(Colors.RED + "Học vie không tồn tại" + Colors.RESET);
        }
    }

    private static void findStudent() {
        List<Student> students = new ArrayList<>();
        while (true) {
            System.out.println("\n-------Tìm kiếm học viên-------");
            System.out.println("1. Tìm kếm theo tên");
            System.out.println("2. Tìm kiếm theo email");
            System.out.println("3. Tìm kiếm theo id");
            System.out.println("4. Quay về");

            System.out.println("Nhập lựa chọn: ");
            int choice = InputMethods.getInteger();
            switch (choice) {
                case 1:
                    System.out.println("Nhập tên học viên bạn muốn tìm kiếm: ");
                    String name = InputMethods.getString();
                    students = studentService.findByName(name);
                    if (students.isEmpty()) {
                        System.out.println(Colors.RED + "Không tìm thấy học viên" + Colors.RESET);
                    } else {
                        System.out.printf("\n%sDANH SÁCH HỌC VIÊN%s\n", "-".repeat(62), "-".repeat(62));
                        for (Student student : students) {
                            student.displayData();
                            System.out.printf("%s\n", "-".repeat(142));
                        }
                    }
                    break;
                case 2:
                    System.out.println("Nhập tên email bạn muốn tìm kiếm: ");
                    String email = InputMethods.getString();
                    students = studentService.findByEmail(email);
                    if (students.isEmpty()) {
                        System.out.println(Colors.RED + "Không tìm thấy học viên" + Colors.RESET);
                    } else {
                        System.out.printf("\n%sDANH SÁCH HỌC VIÊN%s\n", "-".repeat(62), "-".repeat(62));
                        for (Student student : students) {
                            student.displayData();
                            System.out.printf("%s\n", "-".repeat(142));
                        }
                    }
                    break;
                case 3:
                    System.out.println("Nhập id bạn muốn tìm kiếm: ");
                    Integer id = InputMethods.getInteger();
                    Student student = studentService.findById(id);
                    if (student == null) {
                        System.out.println(Colors.RED + "Không tìm thấy học viên" + Colors.RESET);
                    } else {
                        System.out.printf("\n%sDANH SÁCH HỌC VIÊN%s\n", "-".repeat(62), "-".repeat(62));
                        student.displayData();
                        System.out.printf("%s\n", "-".repeat(142));
                    }
                    break;
                case 4:
                    showStudentMenu();
                default:
                    System.out.println(Colors.RED + "Lựa chọn không đúng!" + Colors.RESET);
            }
        }
    }

    private static void sortStudents() {
        System.out.println("\n-------Sắp xếp-------");
        System.out.println("1. Sắp xếp theo tên tăng dần");
        System.out.println("2. Sắp xếp theo tên giảm dần");
        System.out.println("3. Sắp xếp theo id tăng dần");
        System.out.println("4. Sắp xếp theo id giảm dần");
        System.out.println("5. Quay về");

        System.out.println("Nhập lựa chọn: ");
        int choice = InputMethods.getInteger();
        switch (choice) {
            case 1:
                studentService.sort(1, 1);
                break;
            case 2:
                studentService.sort(1, 2);
                break;
            case 3:
                studentService.sort(2, 1);
                break;
            case 4:
                studentService.sort(2, 2);
                break;
            case 5:
                showStudentMenu();
            default:
                System.out.println(Colors.RED + "Lựa chọn không đúng!" + Colors.RESET);
        }
    }

    public static void showListCourse() {
        List<Course> list = courseService.findAll();
        if (list.isEmpty()) {
            System.out.println(Colors.RED + "Danh sách trống" + Colors.RESET);
        } else {
            System.out.printf("\n%sDANH SÁCH KHÓA HỌC%s\n", "-".repeat(58), "-".repeat(58));

            for (Course course : list) {
                course.displayData();
                System.out.printf("%s\n", "-".repeat(135));
            }
        }
    }

    private static void addCourse() {
        Course course = new Course();
        course.inputData();

        while (courseService.isNameExist(course.getName())) {
            System.out.println(Colors.RED + "Tên khóa học đã tồn tại, vui lòng nhập lại!" + Colors.RESET);
            course.setName(InputMethods.getString());
        }

        courseService.add(course);
        System.out.println(Colors.GREEN + "Thêm mới khóa học thành công" + Colors.RESET + "\n");
    }

    private static void updateCourse() {
        showListCourse();
        System.out.print("\nChọn khóa học cần cập nhật theo id: ");
        int id = InputMethods.getInteger();

        Course course = courseService.findById(id);
        while (course == null) {
            System.out.println(Colors.RED + "Không có khóa học phù hợp, vui lòng nhập lại!!" + Colors.RESET);
            id = InputMethods.getInteger();
            course = courseService.findById(id);
        }
        System.out.printf("\n%sDANH SÁCH KHÓA HỌC%s\n", "-".repeat(58), "-".repeat(58));
        course.displayData();
        System.out.printf("%s\n", "-".repeat(135));
        while (true) {
            System.out.println("\n-------Cập nhật thông tin-------");
            System.out.println("1. Cập nhật tên");
            System.out.println("2. Cập nhật thời lượng");
            System.out.println("3. Cập nhật giáo viên");
            System.out.println("4. Lưu thay đổi và quay về");

            System.out.println("Nhập lựa chọn: ");
            int choice = InputMethods.getInteger();
            switch (choice) {
                case 1:
                    System.out.println("Nhập tên khóa học: ");
                    course.setName(InputMethods.getString());
                    break;
                case 2:
                    System.out.println("Nhập thời lượng khóa học: ");
                    course.setDuration(InputMethods.getInteger());
                    break;
                case 3:
                    System.out.println("Nhập tên giáo viên: ");
                    course.setInstructor(InputMethods.getString());
                    break;
                case 4:
                    course.setCreateAt(LocalDate.now());
                    courseService.update(course, id);
                    System.out.println(Colors.GREEN + "Cập nhật thành công" + Colors.RESET);
                    showCourseMenu();
                default:
                    System.out.println(Colors.RED + "Lựa chọn không đúng!" + Colors.RESET);
            }
        }
    }

    private static void deleteCourse() {
        System.out.println("\nChọn khóa học cần xóa theo id: ");
        int id = InputMethods.getInteger();

        if (courseService.isIdExist(id)) {
            System.out.println("Bạn có chắc chắn muốn xóa không? (Y/N): ");
            String choice = InputMethods.getString();
            if (choice.equalsIgnoreCase("Y")) {
                courseService.delete(id);
                System.out.println(Colors.GREEN + "Xóa thành công!" + Colors.RESET);
            } else {
                System.out.println(Colors.GREEN + "Hủy thao tác xóa" + Colors.RESET);
            }
        } else {
            System.out.println(Colors.RED + "Khóa học không tồn tại" + Colors.RESET);
        }
    }

    public static void findCourseByName() {
        System.out.println("Nhập tên khóa học bạn muốn tìm kiếm: ");
        String name = InputMethods.getString();
        List<Course> courses = courseService.findByName(name);
        if (courses.isEmpty()) {
            System.out.println(Colors.RED + "Không tìm thấy khóa học" + Colors.RESET);
        } else {
            System.out.printf("\n%sDANH SÁCH KHÓA HỌC%s\n", "-".repeat(58), "-".repeat(58));
            for (Course course : courses) {
                course.displayData();
                System.out.printf("%s\n", "-".repeat(135));
            }
        }
    }

    private static void sortCourse() {
        System.out.println("\n-------Sắp xếp-------");
        System.out.println("1. Sắp xếp theo tên tăng dần");
        System.out.println("2. Sắp xếp theo tên giảm dần");
        System.out.println("3. Sắp xếp theo id tăng dần");
        System.out.println("4. Sắp xếp theo id giảm dần");
        System.out.println("5. Quay về");

        System.out.println("Nhập lựa chọn: ");
        int choice = InputMethods.getInteger();
        switch (choice) {
            case 1:
                courseService.sort(1, 1);
                break;
            case 2:
                courseService.sort(1, 2);
                break;
            case 3:
                courseService.sort(2, 1);
                break;
            case 4:
                courseService.sort(2, 2);
                break;
            case 5:
                showCourseMenu();
            default:
                System.out.println(Colors.RED + "Lựa chọn không đúng!" + Colors.RESET);
        }
    }
}