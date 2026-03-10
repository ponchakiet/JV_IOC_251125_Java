package ra.ponchakiet.service;

import ra.ponchakiet.model.CoursesEnrollment;
import ra.ponchakiet.model.Enrollment;

import java.util.List;
import java.util.Scanner;

public interface IEnrollmentService {
    void registerCourse(Enrollment enrollment);
    boolean isExistEnrollment (int studentId, int courseId);
    List<CoursesEnrollment> coursesEnrollment (int studentId);
    void sort(Integer sortType, Integer sortOrder);
    void cancel(int studentId, int courseId);
    CoursesEnrollment findEnrollment (int studentId, int courseId);
}
