package ra.ponchakiet.dao;

import ra.ponchakiet.model.*;

import java.util.List;

public interface IEnrollmentDao {
    void saveEnrollment(Enrollment enrollment);
    boolean isExistEnrollment (int studentId, int courseId);
    List<CoursesEnrollment> coursesRegisted(int studentId);
    List<CoursesEnrollment> sort(String columnName, String direction);
    void deleteEnrollment(int studentId, int courseId);
    CoursesEnrollment findEnrollment(int studentId, int courseId);
    List<StudentCourse> displayCourseByStudent();
    void updateStatusEnrollment(Enrollment enrollment, String status);
    Enrollment findEnrollmentByIdWaiting(int enrollmentId);
    Enrollment findEnrollmentByIdConfirm(int enrollmentId);
    List<EnrollmentDetail> getAllStudentWaiting();
    List<EnrollmentDetail> getAllStudentConfirm();
    List<StudentCourse> countStudentsByCourse();
    List<StudentCourse> get5CourseByStudents();
    List<StudentCourse> getCourseByStudentsOver10();
}
