package dao;

import database.MySqlConnector;
import java.sql.*;
import model.UserData;

public class TechnicianDao {

    private final MySqlConnector mysql = new MySqlConnector();

    public void initTable() {
        Connection conn = mysql.openConnection();
        try {
            String sql =
                "CREATE TABLE IF NOT EXISTS technicians (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "username VARCHAR(50) NOT NULL," +
                "email VARCHAR(100) NOT NULL UNIQUE," +
                "password VARCHAR(100) NOT NULL," +
                "address VARCHAR(255)," +
                "otp INT," +
                "emp_id VARCHAR(30)," +
                "specialization VARCHAR(100)," +
                "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ")";
            try (Statement stmt = conn.createStatement()) {
                stmt.executeUpdate(sql);
                System.out.println("Technicians table ready.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            mysql.closeConnection(conn);
        }
    }

    public void createTechnician(UserData user) {
        Connection conn = mysql.openConnection();
        try {
            String sql =
                "INSERT INTO technicians(username, email, password, address, emp_id, specialization) " +
                "VALUES(?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstm = conn.prepareStatement(sql)) {
                pstm.setString(1, user.getUsername());
                pstm.setString(2, user.getEmail());
                pstm.setString(3, user.getPassword());
                pstm.setString(4, user.getAddress());
                pstm.setString(5, generateEmpId());
                pstm.setString(6, user.getSpecialization());
                pstm.executeUpdate();
                System.out.println("Technician created.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            mysql.closeConnection(conn);
        }
    }

    public boolean checkTechnician(String email) {
        Connection conn = mysql.openConnection();
        try {
            String sql = "SELECT * FROM technicians WHERE email = ?";
            try (PreparedStatement pstm = conn.prepareStatement(sql)) {
                pstm.setString(1, email);
                return pstm.executeQuery().next();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }

    public boolean loginTechnician(String email, String password) {
        Connection conn = mysql.openConnection();
        try {
            String sql = "SELECT * FROM technicians WHERE email = ? AND password = ?";
            try (PreparedStatement pstm = conn.prepareStatement(sql)) {
                pstm.setString(1, email);
                pstm.setString(2, password);
                return pstm.executeQuery().next();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }

    public boolean resetPassword(String email, String newPassword) {
        Connection conn = mysql.openConnection();
        try {
            String sql = "UPDATE technicians SET password = ?, otp = NULL WHERE email = ?";
            try (PreparedStatement pstm = conn.prepareStatement(sql)) {
                pstm.setString(1, newPassword);
                pstm.setString(2, email);
                return pstm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }

    public String getUsernameByEmail(String email) {
        Connection conn = mysql.openConnection();
        try {
            String sql = "SELECT username FROM technicians WHERE email = ?";
            try (PreparedStatement pstm = conn.prepareStatement(sql)) {
                pstm.setString(1, email);
                ResultSet rs = pstm.executeQuery();
                if (rs.next()) return rs.getString("username");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            mysql.closeConnection(conn);
        }
        return "";
    }

    public int getIdByEmail(String email) {
        Connection conn = mysql.openConnection();
        try {
            String sql = "SELECT id FROM technicians WHERE email = ?";
            try (PreparedStatement pstm = conn.prepareStatement(sql)) {
                pstm.setString(1, email);
                ResultSet rs = pstm.executeQuery();
                if (rs.next()) return rs.getInt("id");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            mysql.closeConnection(conn);
        }
        return -1;
    }

    public String generateEmpId() {
        Connection conn = mysql.openConnection();
        try {
            String sql = "SELECT COUNT(*) FROM technicians";
            try (PreparedStatement pstm = conn.prepareStatement(sql)) {
                ResultSet rs = pstm.executeQuery();
                if (rs.next()) {
                    int count = rs.getInt(1) + 1;
                    return "FIXLY-TCH-" + String.format("%03d", count);
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            mysql.closeConnection(conn);
        }
        return "FIXLY-TCH-001";
    }
}