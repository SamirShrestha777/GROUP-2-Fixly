package dao;

import database.MySqlConnector;
import java.sql.*;
import model.UserData;

public class UserDao {

    private final MySqlConnector mysql = new MySqlConnector();

    public void createUser(UserData user) {
        Connection conn = mysql.openConnection();
        try {
            // Step 1: Ensure table exists (now includes address)
            String createSql = "CREATE TABLE IF NOT EXISTS users (" +
                               "id INT AUTO_INCREMENT PRIMARY KEY," +
                               "username VARCHAR(50) NOT NULL," +
                               "email VARCHAR(100) NOT NULL UNIQUE," +
                               "password VARCHAR(100) NOT NULL," +
                               "address VARCHAR(255)," +
                               "otp INT" +
                               ")";
            try (Statement stmt = conn.createStatement()) {
                stmt.executeUpdate(createSql);
            }

           
            String insertSql = "INSERT INTO users(username, email, password, address) VALUES(?, ?, ?, ?)";
            try (PreparedStatement pstm = conn.prepareStatement(insertSql)) {
                pstm.setString(1, user.getUsername());
                pstm.setString(2, user.getEmail());
                pstm.setString(3, user.getPassword());
                pstm.setString(4, user.getAddress());
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
                return rs.next(); // returns true if email already exists
            }
        } catch (Exception e) {
            System.out.println("Error in checkUser: " + e.getMessage());
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }
    public boolean resetPassword(String email, String newPassword) {
    Connection conn = mysql.openConnection();
    try {
        String sql = "UPDATE users SET password = ?, otp = NULL WHERE email = ?";
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
    public boolean loginUser(UserData user) {
    Connection conn = mysql.openConnection();
    try {
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
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
    public void initTable() {
    Connection conn = mysql.openConnection();
    try {
        String createSql = "CREATE TABLE IF NOT EXISTS users (" +
                           "id INT AUTO_INCREMENT PRIMARY KEY," +
                           "username VARCHAR(50) NOT NULL," +
                           "email VARCHAR(100) NOT NULL UNIQUE," +
                           "password VARCHAR(100) NOT NULL," +
                           "address VARCHAR(255)," +
                           "otp INT" +
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
}