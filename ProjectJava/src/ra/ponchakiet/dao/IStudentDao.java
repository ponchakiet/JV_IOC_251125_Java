package ra.ponchakiet.dao;

import ra.ponchakiet.model.Course;
import ra.ponchakiet.model.Student;

import java.util.List;

public interface IStudentDao {
    Student findStudentByEmail(String email);
    List<Student> getAll();
    void saveStudent(Student student);
    void updateStudent(Student student, Integer id);
    void deleteStudent(Integer id);
    List<Student> findStudentByName(String name);
    Student findStudentById(Integer id);
    List<Student> findStudentByEmailRelative(String email);
    List<Student> sort(String columnName, String direction);
    void updatePassword(Integer id, String password);
    int totalStudents();
}
