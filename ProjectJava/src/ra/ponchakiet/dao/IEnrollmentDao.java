package ra.ponchakiet.dao;

import ra.ponchakiet.model.Course;
import ra.ponchakiet.model.CoursesEnrollment;
import ra.ponchakiet.model.Enrollment;

import java.util.List;

public interface IEnrollmentDao {
    void saveEnrollment(Enrollment enrollment);
    boolean isExistEnrollment (int studentId, int courseId);
    List<CoursesEnrollment> coursesRegisted(int studentId);
    List<CoursesEnrollment> sort(String columnName, String direction);
    void deleteEnrollment(int studentId, int courseId);
    CoursesEnrollment findEnrollment(int studentId, int courseId);
}
