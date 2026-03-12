package ra.ponchakiet.service;

import ra.ponchakiet.model.CoursesEnrollment;
import ra.ponchakiet.model.Enrollment;
import ra.ponchakiet.model.EnrollmentDetail;
import ra.ponchakiet.model.StudentCourse;

import java.util.List;

public interface IEnrollmentService {
    void registerCourse(Enrollment enrollment);
    boolean isExistEnrollment (int studentId, int courseId);
    List<CoursesEnrollment> coursesEnrollment (int studentId);
    void sort(Integer sortType, Integer sortOrder);
    void cancel(int studentId, int courseId);
    CoursesEnrollment findEnrollment (int studentId, int courseId);
    List<StudentCourse> displayStudentCourse(int page);
    void updateStatus(Enrollment enrollment, String status);
    Enrollment findEnrollmentByIdWaiting(int enrollmentId);
    List<EnrollmentDetail> getEnrollmentDetailsWaiting();
    Enrollment findEnrollmentByIdConfirm(int enrollmentId);
    List<EnrollmentDetail> getEnrollmentDetailsConfirm();
    List<StudentCourse> statisticsStudentsByCourse();
    List<StudentCourse> get5CoursesByStudent();
    List<StudentCourse> getCoursesByStudentOver10();
}
