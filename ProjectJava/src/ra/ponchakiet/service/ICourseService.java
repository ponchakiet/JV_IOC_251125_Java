package ra.ponchakiet.service;

import ra.ponchakiet.model.Course;

import java.util.List;

public interface ICourseService extends IBaseService<Course, Integer> {
    boolean isNameExist(String name);
    List<Course> findByName(String name);
    boolean isIdExist(int id);
}
