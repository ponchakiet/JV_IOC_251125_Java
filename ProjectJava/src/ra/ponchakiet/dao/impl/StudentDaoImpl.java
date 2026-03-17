package ra.ponchakiet.dao.impl;

import ra.ponchakiet.dao.IStudentDao;
import ra.ponchakiet.model.Course;
import ra.ponchakiet.model.Student;
import ra.ponchakiet.utils.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements IStudentDao {

    @Override
    public List<Student> getAll() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM STUDENT";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setDob(rs.getDate("dob").toLocalDate());
                s.setEmail(rs.getString("email"));
                s.setSex(rs.getBoolean("sex"));
                s.setPhone(rs.getString("phone"));
                s.setPassword(rs.getString("password"));
                s.setCreateAt(rs.getDate("created_at").toLocalDate());
                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void saveStudent(Student student) {
        String sql = "INSERT INTO STUDENT(name, dob, email, sex, phone, password) VALUES (?,?,?,?,?,?)";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement pre = conn.prepareStatement(sql))
        {
            pre.setString(1, student.getName());
            pre.setDate(2, Date.valueOf(student.getDob()));
            pre.setString(3, student.getEmail());
            pre.setBoolean(4, student.getSex());
            pre.setString(5, student.getPhone());
            pre.setString(6, student.getPassword());
            pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateStudent(Student student, Integer id) {
        String sql = "UPDATE STUDENT SET name = ?, dob = ?, email = ?, sex = ?, phone = ?, password = ? WHERE id = ?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement pre = conn.prepareStatement(sql))
        {
            pre.setString(1, student.getName());
            pre.setDate(2, Date.valueOf(student.getDob()));
            pre.setString(3, student.getEmail());
            pre.setBoolean(4, student.getSex());
            pre.setString(5, student.getPhone());
            pre.setString(6, student.getPassword());
            pre.setInt(7, id);
            pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteStudent(Integer id) {
        String sql = "DELETE FROM STUDENT WHERE id = ?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement pre = conn.prepareStatement(sql))
        {
            pre.setInt(1, id);
            pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Student> findStudentByName(String name) {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM STUDENT WHERE name ILIKE ?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setDob(rs.getDate("dob").toLocalDate());
                s.setEmail(rs.getString("email"));
                s.setSex(rs.getBoolean("sex"));
                s.setPhone(rs.getString("phone"));
                s.setPassword(rs.getString("password"));
                s.setCreateAt(rs.getDate("created_at").toLocalDate());
                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Student findStudentById(Integer id) {
        String sql = "SELECT * FROM STUDENT WHERE id = ?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setDob(rs.getDate("dob").toLocalDate());
                s.setEmail(rs.getString("email"));
                s.setSex(rs.getBoolean("sex"));
                s.setPhone(rs.getString("phone"));
                s.setPassword(rs.getString("password"));
                s.setCreateAt(rs.getDate("created_at").toLocalDate());
                return s;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Student findStudentByEmail(String email) {
        String sql = "SELECT * FROM STUDENT WHERE email = ?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setDob(rs.getDate("dob").toLocalDate());
                s.setEmail(rs.getString("email"));
                s.setSex(rs.getBoolean("sex"));
                s.setPhone(rs.getString("phone"));
                s.setPassword(rs.getString("password"));
                s.setCreateAt(rs.getDate("created_at").toLocalDate());
                return s;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Student> findStudentByEmailRelative(String email) {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM STUDENT WHERE email ILIKE ?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + email + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setDob(rs.getDate("dob").toLocalDate());
                s.setEmail(rs.getString("email"));
                s.setSex(rs.getBoolean("sex"));
                s.setPhone(rs.getString("phone"));
                s.setPassword(rs.getString("password"));
                s.setCreateAt(rs.getDate("created_at").toLocalDate());
                list.add(s);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public List<Student> sort(String columnName, String direction) {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM STUDENT ORDER BY " + columnName + " " + direction;

        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setDob(rs.getDate("dob").toLocalDate());
                s.setEmail(rs.getString("email"));
                s.setSex(rs.getBoolean("sex"));
                s.setPhone(rs.getString("phone"));
                s.setPassword(rs.getString("password"));
                s.setCreateAt(rs.getDate("created_at").toLocalDate());
                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void updatePassword(Integer id, String password) {
        String sql = "UPDATE student SET password = ? WHERE id = ?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, password);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Student> getAll(int page) {
        List<Student> list = new ArrayList<>();
        int offset = (page - 1) * 5;
        String sql = "SELECT * FROM STUDENT ORDER BY id LIMIT 5 OFFSET ?";
        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setInt(1, offset);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Student c = new Student();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                c.setDob(rs.getDate("dob").toLocalDate());
                c.setEmail(rs.getString("email"));
                c.setSex(rs.getBoolean("sex"));
                c.setPhone(rs.getString("phone"));
                c.setCreateAt(rs.getDate("created_at").toLocalDate());
                list.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}