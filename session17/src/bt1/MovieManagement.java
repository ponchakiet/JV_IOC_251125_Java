package bt1;

import java.sql.*;

public class MovieManagement {

    private final String URL = "jdbc:postgresql://localhost:5432/movie_db";
    private final String USER = "postgres";
    private final String PASSWORD = "Ngocmai@1004";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Thêm phim
    public void addMovie(String title, String director, int year) {
        String sql = "{ call add_movie(?, ?, ?) }";

        try (Connection conn = getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setString(1, title);
            stmt.setString(2, director);
            stmt.setInt(3, year);

            stmt.execute();
            System.out.println("Add movie successfully!");

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    // Liệt kê phim
    public void listMovies() {
        String sql = "SELECT * FROM list_movies()";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("=== Movie List ===");

            while (rs.next()) {
                System.out.println(
                        "ID: " + rs.getInt("id") +
                                " | Title: " + rs.getString("title") +
                                " | Director: " + rs.getString("director") +
                                " | Year: " + rs.getInt("year")
                );
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    // Cập nhật phim
    public void updateMovie(int id, String title, String director, int year) {
        String sql = "{ call update_movie(?, ?, ?, ?) }";

        try (Connection conn = getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setInt(1, id);
            stmt.setString(2, title);
            stmt.setString(3, director);
            stmt.setInt(4, year);

            stmt.execute();
            System.out.println("Update movie successfully!");

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    // Xóa phim
    public void deleteMovie(int id) {
        String sql = "{ call delete_movie(?) }";

        try (Connection conn = getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setInt(1, id);
            stmt.execute();

            System.out.println("Delete movie successfully!");

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}