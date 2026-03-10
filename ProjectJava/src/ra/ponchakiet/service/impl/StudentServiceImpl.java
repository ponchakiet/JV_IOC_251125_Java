package ra.ponchakiet.service.impl;

import org.mindrot.jbcrypt.BCrypt;
import ra.ponchakiet.dao.ICourseDao;
import ra.ponchakiet.dao.IStudentDao;
import ra.ponchakiet.dao.impl.CourseDaoImpl;
import ra.ponchakiet.dao.impl.StudentDaoImpl;
import ra.ponchakiet.model.Course;
import ra.ponchakiet.model.Student;
import ra.ponchakiet.service.IStudentService;
import ra.ponchakiet.utils.Colors;

import java.util.List;

public class StudentServiceImpl implements IStudentService {
    private static final IStudentDao studentDao = new StudentDaoImpl();
    @Override
    public Student login(String email, String password) {
        Student student = studentDao.findStudentByEmail(email);
        if (student != null && BCrypt.checkpw(password, student.getPassword())) {
            return student;
        }
        return null;
    }

    @Override
    public void add(Student student) {
        studentDao.saveStudent(student);
    }

    @Override
    public void delete(Integer s) {
        studentDao.deleteStudent(s);
    }

    @Override
    public void update(Student student, Integer s) {
        studentDao.updateStudent(student, s);
    }

    @Override
    public List<Student> findAll() {
        return studentDao.getAll();
    }

    @Override
    public Student findById(Integer s) {
        return studentDao.findStudentById(s);
    }

    @Override
    public List<Student> findByName(String name) {
        return studentDao.findStudentByName(name);
    }

    @Override
    public List<Student> findByEmail(String email) {
        return studentDao.findStudentByEmailRelative(email);
    }

    @Override
    public void sort(Integer sortType, Integer sortOrder) {
        String column = (sortType == 1) ? "name" : "id";
        String direction = (sortOrder == 2) ? "DESC" : "ASC";
        List<Student> students = studentDao.sort(column, direction);
        if (students.isEmpty()) {
            System.out.println(Colors.RED + "Danh sách trống" + Colors.RESET);
        } else {
            System.out.printf("\n%sDANH SÁCH KHÓA HỌC%s\n", "-".repeat(58), "-".repeat(58));

            for (Student student : students) {
                student.displayData();
                System.out.printf("%s\n", "-".repeat(135));
            }
        }
    }

    @Override
    public boolean isEmailExist(String email) {
        return studentDao.getAll().stream().anyMatch(s -> s.getEmail().equals(email));
    }

    @Override
    public boolean isIdExist(int id) {
        return studentDao.getAll().stream().anyMatch(s -> s.getId() == id);
    }
}
