package bt2;

import java.sql.*;

public class TaskManagement {

    private final String URL = "jdbc:postgresql://localhost:3306/todo_db";
    private final String USER = "postgres";
    private final String PASSWORD = "Ngocmai@1004";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Thêm công việc
    public void addTask(String taskName, String status) {
        String sql = "{ call add_task(?, ?) }";

        try (Connection conn = getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setString(1, taskName);
            stmt.setString(2, status);
            stmt.execute();

            System.out.println("Thêm công việc thành công!");

        } catch (SQLException e) {
            System.out.println("Lỗi DB: " + e.getMessage());
        }
    }

    // Liệt kê
    public void listTasks() {
        String sql = "{ call list_tasks() }";

        try (Connection conn = getConnection();
             CallableStatement stmt = conn.prepareCall(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("=== DANH SÁCH CÔNG VIỆC ===");

            while (rs.next()) {
                System.out.println(
                        "ID: " + rs.getInt("id") +
                                " | Task: " + rs.getString("task_name") +
                                " | Status: " + rs.getString("status")
                );
            }

        } catch (SQLException e) {
            System.out.println("Lỗi DB: " + e.getMessage());
        }
    }

    // Cập nhật trạng thái
    public void updateTaskStatus(int id, String status) {
        String sql = "{ call update_task_status(?, ?) }";

        try (Connection conn = getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setInt(1, id);
            stmt.setString(2, status);
            stmt.execute();

            System.out.println("Cập nhật thành công!");

        } catch (SQLException e) {
            System.out.println("Lỗi DB: " + e.getMessage());
        }
    }

    // Xóa
    public void deleteTask(int id) {
        String sql = "{ call delete_task(?) }";

        try (Connection conn = getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setInt(1, id);
            stmt.execute();

            System.out.println("Xóa thành công!");

        } catch (SQLException e) {
            System.out.println("Lỗi DB: " + e.getMessage());
        }
    }

    // Tìm kiếm
    public void searchTaskByName(String name) {
        String sql = "{ call search_task_by_name(?) }";

        try (Connection conn = getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println(
                        "ID: " + rs.getInt("id") +
                                " | Task: " + rs.getString("task_name") +
                                " | Status: " + rs.getString("status")
                );
            }

        } catch (SQLException e) {
            System.out.println("Lỗi DB: " + e.getMessage());
        }
    }

    // Thống kê
    public void taskStatistics() {
        String sql = "{ call task_statistics() }";

        try (Connection conn = getConnection();
             CallableStatement stmt = conn.prepareCall(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                System.out.println("Đã hoàn thành: " + rs.getInt("completed"));
                System.out.println("Chưa hoàn thành: " + rs.getInt("not_completed"));
            }

        } catch (SQLException e) {
            System.out.println("Lỗi DB: " + e.getMessage());
        }
    }
}