package ra.ponchakiet.service.impl;

import ra.ponchakiet.dao.IEnrollmentDao;
import ra.ponchakiet.dao.impl.EnrollmentDaoImpl;
import ra.ponchakiet.model.*;
import ra.ponchakiet.service.IEnrollmentService;
import ra.ponchakiet.utils.Colors;
import ra.ponchakiet.utils.InputMethods;

import java.util.List;

public class EnrollmentServiceImpl implements IEnrollmentService {
    private static final IEnrollmentDao enrollmentDao = new EnrollmentDaoImpl();

    @Override
    public void registerCourse(Enrollment enrollment) {
        enrollmentDao.saveEnrollment(enrollment);
    }

    @Override
    public boolean isExistEnrollment(int studentId, int courseId) {
        return enrollmentDao.isExistEnrollment(studentId, courseId);
    }

    @Override
    public void coursesEnrollment(int studentId) {
        int currentPage = 1;
        while (true) {
            List<CoursesEnrollment> list = enrollmentDao.coursesRegisted(studentId, currentPage);
            if (list.isEmpty()) {
                System.out.println(Colors.RED + "Danh sách trống" + Colors.RESET);
            } else {
                System.out.printf("\n%sDANH SÁCH KHÓA HỌC%s\n", "-".repeat(57), "-".repeat(57));
                for (CoursesEnrollment course : list) {
                    course.displayData();
                    System.out.printf("%s\n", "-".repeat(142));
                }
                System.out.println("--- TRANG " + currentPage + " ---");
                System.out.println("\n[N] - Trang tiếp | [B] - Trang trước | [E] - Thoát");
                System.out.print("Lựa chọn của bạn: ");
                String choice = InputMethods.getString();

                if (choice.equalsIgnoreCase("N")) {
                    if (list.size() < 5) {
                        System.out.println("Bạn đang ở trang cuối cùng!");
                    } else {
                        currentPage++;
                    }
                } else if (choice.equalsIgnoreCase("B")) {
                    if (currentPage > 1) {
                        currentPage--;
                    } else {
                        System.out.println("Bạn đang ở trang đầu tiên!");
                    }
                } else if (choice.equalsIgnoreCase("E")) {
                    break;
                }
            }

        }
    }

    @Override
    public void sort(Integer sortType, Integer sortOrder, int studentId) {
        String column = (sortType == 1) ? "name" : "registered_at";
        String direction = (sortOrder == 2) ? "DESC" : "ASC";
        List<CoursesEnrollment> ce = enrollmentDao.sort(column, direction, studentId);
        if (ce.isEmpty()) {
            System.out.println(Colors.RED + "Danh sách trống" + Colors.RESET);
        } else {
            System.out.printf("\n%sDANH SÁCH MÔN HỌC ĐÃ ĐĂNG KÝ%s\n", "-".repeat(57), "-".repeat(57));

            for (CoursesEnrollment course : ce) {
                course.displayData();
                System.out.printf("%s\n", "-".repeat(142));
            }
        }
    }

    @Override
    public void cancel(int studentId, int courseId) {
        enrollmentDao.deleteEnrollment(studentId, courseId);
    }

    @Override
    public CoursesEnrollment findEnrollment(int studentId, int courseId) {
        return enrollmentDao.findEnrollment(studentId, courseId);
    }

    @Override
    public List<StudentCourse> displayStudentCourse(int page) {
        return enrollmentDao.displayCourseByStudent(page);
    }

    @Override
    public void updateStatus(Enrollment enrollment, String status) {
        enrollmentDao.updateStatusEnrollment(enrollment, status);
    }

    @Override
    public Enrollment findEnrollmentByIdWaiting(int enrollmentId) {
        return enrollmentDao.findEnrollmentByIdWaiting(enrollmentId);
    }

    @Override
    public List<EnrollmentDetail> getEnrollmentDetailsWaiting() {
        return enrollmentDao.getAllStudentWaiting();
    }

    @Override
    public Enrollment findEnrollmentByIdConfirm(int enrollmentId) {
        return enrollmentDao.findEnrollmentByIdConfirm(enrollmentId);
    }

    @Override
    public List<EnrollmentDetail> getEnrollmentDetailsConfirm() {
        return enrollmentDao.getAllStudentConfirm();
    }

    @Override
    public List<StudentCourse> statisticsStudentsByCourse() {
        return enrollmentDao.countStudentsByCourse();
    }

    @Override
    public List<StudentCourse> get5CoursesByStudent() {
        return enrollmentDao.get5CourseByStudents();
    }

    @Override
    public List<StudentCourse> getCoursesByStudentOver10() {
        return enrollmentDao.getCourseByStudentsOver10();
    }
}
