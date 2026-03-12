package ra.ponchakiet.presentation;

import ra.ponchakiet.model.Course;
import ra.ponchakiet.service.ICourseService;
import ra.ponchakiet.service.impl.CourseServiceImpl;
import ra.ponchakiet.utils.Colors;
import ra.ponchakiet.utils.InputMethods;

import java.time.LocalDate;
import java.util.List;

public class CourseView {
    private static final ICourseService courseService = new CourseServiceImpl();
    public static void showCourseMenu() {
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
                    AdminView.showAdminMenu();
                    break;
                default:
                    System.out.println(Colors.RED + "Lựa chọn không đúng!" + Colors.RESET);
            }
        }
    }

    public static void showListCourse() {
        List<Course> list = courseService.findAll();
        if (list.isEmpty()) {
            System.out.println(Colors.RED + "Danh sách trống" + Colors.RESET);
        } else {
            courseService.findAllPagination();
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
        System.out.println(Colors.GREEN + "Thêm mới khóa học thành công" + Colors.RESET);
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

            System.out.print("Nhập lựa chọn: ");
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
        System.out.print("\nChọn khóa học cần xóa theo id: ");
        int id = InputMethods.getInteger();

        if (courseService.isIdExist(id)) {
            System.out.print("Bạn có chắc chắn muốn xóa không? (Y/N): ");
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
        System.out.print("Nhập tên khóa học bạn muốn tìm kiếm: ");
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

        System.out.print("Nhập lựa chọn: ");
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
