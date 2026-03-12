package ra.ponchakiet.presentation;

import org.mindrot.jbcrypt.BCrypt;
import ra.ponchakiet.model.Student;
import ra.ponchakiet.service.IStudentService;
import ra.ponchakiet.service.impl.StudentServiceImpl;
import ra.ponchakiet.utils.Colors;
import ra.ponchakiet.utils.InputMethods;
import ra.ponchakiet.utils.Validate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentAdminView {
    private static final IStudentService studentService = new StudentServiceImpl();

    public static void showStudentMenu() {
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
                    AdminView.showAdminMenu();
                    break;
                default:
                    System.out.println(Colors.RED + "Lựa chọn không đúng!" + Colors.RESET);
            }
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
}
