package ra.ponchakiet.dao;

import ra.ponchakiet.model.CoursesEnrollment;
import ra.ponchakiet.model.Enrollment;
import ra.ponchakiet.model.EnrollmentDetail;
import ra.ponchakiet.model.StudentCourse;

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
}
