package ra.ponchakiet.service.impl;

import ra.ponchakiet.dao.ICourseDao;
import ra.ponchakiet.dao.impl.CourseDaoImpl;
import ra.ponchakiet.model.Course;
import ra.ponchakiet.service.ICourseService;
import ra.ponchakiet.utils.Colors;

import java.util.List;

public class CourseServiceImpl implements ICourseService {
    private static final ICourseDao courseDao = new CourseDaoImpl();
    @Override
    public void add(Course course) {
        courseDao.saveCourse(course);
    }

    @Override
    public void update(Course course, Integer s) {
        courseDao.updateCourse(course,s);
    }

    @Override
    public void delete(Integer s) {
        courseDao.deleteCourse(s);
    }

    @Override
    public Course findById(Integer s) {
        return courseDao.findCourseById(s);
    }

    @Override
    public List<Course> findAll() {
        return courseDao.getAll();
    }

    @Override
    public void sort(Integer sortType, Integer sortOrder) {
        String column = (sortType == 1) ? "name" : "id";
        String direction = (sortOrder == 2) ? "DESC" : "ASC";
        List<Course> courses = courseDao.sort(column, direction);
        if (courses.isEmpty()) {
            System.out.println(Colors.RED + "Danh sách trống" + Colors.RESET);
        } else {
            System.out.printf("\n%sDANH SÁCH KHÓA HỌC%s\n", "-".repeat(58), "-".repeat(58));

            for (Course course : courses) {
                course.displayData();
                System.out.printf("%s\n", "-".repeat(135));
            }
        }
    }

    @Override
    public List<Course> findByName(String name) {
        return courseDao.findCourseByName(name);
    }

    @Override
    public boolean isNameExist(String name) {
        return courseDao.getAll().stream().anyMatch(c -> c.getName().equals(name));
    }

    @Override
    public boolean isIdExist(int id) {
        return courseDao.getAll().stream().anyMatch(c -> c.getId() == id);
    }

    @Override
    public int totalCourses() {
        return courseDao.totalCourses();
    }
}
