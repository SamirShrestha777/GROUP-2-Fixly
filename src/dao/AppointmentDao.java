package dao;

import database.MySqlConnector;
import java.sql.*;
import model.Appointment;

public class AppointmentDao {
    private final MySqlConnector mysql = new MySqlConnector();

    public boolean saveAppointment(Appointment appointment) {
        Connection conn = mysql.openConnection();
        try {
            String sql = "INSERT INTO appointments " +
                         "(client_id, service_type, date, time, notes, address, status, payment_method, technician_id) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, appointment.getClientId());
            pstm.setString(2, appointment.getServiceType());
            pstm.setString(3, appointment.getDate());
            pstm.setString(4, appointment.getTime());
            pstm.setString(5, appointment.getNotes());
            pstm.setString(6, appointment.getAddress());
            pstm.setString(7, appointment.getStatus());
            pstm.setString(8, appointment.getPaymentMethod());
            if (appointment.getTechnicianId() > 0) {
                pstm.setInt(9, appointment.getTechnicianId());
            } else {
                pstm.setNull(9, java.sql.Types.INTEGER);
            }
            boolean result = pstm.executeUpdate() > 0;
            if (result) System.out.println("Appointment saved successfully.");
            return result;
        } catch (Exception e) {
            System.out.println("Error saving appointment: " + e.getMessage());
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }

    public boolean hasPendingDirectHire(int clientId, int technicianId) {
        Connection conn = mysql.openConnection();
        try {
            String sql = "SELECT COUNT(*) FROM appointments WHERE client_id = ? AND technician_id = ? AND status IN ('pending', 'accepted')";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, clientId);
            pstm.setInt(2, technicianId);
            ResultSet rs = pstm.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (Exception e) {
            System.out.println("Error checking direct hire: " + e.getMessage());
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }

    public boolean submitPaymentProof(int appointmentId, String screenshotPath) {
        Connection conn = mysql.openConnection();
        try {
            String sql = "UPDATE appointments SET payment_proof_path = ?, status = 'payment_submitted' WHERE id = ?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, screenshotPath);
            pstm.setInt(2, appointmentId);
            boolean result = pstm.executeUpdate() > 0;
            if (result) System.out.println("Payment proof submitted successfully.");
            return result;
        } catch (Exception e) {
            System.out.println("Error submitting payment proof: " + e.getMessage());
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }

    public java.util.List<Appointment> getAppointmentsByStatus(String status) {
        java.util.List<Appointment> list = new java.util.ArrayList<>();
        Connection conn = mysql.openConnection();
        try {
            String sql = "SELECT * FROM appointments WHERE status = ? ORDER BY created_at DESC";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, status);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (Exception e) {
            System.out.println("Error fetching appointments by status: " + e.getMessage());
        } finally {
            mysql.closeConnection(conn);
        }
        return list;
    }

    public boolean updateAppointmentStatus(int appointmentId, String status) {
        Connection conn = mysql.openConnection();
        try {
            String sql = "UPDATE appointments SET status = ? WHERE id = ?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, status);
            pstm.setInt(2, appointmentId);
            return pstm.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error updating appointment status: " + e.getMessage());
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }

    public String getPaymentProofPathById(int appointmentId) {
        Connection conn = mysql.openConnection();
        try {
            String sql = "SELECT payment_proof_path FROM appointments WHERE id = ?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, appointmentId);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                return rs.getString("payment_proof_path");
            }
        } catch (Exception e) {
            System.out.println("Error fetching payment proof path: " + e.getMessage());
        } finally {
            mysql.closeConnection(conn);
        }
        return null;
    }

    public java.util.List<Appointment> getAppointmentsByUser(int clientId) {
        java.util.List<Appointment> list = new java.util.ArrayList<>();
        Connection conn = mysql.openConnection();
        try {
            String sql = "SELECT * FROM appointments WHERE client_id = ? ORDER BY created_at DESC";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, clientId);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (Exception e) {
            System.out.println("Error fetching appointments: " + e.getMessage());
        } finally {
            mysql.closeConnection(conn);
        }
        return list;
    }

    public java.util.List<Appointment> getPendingAppointments() {
        java.util.List<Appointment> list = new java.util.ArrayList<>();
        Connection conn = mysql.openConnection();
        try {
            String sql = "SELECT * FROM appointments WHERE status = 'pending' ORDER BY created_at DESC";
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (Exception e) {
            System.out.println("Error fetching pending appointments: " + e.getMessage());
        } finally {
            mysql.closeConnection(conn);
        }
        return list;
    }

    public java.util.List<Appointment> getPendingAppointmentsByService(String serviceType) {
        java.util.List<Appointment> list = new java.util.ArrayList<>();
        Connection conn = mysql.openConnection();
        try {
            String sql = "SELECT * FROM appointments WHERE service_type = ? " +
                         "AND status = 'pending' ORDER BY created_at DESC";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, serviceType);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (Exception e) {
            System.out.println("Error fetching appointments by service: " + e.getMessage());
        } finally {
            mysql.closeConnection(conn);
        }
        return list;
    }

    public java.util.List<Appointment> getPendingAndAcceptedAppointments(int techId) {
        java.util.List<Appointment> list = new java.util.ArrayList<>();
        Connection conn = mysql.openConnection();
        try {
            String sql = "SELECT * FROM appointments WHERE status IN ('pending','accepted') AND (technician_id IS NULL OR technician_id = ?) ORDER BY created_at DESC";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, techId);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) { list.add(mapRow(rs)); }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally { mysql.closeConnection(conn); }
        return list;
    }

    public java.util.List<Appointment> getPendingAndAcceptedByService(String serviceType, int techId) {
        java.util.List<Appointment> list = new java.util.ArrayList<>();
        Connection conn = mysql.openConnection();
        try {
            String sql = "SELECT * FROM appointments WHERE service_type = ? AND status IN ('pending','accepted') AND (technician_id IS NULL OR technician_id = ?) ORDER BY created_at DESC";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, serviceType);
            pstm.setInt(2, techId);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) { list.add(mapRow(rs)); }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally { mysql.closeConnection(conn); }
        return list;
    }

    public java.util.List<Appointment> getAllAppointmentsByService(String serviceType) {
        java.util.List<Appointment> list = new java.util.ArrayList<>();
        Connection conn = mysql.openConnection();
        try {
            String sql = "SELECT * FROM appointments WHERE service_type = ? ORDER BY created_at DESC";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, serviceType);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) { list.add(mapRow(rs)); }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally { mysql.closeConnection(conn); }
        return list;
    }

    public java.util.List<Appointment> getPendingPayments() {
        java.util.List<Appointment> list = new java.util.ArrayList<>();
        Connection conn = mysql.openConnection();
        try {
            String sql = "SELECT * FROM appointments WHERE status IN ('payment_submitted', 'awaiting_payment') ORDER BY created_at DESC";
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) { list.add(mapRow(rs)); }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally { mysql.closeConnection(conn); }
        return list;
    }

    private Appointment mapRow(ResultSet rs) throws Exception {
        Appointment a = new Appointment();
        a.setId(rs.getInt("id"));
        a.setClientId(rs.getInt("client_id"));
        a.setServiceType(rs.getString("service_type"));
        a.setDate(rs.getString("date"));
        a.setTime(rs.getString("time"));
        a.setNotes(rs.getString("notes"));
        a.setAddress(rs.getString("address"));
        a.setStatus(rs.getString("status"));
        a.setPaymentMethod(rs.getString("payment_method"));
        // technician_id may be null if not yet accepted
        try { a.setTechnicianId(rs.getInt("technician_id")); } catch (Exception ignored) {}
        return a;
    }
}