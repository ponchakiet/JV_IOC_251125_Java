package ra.ponchakiet.dao.impl;

import ra.ponchakiet.dao.ICourseDao;
import ra.ponchakiet.model.Course;
import ra.ponchakiet.utils.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDaoImpl implements ICourseDao {
    @Override
    public List<Course> getAll(int page) {
        List<Course> list = new ArrayList<>();
        int offset = (page - 1) * 5;
        String sql = "SELECT * FROM COURSE ORDER BY id LIMIT 5 OFFSET ?";
        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setInt(1, offset);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Course c = new Course();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                c.setDuration(rs.getInt("duration"));
                c.setInstructor(rs.getString("instructor"));
                c.setCreateAt(rs.getDate("created_at").toLocalDate());
                list.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Course> getAll() {
        List<Course> list = new ArrayList<>();
        String sql = "SELECT * FROM COURSE";
        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Course c = new Course();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                c.setDuration(rs.getInt("duration"));
                c.setInstructor(rs.getString("instructor"));
                c.setCreateAt(rs.getDate("created_at").toLocalDate());
                list.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void saveCourse(Course course) {
        String sql = "INSERT INTO COURSE(name, duration, instructor)" +
                " values(?,?,?)";
        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement pre = conn.prepareStatement(sql);
        ) {
            pre.setString(1, course.getName());
            pre.setInt(2, course.getDuration());
            pre.setString(3, course.getInstructor());
            pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCourse(Course course, Integer id) {
        String sql = "UPDATE COURSE SET name = ?, duration = ?, instructor = ?, created_at = ? WHERE id = ?";
        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement pre = conn.prepareStatement(sql);
        ) {
            pre.setString(1, course.getName());
            pre.setInt(2, course.getDuration());
            pre.setString(3, course.getInstructor());
            pre.setDate(4, Date.valueOf(course.getCreateAt()));
            pre.setInt(5, id);
            pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCourse(Integer id) {
        String sql = "DELETE FROM COURSE WHERE id = ?";
        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement pre = conn.prepareStatement(sql);
        ) {
            pre.setInt(1, id);
            pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Course> findCourseByName(String name) {
        List<Course> listCourseByName = new ArrayList<>();
        String sql = "SELECT * FROM COURSE WHERE name ILIKE ?";
        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Course c = new Course();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                c.setDuration(rs.getInt("duration"));
                c.setInstructor(rs.getString("instructor"));
                c.setCreateAt(rs.getDate("created_at").toLocalDate());
                listCourseByName.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listCourseByName;
    }

    @Override
    public Course findCourseById(Integer id) {
        String sql = "SELECT * FROM COURSE WHERE id = ?";
        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Course(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getDate(5).toLocalDate()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Course> sort(String columnName, String direction) {
        List<Course> list = new ArrayList<>();
        String sql = "SELECT * FROM COURSE ORDER BY " + columnName + " " + direction;

        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Course c = new Course();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                c.setDuration(rs.getInt("duration"));
                c.setInstructor(rs.getString("instructor"));
                c.setCreateAt(rs.getDate("created_at").toLocalDate());
                list.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int totalCourses() {
        int total = 0;
        String sql = "SELECT COUNT(*) FROM COURSE";

        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }
}
