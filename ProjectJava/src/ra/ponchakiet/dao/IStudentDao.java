package ra.ponchakiet.dao;

import ra.ponchakiet.model.Student;

public interface IStudentDao {
    Student findStudentByEmail(String email);
}
