package ra.ponchakiet.dao;

import ra.ponchakiet.model.Course;

import java.util.List;

public interface ICourseDao {
    List<Course> getAll(int page);
    List<Course> getAll();
    void saveCourse(Course course);
    void updateCourse(Course course, Integer id);
    void deleteCourse(Integer id);
    List<Course> findCourseByName(String name);
    Course findCourseById(Integer id);
    List<Course> sort(String columnName, String direction);
    int totalCourses();
}
