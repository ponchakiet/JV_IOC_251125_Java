package ra.ponchakiet.dao.impl;

import ra.ponchakiet.dao.IAdminDao;
import ra.ponchakiet.model.Admin;
import ra.ponchakiet.utils.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDaoImpl implements IAdminDao {
    @Override
    public Admin findAdminByUsername(String username) {
        String sql = "SELECT * FROM ADMIN WHERE USERNAME=?";
        try (
                Connection conn = ConnectionDB.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Admin(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
