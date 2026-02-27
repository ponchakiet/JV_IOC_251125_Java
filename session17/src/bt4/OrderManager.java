package bt4;

import java.sql.*;
import java.util.Scanner;

public class OrderManager {

    private final String URL = "jdbc:mysql://localhost:3306/order_db";
    private final String USER = "root";
    private final String PASSWORD = "123456";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // ================== THÊM PRODUCT ==================
    public void addProduct(Product product) {
        String checkSql = "SELECT * FROM Product WHERE name=?";
        String insertSql = "INSERT INTO Product(name, price) VALUES(?,?)";

        try (Connection conn = getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {

            checkStmt.setString(1, product.getName());
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                System.out.println("Sản phẩm đã tồn tại!");
                return;
            }

            try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                insertStmt.setString(1, product.getName());
                insertStmt.setDouble(2, product.getPrice());
                insertStmt.executeUpdate();
                System.out.println("Thêm sản phẩm thành công!");
            }

        } catch (SQLException e) {
            System.out.println("Lỗi thêm sản phẩm: " + e.getMessage());
        }
    }

    // ================== UPDATE CUSTOMER ==================
    public void updateCustomer(int id, Customer customer) {
        String checkSql = "SELECT * FROM Customer WHERE id=?";
        String updateSql = "UPDATE Customer SET name=?, email=? WHERE id=?";

        try (Connection conn = getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {

            checkStmt.setInt(1, id);
            ResultSet rs = checkStmt.executeQuery();

            if (!rs.next()) {
                System.out.println("Không tìm thấy khách hàng!");
                return;
            }

            try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                updateStmt.setString(1, customer.getName());
                updateStmt.setString(2, customer.getEmail());
                updateStmt.setInt(3, id);
                updateStmt.executeUpdate();
                System.out.println("Cập nhật thành công!");
            }

        } catch (SQLException e) {
            System.out.println("Lỗi cập nhật khách hàng: " + e.getMessage());
        }
    }

    // ================== TẠO ORDER ==================
    public void createOrder() {
        Scanner sc = new Scanner(System.in);

        try (Connection conn = getConnection()) {

            System.out.print("Nhập ID khách hàng: ");
            int customerId = Integer.parseInt(sc.nextLine());

            System.out.print("Nhập ID sản phẩm: ");
            int productId = Integer.parseInt(sc.nextLine());

            System.out.print("Nhập số lượng: ");
            int quantity = Integer.parseInt(sc.nextLine());

            String productSql = "SELECT price FROM Product WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(productSql);
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                System.out.println("Không tìm thấy sản phẩm!");
                return;
            }

            double price = rs.getDouble("price");
            double total = price * quantity;

            String insertOrder = "INSERT INTO Orders(customer_id, order_date, total_amount) VALUES(?,?,?)";
            PreparedStatement insertStmt = conn.prepareStatement(insertOrder);
            insertStmt.setInt(1, customerId);
            insertStmt.setDate(2, new Date(System.currentTimeMillis()));
            insertStmt.setDouble(3, total);
            insertStmt.executeUpdate();

            System.out.println("Tạo đơn hàng thành công! Tổng tiền: " + total);

        } catch (Exception e) {
            System.out.println("Lỗi tạo đơn hàng: " + e.getMessage());
        }
    }

    // ================== LIST ALL ORDERS ==================
    public void listAllOrders() {
        String sql = "SELECT o.id, c.name, o.order_date, o.total_amount " +
                "FROM Orders o JOIN Customer c ON o.customer_id = c.id";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(
                        "Order ID: " + rs.getInt("id") +
                                " | Customer: " + rs.getString("name") +
                                " | Date: " + rs.getDate("order_date") +
                                " | Total: " + rs.getDouble("total_amount")
                );
            }

        } catch (SQLException e) {
            System.out.println("Lỗi hiển thị đơn hàng: " + e.getMessage());
        }
    }

    // ================== GET ORDER BY CUSTOMER ==================
    public void getOrdersByCustomer(int customerId) {
        String sql = "SELECT * FROM Orders WHERE customer_id=?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println(
                        "Order ID: " + rs.getInt("id") +
                                " | Date: " + rs.getDate("order_date") +
                                " | Total: " + rs.getDouble("total_amount")
                );
            }

        } catch (SQLException e) {
            System.out.println("Lỗi tìm đơn hàng: " + e.getMessage());
        }
    }
}
