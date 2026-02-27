package bt3;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookManager {

    private final String URL = "jdbc:postgresql://localhost:3306/library_db";
    private final String USER = "postgres";
    private final String PASSWORD = "Ngocmai@1004"; // đổi theo máy bạn

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // ================== THÊM SÁCH ==================
    public void addBook(Book book) {
        String checkSql = "SELECT * FROM books WHERE title=? AND author=?";
        String insertSql = "INSERT INTO books(title, author, published_year, price) VALUES(?,?,?,?)";

        try (Connection conn = getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {

            checkStmt.setString(1, book.getTitle());
            checkStmt.setString(2, book.getAuthor());
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                System.out.println("Sách đã tồn tại!");
                return;
            }

            try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                insertStmt.setString(1, book.getTitle());
                insertStmt.setString(2, book.getAuthor());
                insertStmt.setInt(3, book.getPublishedYear());
                insertStmt.setDouble(4, book.getPrice());
                insertStmt.executeUpdate();
                System.out.println("Thêm sách thành công!");
            }

        } catch (SQLException e) {
            System.out.println("Lỗi khi thêm sách: " + e.getMessage());
        }
    }

    // ================== CẬP NHẬT ==================
    public void updateBook(int id, Book book) {
        String checkSql = "SELECT * FROM books WHERE id=?";
        String updateSql = "UPDATE books SET title=?, author=?, published_year=?, price=? WHERE id=?";

        try (Connection conn = getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {

            checkStmt.setInt(1, id);
            ResultSet rs = checkStmt.executeQuery();

            if (!rs.next()) {
                System.out.println("Không tìm thấy sách!");
                return;
            }

            try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                updateStmt.setString(1, book.getTitle());
                updateStmt.setString(2, book.getAuthor());
                updateStmt.setInt(3, book.getPublishedYear());
                updateStmt.setDouble(4, book.getPrice());
                updateStmt.setInt(5, id);
                updateStmt.executeUpdate();
                System.out.println("Cập nhật thành công!");
            }

        } catch (SQLException e) {
            System.out.println("Lỗi cập nhật: " + e.getMessage());
        }
    }

    // ================== XÓA ==================
    public void deleteBook(int id) {
        String sql = "DELETE FROM books WHERE id=?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();

            if (rows > 0)
                System.out.println("Xóa thành công!");
            else
                System.out.println("Không tìm thấy sách!");

        } catch (SQLException e) {
            System.out.println("Lỗi xóa: " + e.getMessage());
        }
    }

    // ================== TÌM THEO TÁC GIẢ ==================
    public void findBooksByAuthor(String author) {
        String sql = "SELECT * FROM books WHERE author LIKE ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + author + "%");
            ResultSet rs = stmt.executeQuery();

            boolean found = false;

            while (rs.next()) {
                found = true;
                System.out.println(
                        rs.getInt("id") + " | " +
                                rs.getString("title") + " | " +
                                rs.getString("author") + " | " +
                                rs.getInt("published_year") + " | " +
                                rs.getDouble("price")
                );
            }

            if (!found)
                System.out.println("Không tìm thấy sách!");

        } catch (SQLException e) {
            System.out.println("Lỗi tìm kiếm: " + e.getMessage());
        }
    }

    // ================== HIỂN THỊ TẤT CẢ ==================
    public void listAllBooks() {
        String sql = "SELECT * FROM books";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                                rs.getString("title") + " | " +
                                rs.getString("author") + " | " +
                                rs.getInt("published_year") + " | " +
                                rs.getDouble("price")
                );
            }

        } catch (SQLException e) {
            System.out.println("Lỗi hiển thị: " + e.getMessage());
        }
    }
}