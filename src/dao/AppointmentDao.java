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
                         "(client_id, service_type, date, time, notes, address, status, payment_method) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, appointment.getClientId());
            pstm.setString(2, appointment.getServiceType());
            pstm.setString(3, appointment.getDate());
            pstm.setString(4, appointment.getTime());
            pstm.setString(5, appointment.getNotes());
            pstm.setString(6, appointment.getAddress());
            pstm.setString(7, appointment.getStatus());
            pstm.setString(8, appointment.getPaymentMethod());
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

    public java.util.List<Appointment> getAppointmentsByUser(int clientId) {
        java.util.List<Appointment> list = new java.util.ArrayList<>();
        Connection conn = mysql.openConnection();
        try {
            String sql = "SELECT * FROM appointments WHERE client_id = ? ORDER BY created_at DESC";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, clientId);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
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
                list.add(a);
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
                list.add(a);
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
                list.add(a);
            }
        } catch (Exception e) {
            System.out.println("Error fetching appointments by service: " + e.getMessage());
        } finally {
            mysql.closeConnection(conn);
        }
        return list;
    }
}