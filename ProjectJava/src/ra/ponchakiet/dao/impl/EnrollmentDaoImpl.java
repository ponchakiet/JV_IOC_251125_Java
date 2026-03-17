package ra.ponchakiet.dao.impl;

import ra.ponchakiet.dao.IEnrollmentDao;
import ra.ponchakiet.model.*;
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
        String sql = "SELECT COUNT(*) FROM ENROLLMENT WHERE student_id = ? AND course_id = ? AND (status != 'CANCEL')";
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
    public List<CoursesEnrollment> coursesRegisted(int studentId, int page) {
        List<CoursesEnrollment> list = new ArrayList<>();
        int offset = (page - 1) * 5;
        String sql = "SELECT c.id, c.name, c.instructor, " +
                "e.status, e.registered_at " +
                "FROM ENROLLMENT e JOIN COURSE c ON e.course_id = c.id " +
                "WHERE e.student_id = ? " +
                "ORDER BY c.id LIMIT 5 OFFSET ?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            ps.setInt(2, offset);
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
    public List<CoursesEnrollment> sort(String columnName, String direction, int studentId) {
        List<CoursesEnrollment> list = new ArrayList<>();
        String sql = "SELECT c.id, c.name, c.instructor, " +
                "e.status, e.registered_at " +
                "FROM ENROLLMENT e JOIN COURSE c ON e.course_id = c.id " +
                "WHERE e.student_id = ? " +
                "ORDER BY " + columnName + " " + direction;

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
    public void deleteEnrollment(int studentId, int courseId) {
        String sql = "UPDATE enrollment SET status = 'CANCEL' WHERE student_id = ? AND course_id = ?";
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

    @Override
    public List<StudentCourse> displayCourseByStudent(int page) {
        List<StudentCourse> list = new ArrayList<>();
        int offset = page - 1;
        String sql = "SELECT c.name AS course_name, s.name AS student_name " +
                "FROM (SELECT id, name FROM course ORDER BY id LIMIT 1 OFFSET ?) c " +
                "JOIN enrollment e ON c.id = e.course_id " +
                "JOIN student s ON e.student_id = s.id " +
                "WHERE e.status = 'CONFIRM' " +
                "ORDER BY s.name";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, offset);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                StudentCourse sc = new StudentCourse();
                sc.setCourseName(rs.getString("course_name"));
                sc.setStudentName(rs.getString("student_name"));
                list.add(sc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void updateStatusEnrollment(Enrollment enrollment, String status) {
        String sql = "UPDATE enrollment SET status = ?::enrollment_status WHERE id = ?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, enrollment.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Enrollment findEnrollmentByIdWaiting(int enrollmentId) {
        String sql = "SELECT id, student_id, course_id, status, registered_at " +
                "FROM enrollment WHERE id = ? AND status = 'WAITING'";

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, enrollmentId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Enrollment enrollment = new Enrollment();
                enrollment.setId(rs.getInt("id"));
                enrollment.setStudentId(rs.getInt("student_id"));
                enrollment.setCourseId(rs.getInt("course_id"));
                enrollment.setStatus(EnrollmentStatus.valueOf(rs.getString("status")));
                enrollment.setRegisteredAt(rs.getTimestamp("registered_at").toLocalDateTime());
                return enrollment;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Enrollment findEnrollmentByIdConfirm(int enrollmentId) {
        String sql = "SELECT id, student_id, course_id, status, registered_at " +
                "FROM enrollment WHERE id = ? AND status = 'CONFIRM'";

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, enrollmentId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Enrollment enrollment = new Enrollment();
                enrollment.setId(rs.getInt("id"));
                enrollment.setStudentId(rs.getInt("student_id"));
                enrollment.setCourseId(rs.getInt("course_id"));
                enrollment.setStatus(EnrollmentStatus.valueOf(rs.getString("status")));
                enrollment.setRegisteredAt(rs.getTimestamp("registered_at").toLocalDateTime());
                return enrollment;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<EnrollmentDetail> getAllStudentWaiting() {
        List<EnrollmentDetail> list = new ArrayList<>();
        String sql = "SELECT e.id, s.name AS student_name, c.name AS course_name, " +
                "e.registered_at, e.status " +
                "FROM enrollment e " +
                "JOIN student s ON e.student_id = s.id " +
                "JOIN course c ON e.course_id = c.id " +
                "WHERE e.status = 'WAITING'";

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                EnrollmentDetail dto = new EnrollmentDetail();
                dto.setId(rs.getInt("id"));
                dto.setStudentName(rs.getString("student_name"));
                dto.setCourseName(rs.getString("course_name"));
                dto.setStatus(rs.getString("status"));
                dto.setRegisteredAt(rs.getTimestamp("registered_at").toLocalDateTime().toLocalDate());
                list.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<EnrollmentDetail> getAllStudentConfirm() {
        List<EnrollmentDetail> list = new ArrayList<>();
        String sql = "SELECT e.id, s.name AS student_name, c.name AS course_name, " +
                "e.registered_at, e.status " +
                "FROM enrollment e " +
                "JOIN student s ON e.student_id = s.id " +
                "JOIN course c ON e.course_id = c.id " +
                "WHERE e.status = 'CONFIRM'";

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                EnrollmentDetail dto = new EnrollmentDetail();
                dto.setId(rs.getInt("id"));
                dto.setStudentName(rs.getString("student_name"));
                dto.setCourseName(rs.getString("course_name"));
                dto.setStatus(rs.getString("status"));
                dto.setRegisteredAt(rs.getTimestamp("registered_at").toLocalDateTime().toLocalDate());
                list.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<StudentCourse> countStudentsByCourse() {
        List<StudentCourse> list = new ArrayList<>();
        String sql = "SELECT c.name AS course_name, COUNT(e.student_id) AS total_students " +
                "FROM course c " +
                "JOIN enrollment e ON c.id = e.course_id " +
                "WHERE e.status = 'CONFIRM' " +
                "GROUP BY c.name " +
                "ORDER BY course_name";

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                StudentCourse sc = new StudentCourse();
                sc.setCourseName(rs.getString("course_name"));
                sc.setStudentName(String.valueOf(rs.getInt("total_students")));
                list.add(sc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<StudentCourse> get5CourseByStudents() {
        List<StudentCourse> list = new ArrayList<>();
        String sql = "SELECT c.name AS course_name, COUNT(e.student_id) AS total_students " +
                "FROM course c " +
                "JOIN enrollment e ON c.id = e.course_id " +
                "WHERE e.status = 'CONFIRM' " +
                "GROUP BY c.name " +
                "ORDER BY total_students DESC " +
                "LIMIT 5";

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                StudentCourse sc = new StudentCourse();
                sc.setCourseName(rs.getString("course_name"));
                sc.setStudentName(String.valueOf(rs.getInt("total_students")));
                list.add(sc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<StudentCourse> getCourseByStudentsOver10() {
        List<StudentCourse> list = new ArrayList<>();
        String sql = "SELECT c.name AS course_name, COUNT(e.student_id) AS total_students " +
                "FROM course c " +
                "JOIN enrollment e ON c.id = e.course_id " +
                "WHERE e.status = 'CONFIRM' " +
                "GROUP BY c.name " +
                "HAVING COUNT(e.student_id) > 10 " +
                "ORDER BY course_name";

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                StudentCourse sc = new StudentCourse();
                sc.setCourseName(rs.getString("course_name"));
                sc.setStudentName(String.valueOf(rs.getInt("total_students")));
                list.add(sc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
