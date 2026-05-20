package dao;

import database.MySqlConnector;
import java.sql.*;
import model.UserData;

public class UserDao {
    MySqlConnector mysql = new MySqlConnector();

      public void ensureUserTable() {
        Connection conn = mysql.openConnection();
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                     "id INT AUTO_INCREMENT PRIMARY KEY," +
                     "username VARCHAR(50) NOT NULL," +
                     "email VARCHAR(100) NOT NULL UNIQUE," +
                     "password VARCHAR(100) NOT NULL," +
                     "otp INT" +
                     ")";
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Users table ensured.");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            mysql.closeConnection(conn);
        }
    }

    public void createUser(UserData user) {
        Connection conn = mysql.openConnection();
        String sql = "INSERT INTO users(username,email,password) VALUES(?,?,?)";
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, user.getUsername());
            pstm.setString(2, user.getEmail());
            pstm.setString(3, user.getPassword());
            pstm.executeUpdate();
            System.out.println("User created successfully.");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            mysql.closeConnection(conn);
        }
    }
}
