package ra.ponchakiet.dao.impl;

import ra.ponchakiet.dao.IEnrollmentDao;
import ra.ponchakiet.model.CoursesEnrollment;
import ra.ponchakiet.model.Enrollment;
import ra.ponchakiet.utils.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDaoImpl implements IEnrollmentDao {
    @Override
    public void saveEnrollment(Enrollment enrollment) {
        String sql = "INSERT INTO ENROLLMENT(student_id, course_id)" +
                " values(?,?)";
        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement pre = conn.prepareStatement(sql);
        ) {
            pre.setInt(1, enrollment.getStudentId());
            pre.setInt(2, enrollment.getCourseId());
            pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isExistEnrollment(int studentId, int courseId) {
        String sql = "SELECT COUNT(*) FROM ENROLLMENT WHERE student_id = ? AND course_id = ?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            ps.setInt(2, courseId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<CoursesEnrollment> coursesRegisted(int studentId) {
        List<CoursesEnrollment> list = new ArrayList<>();
        String sql = "SELECT c.id, c.name, c.instructor, " +
                "e.status, e.registered_at " +
                "FROM ENROLLMENT e JOIN COURSE c ON e.course_id = c.id " +
                "WHERE e.student_id = ?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CoursesEnrollment ce = new CoursesEnrollment();
                ce.setCourseId(rs.getInt(1));
                ce.setCourseName(rs.getString(2));
                ce.setInstructor(rs.getString(3));
                ce.setStatus(rs.getString(4));
                ce.setEnrollDate(rs.getTimestamp(5).toLocalDateTime().toLocalDate());
                list.add(ce);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<CoursesEnrollment> sort(String columnName, String direction) {
        List<CoursesEnrollment> list = new ArrayList<>();
        String sql = "SELECT c.id, c.name, c.instructor, " +
                "e.status, e.registered_at " +
                "FROM ENROLLMENT e JOIN COURSE c ON e.course_id = c.id " +
                "ORDER BY " + columnName + " " + direction;

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CoursesEnrollment ce = new CoursesEnrollment();
                ce.setCourseId(rs.getInt(1));
                ce.setCourseName(rs.getString(2));
                ce.setInstructor(rs.getString(3));
                ce.setStatus(rs.getString(4));
                ce.setEnrollDate(rs.getTimestamp(5).toLocalDateTime().toLocalDate());
                list.add(ce);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void deleteEnrollment(int studentId, int courseId) {
        String sql = "DELETE FROM ENROLLMENT WHERE student_id = ? AND course_id = ?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setInt(1, studentId);
            pre.setInt(2, courseId);
            pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public CoursesEnrollment findEnrollment(int studentId, int courseId) {
        String sql = "SELECT c.id, c.name, c.instructor, " +
                "e.status, e.registered_at " +
                "FROM ENROLLMENT e JOIN COURSE c ON e.course_id = c.id " +
                "WHERE e.student_id = ? AND  e.course_id = ?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement pre = conn.prepareStatement(sql)) {
            pre.setInt(1, studentId);
            pre.setInt(2, courseId);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                CoursesEnrollment ce = new CoursesEnrollment();
                ce.setCourseId(rs.getInt(1));
                ce.setCourseName(rs.getString(2));
                ce.setInstructor(rs.getString(3));
                ce.setStatus(rs.getString(4));
                ce.setEnrollDate(rs.getTimestamp(5).toLocalDateTime().toLocalDate());
                return ce;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
