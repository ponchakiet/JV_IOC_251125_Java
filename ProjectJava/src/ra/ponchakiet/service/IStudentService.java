package ra.ponchakiet.service;

import ra.ponchakiet.model.Student;

import java.util.List;

public interface IStudentService extends IBaseService<Student, Integer> {
    Student login(String email, String password);
    List<Student> findByName(String name);
    List<Student> findByEmail(String email);
    boolean isEmailExist(String email);
    boolean isIdExist(int id);
    void changePassword(Integer id, String password);
    void findAllPagination();
}
