package ra.ponchakiet.dao.impl;

import ra.ponchakiet.dao.IStudentDao;
import ra.ponchakiet.model.Student;
import ra.ponchakiet.utils.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDaoImpl implements IStudentDao {
    @Override
    public Student findStudentByEmail(String email) {
        String sql = "SELECT * FROM STUDENT WHERE EMAIL = ?";
        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Student(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDate(3).toLocalDate(),
                        rs.getString(4),
                        rs.getBoolean(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getDate(8).toLocalDate()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
