package ra.ponchakiet.service.impl;

import org.mindrot.jbcrypt.BCrypt;
import ra.ponchakiet.dao.IStudentDao;
import ra.ponchakiet.dao.impl.StudentDaoImpl;
import ra.ponchakiet.model.Student;
import ra.ponchakiet.service.IStudentService;

public class StudentServiceImpl implements IStudentService {
    private static IStudentDao studentDao = new StudentDaoImpl();
    @Override
    public Student login(String email, String password) {
        Student student = studentDao.findStudentByEmail(email);
        if (student != null && BCrypt.checkpw(password, student.getPassword())) {
            return student;
        }
        return null;
    }
}
