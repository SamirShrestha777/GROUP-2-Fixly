package dao;

import database.MySqlConnector;
import java.sql.*;

public class AdminDao {

    private final MySqlConnector mysql = new MySqlConnector();

    private static final String ADMIN_ID = "fixlynepal@gmail.com";
    private static final String ADMIN_PASSWORD = "fixly@69";

    public void initTable() {
        Connection conn = mysql.openConnection();
        try {
            String sql =
                "CREATE TABLE IF NOT EXISTS admin_logs (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "action VARCHAR(255)," +
                "performed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ")";
            try (Statement stmt = conn.createStatement()) {
                stmt.executeUpdate(sql);
                System.out.println("Admin table ready.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            mysql.closeConnection(conn);
        }
    }

    // Only login — no creation
    public boolean loginAdmin(String adminId, String password) {
        return ADMIN_ID.equals(adminId) && ADMIN_PASSWORD.equals(password);
    }

    // Log admin actions to DB
    public void logAction(String action) {
        Connection conn = mysql.openConnection();
        try {
            String sql = "INSERT INTO admin_logs(action) VALUES(?)";
            try (PreparedStatement pstm = conn.prepareStatement(sql)) {
                pstm.setString(1, action);
                pstm.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println("Error logging action: " + e.getMessage());
        } finally {
            mysql.closeConnection(conn);
        }
    }

    // Get all users
    public java.util.List<String[]> getAllUsers() {
        java.util.List<String[]> list = new java.util.ArrayList<>();
        Connection conn = mysql.openConnection();
        try {
            String sql = "SELECT id, username, email, address FROM users";
            try (Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    list.add(new String[]{
                        rs.getString("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("address")
                    });
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            mysql.closeConnection(conn);
        }
        return list;
    }

    // Get all technicians
    public java.util.List<String[]> getAllTechnicians() {
        java.util.List<String[]> list = new java.util.ArrayList<>();
        Connection conn = mysql.openConnection();
        try {
            String sql = "SELECT id, username, email, specialization FROM technicians";
            try (Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    list.add(new String[]{
                        rs.getString("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("specialization")
                    });
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            mysql.closeConnection(conn);
        }
        return list;
    }

    // Get all bookings
    public java.util.List<String[]> getAllBookings() {
        java.util.List<String[]> list = new java.util.ArrayList<>();
        Connection conn = mysql.openConnection();
        try {
            String sql =
                "SELECT b.id, u.username as customer, t.username as technician, " +
                "b.service_type as service, b.address, b.date, b.status " +
                "FROM appointments b " +
                "JOIN users u ON b.client_id = u.id " +
                "JOIN technicians t ON b.technician_id = t.id";
            try (Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    list.add(new String[]{
                        rs.getString("id"),
                        rs.getString("customer"),
                        rs.getString("technician"),
                        rs.getString("service"),
                        rs.getString("address"),
                        rs.getString("date"),
                        rs.getString("status")
                    });
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            mysql.closeConnection(conn);
        }
        return list;
    }

    // Delete a user
    public boolean deleteUser(int userId) {
        Connection conn = mysql.openConnection();
        try {
            String sql = "DELETE FROM users WHERE id = ?";
            try (PreparedStatement pstm = conn.prepareStatement(sql)) {
                pstm.setInt(1, userId);
                return pstm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }

    // Delete a technician
    public boolean deleteTechnician(int techId) {
        Connection conn = mysql.openConnection();
        try {
            String sql = "DELETE FROM technicians WHERE id = ?";
            try (PreparedStatement pstm = conn.prepareStatement(sql)) {
                pstm.setInt(1, techId);
                return pstm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }
}