package dao;

import database.MySqlConnector;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.UserData;
import model.Appointment;

public class TechnicianDao {

    private final MySqlConnector mysql = new MySqlConnector();

    public void initTable() {
        Connection conn = mysql.openConnection();
        try {
            String sql = "CREATE TABLE IF NOT EXISTS technicians (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "username VARCHAR(50) NOT NULL," +
                    "email VARCHAR(100) NOT NULL UNIQUE," +
                    "password VARCHAR(100) NOT NULL," +
                    "address VARCHAR(255)," +
                    "otp INT," +
                    "emp_id VARCHAR(30)," +
                    "specialization VARCHAR(100)," +
                    "is_verified BOOLEAN DEFAULT FALSE," +
                    "status VARCHAR(20) DEFAULT 'pending'," +
                    "account_status VARCHAR(20) DEFAULT 'active'," +
                    "certification_path VARCHAR(500)," +
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
        ensureAccountStatusColumn();
    }

    private void ensureAccountStatusColumn() {
        Connection conn = mysql.openConnection();
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("ALTER TABLE technicians ADD COLUMN account_status VARCHAR(20) DEFAULT 'active'");
        } catch (Exception e) {
            // Column already exists - safe to ignore.
        } finally {
            mysql.closeConnection(conn);
        }
    }

    public void createTechnician(UserData user) {
        Connection conn = mysql.openConnection();
        try {
            String sql = "INSERT INTO technicians(username, email, password, address, emp_id, specialization) " +
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

    public void createTechnicianWithCert(UserData user, String certPath) {
        Connection conn = mysql.openConnection();
        try {
            String sql = "INSERT INTO technicians(username, email, password, specialization, " +
                    "emp_id, certification_path, status, is_verified) " +
                    "VALUES(?, ?, ?, ?, ?, ?, 'pending', FALSE)";
            try (PreparedStatement pstm = conn.prepareStatement(sql)) {
                pstm.setString(1, user.getUsername());
                pstm.setString(2, user.getEmail());
                pstm.setString(3, user.getPassword());
                pstm.setString(4, user.getSpecialization());
                pstm.setString(5, generateEmpId());
                pstm.setString(6, certPath);
                pstm.executeUpdate();
                System.out.println("Technician application submitted.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            mysql.closeConnection(conn);
        }
    }

    public boolean isVerified(String email) {
        Connection conn = mysql.openConnection();
        try {
            String sql = "SELECT is_verified FROM technicians WHERE email = ?";
            try (PreparedStatement pstm = conn.prepareStatement(sql)) {
                pstm.setString(1, email);
                ResultSet rs = pstm.executeQuery();
                if (rs.next())
                    return rs.getBoolean("is_verified");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            mysql.closeConnection(conn);
        }
        return false;
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
            String sql = "SELECT * FROM technicians WHERE email = ? AND password = ? AND is_verified = TRUE";
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

    public boolean updateProfile(int techId, String username, String email, String password) {
        Connection conn = mysql.openConnection();
        try {
            String sql = "UPDATE technicians SET username = ?, email = ?, password = ? WHERE id = ?";
            try (PreparedStatement pstm = conn.prepareStatement(sql)) {
                pstm.setString(1, username);
                pstm.setString(2, email);
                pstm.setString(3, password);
                pstm.setInt(4, techId);
                return pstm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            System.out.println("Error updating profile: " + e.getMessage());
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
                if (rs.next())
                    return rs.getString("username");
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
                if (rs.next())
                    return rs.getInt("id");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            mysql.closeConnection(conn);
        }
        return -1;
    }

    public String getSpecializationByEmail(String email) {
        Connection conn = mysql.openConnection();
        try {
            String sql = "SELECT specialization FROM technicians WHERE email = ?";
            try (PreparedStatement pstm = conn.prepareStatement(sql)) {
                pstm.setString(1, email);
                ResultSet rs = pstm.executeQuery();
                if (rs.next())
                    return rs.getString("specialization");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            mysql.closeConnection(conn);
        }
        return "";
    }

    public String getCertificationPathById(int id) {
        Connection conn = mysql.openConnection();
        try {
            String sql = "SELECT certification_path FROM technicians WHERE id = ?";
            try (PreparedStatement pstm = conn.prepareStatement(sql)) {
                pstm.setInt(1, id);
                ResultSet rs = pstm.executeQuery();
                if (rs.next())
                    return rs.getString("certification_path");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            mysql.closeConnection(conn);
        }
        return null;
    }

    public UserData getTechnicianById(int id) {
        Connection conn = mysql.openConnection();
        try {
            String sql = "SELECT * FROM technicians WHERE id = ?";
            try (PreparedStatement pstm = conn.prepareStatement(sql)) {
                pstm.setInt(1, id);
                ResultSet rs = pstm.executeQuery();
                if (rs.next()) {
                    UserData u = new UserData();
                    u.setId(rs.getInt("id"));
                    u.setUsername(rs.getString("username"));
                    u.setEmail(rs.getString("email"));
                    u.setSpecialization(rs.getString("specialization"));
                    u.setCertificationPath(rs.getString("certification_path"));
                    return u;
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            mysql.closeConnection(conn);
        }
        return null;
    }

    public List<UserData> getAllTechnicians() {
        return getTechniciansBySpecialization(null);
    }

    public List<UserData> getTechniciansBySpecialization(String specialization) {
        List<UserData> list = new ArrayList<>();
        Connection conn = mysql.openConnection();
        try {
            String sql = specialization == null
                    ? "SELECT * FROM technicians"
                    : "SELECT * FROM technicians WHERE specialization = ?";
            try (PreparedStatement pstm = conn.prepareStatement(sql)) {
                if (specialization != null)
                    pstm.setString(1, specialization);
                ResultSet rs = pstm.executeQuery();
                while (rs.next()) {
                    UserData u = new UserData();
                    u.setId(rs.getInt("id"));
                    u.setUsername(rs.getString("username"));
                    u.setEmail(rs.getString("email"));
                    u.setSpecialization(rs.getString("specialization"));
                    list.add(u);
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            mysql.closeConnection(conn);
        }
        return list;
    }

    public List<Appointment> getPendingJobs(String specialization) {
        List<Appointment> list = new ArrayList<>();
        Connection conn = mysql.openConnection();
        try {
            String sql = "SELECT a.*, u.username AS client_name " +
                    "FROM appointments a " +
                    "JOIN users u ON a.client_id = u.id " +
                    "WHERE a.service_type = ? AND a.status = 'pending'";
            try (PreparedStatement pstm = conn.prepareStatement(sql)) {
                pstm.setString(1, specialization);
                ResultSet rs = pstm.executeQuery();
                while (rs.next()) {
                    Appointment a = new Appointment();
                    a.setId(rs.getInt("id"));
                    a.setClientId(rs.getInt("client_id"));
                    a.setClientName(rs.getString("client_name"));
                    a.setServiceType(rs.getString("service_type"));
                    a.setDate(rs.getString("date"));
                    a.setTime(rs.getString("time"));
                    a.setAddress(rs.getString("address"));
                    a.setNotes(rs.getString("notes"));
                    a.setStatus(rs.getString("status"));
                    list.add(a);
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            mysql.closeConnection(conn);
        }
        return list;
    }

    public List<Appointment> getJobHistory(int technicianId) {
        List<Appointment> list = new ArrayList<>();
        Connection conn = mysql.openConnection();
        try {
            String sql = "SELECT a.*, u.username AS client_name " +
                    "FROM appointments a " +
                    "JOIN users u ON a.client_id = u.id " +
                    "WHERE a.technician_id = ? AND a.status IN ('accepted','completed')";
            try (PreparedStatement pstm = conn.prepareStatement(sql)) {
                pstm.setInt(1, technicianId);
                ResultSet rs = pstm.executeQuery();
                while (rs.next()) {
                    Appointment a = new Appointment();
                    a.setId(rs.getInt("id"));
                    a.setClientId(rs.getInt("client_id"));
                    a.setClientName(rs.getString("client_name"));
                    a.setServiceType(rs.getString("service_type"));
                    a.setDate(rs.getString("date"));
                    a.setTime(rs.getString("time"));
                    a.setAddress(rs.getString("address"));
                    a.setStatus(rs.getString("status"));
                    list.add(a);
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            mysql.closeConnection(conn);
        }
        return list;
    }

    public boolean acceptJob(int appointmentId, int technicianId) {
        Connection conn = mysql.openConnection();
        try {
            String sql = "UPDATE appointments SET status = 'accepted', technician_id = ? " +
                    "WHERE id = ? AND status = 'pending'";
            try (PreparedStatement pstm = conn.prepareStatement(sql)) {
                pstm.setInt(1, technicianId);
                pstm.setInt(2, appointmentId);
                return pstm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }

    public boolean declineJob(int appointmentId, int technicianId) {
        Connection conn = mysql.openConnection();
        try {
            String sql = "INSERT INTO declined_jobs (appointment_id, technician_id) VALUES (?, ?)";
            try (PreparedStatement pstm = conn.prepareStatement(sql)) {
                pstm.setInt(1, appointmentId);
                pstm.setInt(2, technicianId);
                return pstm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }

    public boolean completeJob(int appointmentId, int technicianId) {
        Connection conn = mysql.openConnection();
        try {
            String sql = "UPDATE appointments SET status = 'completed' " +
                    "WHERE id = ? AND technician_id = ?";
            try (PreparedStatement pstm = conn.prepareStatement(sql)) {
                pstm.setInt(1, appointmentId);
                pstm.setInt(2, technicianId);
                return pstm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
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

    public boolean approveTechnician(int techId) {
        Connection conn = mysql.openConnection();
        try {
            String sql = "UPDATE technicians SET is_verified = TRUE, status = 'approved' WHERE id = ?";
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

    public boolean rejectTechnician(int techId) {
        Connection conn = mysql.openConnection();
        try {
            String sql = "UPDATE technicians SET status = 'rejected' WHERE id = ?";
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

    public List<UserData> getPendingTechnicians() {
        List<UserData> list = new ArrayList<>();
        Connection conn = mysql.openConnection();
        try {
            String sql = "SELECT * FROM technicians WHERE status = 'pending'";
            try (PreparedStatement pstm = conn.prepareStatement(sql)) {
                ResultSet rs = pstm.executeQuery();
                while (rs.next()) {
                    UserData u = new UserData();
                    u.setId(rs.getInt("id"));
                    u.setUsername(rs.getString("username"));
                    u.setEmail(rs.getString("email"));
                    u.setSpecialization(rs.getString("specialization"));
                    u.setCertificationPath(rs.getString("certification_path"));
                    list.add(u);
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            mysql.closeConnection(conn);
        }
        return list;
    }

    public List<UserData> getApprovedTechnicians() {
        List<UserData> list = new ArrayList<>();
        Connection conn = mysql.openConnection();
        try {
            String sql = "SELECT * FROM technicians WHERE status = 'approved'";
            try (PreparedStatement pstm = conn.prepareStatement(sql)) {
                ResultSet rs = pstm.executeQuery();
                while (rs.next()) {
                    UserData u = new UserData();
                    u.setId(rs.getInt("id"));
                    u.setUsername(rs.getString("username"));
                    u.setEmail(rs.getString("email"));
                    u.setSpecialization(rs.getString("specialization"));
                    u.setAccountStatus(rs.getString("account_status"));
                    list.add(u);
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            mysql.closeConnection(conn);
        }
        return list;
    }

    public boolean updateAccountStatus(int techId, String accountStatus) {
        Connection conn = mysql.openConnection();
        try {
            String sql = "UPDATE technicians SET account_status = ? WHERE id = ?";
            try (PreparedStatement pstm = conn.prepareStatement(sql)) {
                pstm.setString(1, accountStatus);
                pstm.setInt(2, techId);
                return pstm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            System.out.println("Error updating account status: " + e.getMessage());
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }
}