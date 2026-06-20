package dao;

import database.MySqlConnector;
import java.sql.*;

public class HireDao {
    private final MySqlConnector mysql = new MySqlConnector();

    /** Hire a technician. Silently ignores if already hired (UNIQUE KEY). */
    public boolean hireTechnician(int clientId, int technicianId) {
        Connection conn = mysql.openConnection();
        try {
            String sql = "INSERT IGNORE INTO hires (client_id, technician_id) VALUES (?, ?)";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, clientId);
            pstm.setInt(2, technicianId);
            return pstm.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error hiring technician: " + e.getMessage());
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }

    /** Check if client has already hired this technician. */
    public boolean isAlreadyHired(int clientId, int technicianId) {
        Connection conn = mysql.openConnection();
        try {
            String sql = "SELECT COUNT(*) FROM hires WHERE client_id = ? AND technician_id = ?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, clientId);
            pstm.setInt(2, technicianId);
            ResultSet rs = pstm.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (Exception e) {
            System.out.println("Error checking hire: " + e.getMessage());
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }
}
