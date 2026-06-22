package dao;

import database.MySqlConnector;
import java.sql.*;
import model.UserData;

public class UserDao {

    private final MySqlConnector mysql = new MySqlConnector();

    public void createUser(UserData user) {
        Connection conn = mysql.openConnection();
        try {
            String createSql = "CREATE TABLE IF NOT EXISTS users (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "username VARCHAR(50) NOT NULL," +
                "email VARCHAR(100) NOT NULL UNIQUE," +
                "password VARCHAR(100) NOT NULL," +
                "address VARCHAR(255)," +
                "role VARCHAR(20) DEFAULT 'client'," +
                "specialization VARCHAR(100)," +
                "is_verified BOOLEAN DEFAULT FALSE" +
                ")";
            try (Statement stmt = conn.createStatement()) {
                stmt.executeUpdate(createSql);
            }

            String insertSql = "INSERT INTO users(username, email, password, address, role) " +
                               "VALUES(?, ?, ?, ?, ?)";
            try (PreparedStatement pstm = conn.prepareStatement(insertSql)) {
                pstm.setString(1, user.getUsername());
                pstm.setString(2, user.getEmail());
                pstm.setString(3, user.getPassword());
                pstm.setString(4, user.getAddress());
                pstm.setString(5, user.getRole() != null ? user.getRole() : "client");
                pstm.executeUpdate();
                System.out.println("User created successfully.");
            }

        } catch (Exception e) {
            System.out.println("Error in createUser: " + e.getMessage());
        } finally {
            mysql.closeConnection(conn);
        }
    }

    public boolean checkUser(UserData user) {
        Connection conn = mysql.openConnection();
        try {
            String sql = "SELECT * FROM users WHERE email = ?";
            try (PreparedStatement pstm = conn.prepareStatement(sql)) {
                pstm.setString(1, user.getEmail());
                ResultSet rs = pstm.executeQuery();
                return rs.next();
            }
        } catch (Exception e) {
            System.out.println("Error in checkUser: " + e.getMessage());
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }

    public boolean loginUser(UserData user) {
        Connection conn = mysql.openConnection();
        try {
            String sql = "SELECT * FROM users WHERE email = ? AND password = ? AND role = 'client'";
            try (PreparedStatement pstm = conn.prepareStatement(sql)) {
                pstm.setString(1, user.getEmail());
                pstm.setString(2, user.getPassword());
                ResultSet rs = pstm.executeQuery();
                return rs.next();
            }
        } catch (Exception e) {
            System.out.println("Error in loginUser: " + e.getMessage());
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }

    public boolean resetPassword(String email, String newPassword) {
        Connection conn = mysql.openConnection();
        try {
            String sql = "UPDATE users SET password = ? WHERE email = ?";
            try (PreparedStatement pstm = conn.prepareStatement(sql)) {
                pstm.setString(1, newPassword);
                pstm.setString(2, email);
                int rows = pstm.executeUpdate();
                return rows > 0;
            }
        } catch (Exception e) {
            System.out.println("Error resetting password: " + e.getMessage());
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }

    public boolean updateProfile(int userId, String username, String email, String password) {
        Connection conn = mysql.openConnection();
        try {
            String sql = "UPDATE users SET username = ?, email = ?, password = ? WHERE id = ?";
            try (PreparedStatement pstm = conn.prepareStatement(sql)) {
                pstm.setString(1, username);
                pstm.setString(2, email);
                pstm.setString(3, password);
                pstm.setInt(4, userId);
                return pstm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            System.out.println("Error updating profile: " + e.getMessage());
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }

    public UserData getUserById(int userId) {
        Connection conn = mysql.openConnection();
        try {
            String sql = "SELECT * FROM users WHERE id = ?";
            try (PreparedStatement pstm = conn.prepareStatement(sql)) {
                pstm.setInt(1, userId);
                ResultSet rs = pstm.executeQuery();
                if (rs.next()) {
                    UserData u = new UserData();
                    u.setId(rs.getInt("id"));
                    u.setUsername(rs.getString("username"));
                    u.setEmail(rs.getString("email"));
                    u.setPassword(rs.getString("password"));
                    u.setAddress(rs.getString("address"));
                    u.setRole(rs.getString("role"));
                    return u;
                }
            }
        } catch (Exception e) {
            System.out.println("Error getting user by id: " + e.getMessage());
        } finally {
            mysql.closeConnection(conn);
        }
        return null;
    }

    public String getUsernameByEmail(String email) {
        Connection conn = mysql.openConnection();
        try {
            String sql = "SELECT username FROM users WHERE email = ?";
            try (PreparedStatement pstm = conn.prepareStatement(sql)) {
                pstm.setString(1, email);
                ResultSet rs = pstm.executeQuery();
                if (rs.next()) {
                    return rs.getString("username");
                }
            }
        } catch (Exception e) {
            System.out.println("Error getting username: " + e.getMessage());
        } finally {
            mysql.closeConnection(conn);
        }
        return "";
    }

    public int getIdByEmail(String email) {
        Connection conn = mysql.openConnection();
        try {
            String sql = "SELECT id FROM users WHERE email = ?";
            try (PreparedStatement pstm = conn.prepareStatement(sql)) {
                pstm.setString(1, email);
                ResultSet rs = pstm.executeQuery();
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        } catch (Exception e) {
            System.out.println("Error getting user id: " + e.getMessage());
        } finally {
            mysql.closeConnection(conn);
        }
        return -1;
    }

    public void initTable() {
        Connection conn = mysql.openConnection();
        try {
            String createSql = "CREATE TABLE IF NOT EXISTS users (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "username VARCHAR(50) NOT NULL," +
                "email VARCHAR(100) NOT NULL UNIQUE," +
                "password VARCHAR(100) NOT NULL," +
                "address VARCHAR(255)," +
                "role VARCHAR(20) DEFAULT 'client'," +
                "specialization VARCHAR(100)," +
                "is_verified BOOLEAN DEFAULT FALSE" +
                ")";
            try (Statement stmt = conn.createStatement()) {
                stmt.executeUpdate(createSql);
                System.out.println("Table ready.");
            }
        } catch (Exception e) {
            System.out.println("Error creating table: " + e.getMessage());
        } finally {
            mysql.closeConnection(conn);
        }
    }
}