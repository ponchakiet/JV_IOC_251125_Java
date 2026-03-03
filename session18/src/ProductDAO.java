import java.sql.Date;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;


public class ProductDAO {

    public List<Product> getAll() throws SQLException {
        String sql = "SELECT * FROM \"Product\" ORDER BY \"Product_Id\"";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            List<Product> list = new ArrayList<>();
            while (rs.next()) list.add(map(rs));
            return list;
        }
    }

    public Product getById(int id) throws SQLException {
        String sql = "SELECT * FROM \"Product\" WHERE \"Product_Id\" = ?";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? map(rs) : null;
            }
        }
    }

    public void add(String name, double price, String title, LocalDate created, String catalog, boolean status)
            throws SQLException {

        ProductValidator.validate(name, price, title, created, catalog);

        String sql = """
                INSERT INTO "Product"
                ("Product_Name","Product_Price","Product_Title","Product_created","Product_catalog","Product_Status")
                VALUES (?,?,?,?,?,?)
                """;

        try (Connection cn = DBConnection.getConnection()) {
            cn.setAutoCommit(false);
            try (PreparedStatement ps = cn.prepareStatement(sql)) {
                ps.setString(1, name.trim());
                ps.setDouble(2, price);
                ps.setString(3, title.trim());
                ps.setDate(4, Date.valueOf(created));
                ps.setString(5, catalog.trim());
                ps.setBoolean(6, status);

                ps.executeUpdate();
                cn.commit();
            } catch (SQLException ex) {
                cn.rollback();
                throw ex;
            } finally {
                cn.setAutoCommit(true);
            }
        }
    }

    public void update(int id, String name, double price, String title, LocalDate created, String catalog, boolean status)
            throws SQLException {

        ProductValidator.validate(name, price, title, created, catalog);

        String sql = """
                UPDATE "Product"
                SET "Product_Name"=?,
                    "Product_Price"=?,
                    "Product_Title"=?,
                    "Product_created"=?,
                    "Product_catalog"=?,
                    "Product_Status"=?
                WHERE "Product_Id"=?
                """;

        try (Connection cn = DBConnection.getConnection()) {
            cn.setAutoCommit(false);
            try (PreparedStatement ps = cn.prepareStatement(sql)) {
                ps.setString(1, name.trim());
                ps.setDouble(2, price);
                ps.setString(3, title.trim());
                ps.setDate(4, Date.valueOf(created));
                ps.setString(5, catalog.trim());
                ps.setBoolean(6, status);
                ps.setInt(7, id);

                int rows = ps.executeUpdate();
                if (rows == 0) throw new SQLException("Không tìm thấy Product_Id=" + id);

                cn.commit();
            } catch (SQLException ex) {
                cn.rollback();
                throw ex;
            } finally {
                cn.setAutoCommit(true);
            }
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM \"Product\" WHERE \"Product_Id\" = ?";

        try (Connection cn = DBConnection.getConnection()) {
            cn.setAutoCommit(false);
            try (PreparedStatement ps = cn.prepareStatement(sql)) {
                ps.setInt(1, id);

                int rows = ps.executeUpdate();
                if (rows == 0) throw new SQLException("Không tìm thấy Product_Id=" + id);

                cn.commit();
            } catch (SQLException ex) {
                cn.rollback();
                throw ex;
            } finally {
                cn.setAutoCommit(true);
            }
        }
    }

    public List<Product> searchByName(String keyword) throws SQLException {
        String sql = """
                SELECT * FROM "Product"
                WHERE LOWER("Product_Name") LIKE LOWER(?)
                ORDER BY "Product_Name"
                """;

        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, "%" + (keyword == null ? "" : keyword.trim()) + "%");

            try (ResultSet rs = ps.executeQuery()) {
                List<Product> list = new ArrayList<>();
                while (rs.next()) list.add(map(rs));
                return list;
            }
        }
    }

    public List<Product> sortByPriceAsc() throws SQLException {
        String sql = "SELECT * FROM \"Product\" ORDER BY \"Product_Price\" ASC";
        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            List<Product> list = new ArrayList<>();
            while (rs.next()) list.add(map(rs));
            return list;
        }
    }

    public Map<String, Long> countByCatalog() throws SQLException {
        String sql = """
                SELECT "Product_catalog", COUNT(*) AS total
                FROM "Product"
                GROUP BY "Product_catalog"
                ORDER BY total DESC, "Product_catalog"
                """;

        try (Connection cn = DBConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            Map<String, Long> map = new LinkedHashMap<>();
            while (rs.next()) {
                map.put(rs.getString("Product_catalog"), rs.getLong("total"));
            }
            return map;
        }
    }

    private Product map(ResultSet rs) throws SQLException {
        Product p = new Product();
        p.id = rs.getInt("Product_Id");
        p.name = rs.getString("Product_Name");
        p.price = rs.getDouble("Product_Price");
        p.title = rs.getString("Product_Title");
        p.created = rs.getDate("Product_created").toLocalDate();
        p.catalog = rs.getString("Product_catalog");
        p.status = rs.getBoolean("Product_Status");
        return p;
    }
}