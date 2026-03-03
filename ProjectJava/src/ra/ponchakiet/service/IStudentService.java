package ra.ponchakiet.service;

import ra.ponchakiet.model.Student;

public interface IStudentService {
    Student login(String email, String password);
}
