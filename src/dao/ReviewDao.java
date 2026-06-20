package dao;

import database.MySqlConnector;
import model.Review;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewDao {
    private final MySqlConnector mysql = new MySqlConnector();

    /** Submit a review. Returns false if already reviewed (enforced by UNIQUE KEY). */
    public boolean submitReview(int appointmentId, int clientId, int technicianId, int rating, String comment) {
        Connection conn = mysql.openConnection();
        try {
            String sql = "INSERT INTO reviews (appointment_id, client_id, technician_id, rating, comment) " +
                         "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, appointmentId);
            pstm.setInt(2, clientId);
            pstm.setInt(3, technicianId);
            pstm.setInt(4, rating);
            pstm.setString(5, comment);
            return pstm.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error submitting review: " + e.getMessage());
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }

    /** Get all reviews for a technician, newest first. */
    public List<Review> getReviewsForTechnician(int technicianId) {
        List<Review> list = new ArrayList<>();
        Connection conn = mysql.openConnection();
        try {
            String sql = "SELECT r.*, u.username as client_name FROM reviews r " +
                         "LEFT JOIN users u ON r.client_id = u.id " +
                         "WHERE r.technician_id = ? ORDER BY r.created_at DESC";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, technicianId);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Review r = new Review();
                r.setId(rs.getInt("id"));
                r.setAppointmentId(rs.getInt("appointment_id"));
                r.setClientId(rs.getInt("client_id"));
                r.setTechnicianId(rs.getInt("technician_id"));
                r.setRating(rs.getInt("rating"));
                r.setComment(rs.getString("comment"));
                r.setCreatedAt(rs.getString("created_at"));
                r.setClientName(rs.getString("client_name"));
                list.add(r);
            }
        } catch (Exception e) {
            System.out.println("Error fetching reviews: " + e.getMessage());
        } finally {
            mysql.closeConnection(conn);
        }
        return list;
    }

    /** Check if client already reviewed this appointment. */
    public boolean hasReviewed(int appointmentId, int clientId) {
        Connection conn = mysql.openConnection();
        try {
            String sql = "SELECT COUNT(*) FROM reviews WHERE appointment_id = ? AND client_id = ?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, appointmentId);
            pstm.setInt(2, clientId);
            ResultSet rs = pstm.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (Exception e) {
            System.out.println("Error checking review: " + e.getMessage());
            return false;
        } finally {
            mysql.closeConnection(conn);
        }
    }

    /** Get average rating for a technician (0.0 if no reviews). */
    public double getAverageRating(int technicianId) {
        Connection conn = mysql.openConnection();
        try {
            String sql = "SELECT AVG(rating) FROM reviews WHERE technician_id = ?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, technicianId);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) return rs.getDouble(1);
        } catch (Exception e) {
            System.out.println("Error fetching avg rating: " + e.getMessage());
        } finally {
            mysql.closeConnection(conn);
        }
        return 0.0;
    }
}
