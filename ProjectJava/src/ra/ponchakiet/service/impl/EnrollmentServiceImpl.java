package ra.ponchakiet.service.impl;

import ra.ponchakiet.dao.IEnrollmentDao;
import ra.ponchakiet.dao.impl.EnrollmentDaoImpl;
import ra.ponchakiet.model.*;
import ra.ponchakiet.service.IEnrollmentService;
import ra.ponchakiet.utils.Colors;

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
    public List<CoursesEnrollment> coursesEnrollment(int studentId) {
        return enrollmentDao.coursesRegisted(studentId);
    }

    @Override
    public void sort(Integer sortType, Integer sortOrder) {
        String column = (sortType == 1) ? "name" : "registered_at";
        String direction = (sortOrder == 2) ? "DESC" : "ASC";
        List<CoursesEnrollment> ce = enrollmentDao.sort(column, direction);
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
        return  enrollmentDao.findEnrollment(studentId, courseId);
    }

    @Override
    public List<StudentCourse> displayStudentCourse() {
        return enrollmentDao.displayCourseByStudent();
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
