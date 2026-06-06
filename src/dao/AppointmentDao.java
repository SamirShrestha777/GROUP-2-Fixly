package dao;

import database.MySqlConnector;
import java.sql.*;
import model.Appointment;

public class AppointmentDao {

    private final MySqlConnector mysql = new MySqlConnector();

    public void createTable() {

        Connection conn = mysql.openConnection();

        try {

            String sql =
                "CREATE TABLE IF NOT EXISTS appointments (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "email VARCHAR(100)," +
                "date VARCHAR(50)," +
                "time VARCHAR(50)," +
                "notes TEXT," +
                "address TEXT" +
                ")";

            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);

        } catch (Exception e) {

            System.out.println(e.getMessage());

        } finally {

            mysql.closeConnection(conn);
        }
    }
    public boolean saveAppointment(Appointment appointment) {
    Connection conn = mysql.openConnection();
    try {
        String sql = "INSERT INTO appointments (email, date, time, notes, address) " +
                     "VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, appointment.getEmail());
        pstm.setString(2, appointment.getDate());
        pstm.setString(3, appointment.getTime());
        pstm.setString(4, appointment.getNotes());
        pstm.setString(5, appointment.getAddress());
        return pstm.executeUpdate() > 0;
    } catch (Exception e) {
        System.out.println(e.getMessage());
        return false;
    } finally {
        mysql.closeConnection(conn);
    }
}    public void createAppointment(Appointment appointment) {

    Connection conn = mysql.openConnection();

    try {

        String sql =
            "INSERT INTO appointments" +
            "(email,date,time,notes,address)" +
            "VALUES(?,?,?,?,?)";

        PreparedStatement pstm =
            conn.prepareStatement(sql);

        pstm.setString(1, appointment.getEmail());
        pstm.setString(2, appointment.getDate());
        pstm.setString(3, appointment.getTime());
        pstm.setString(4, appointment.getNotes());
        pstm.setString(5, appointment.getAddress());

        pstm.executeUpdate();

    } catch(Exception e) {

        System.out.println(e.getMessage());

    } finally {

        mysql.closeConnection(conn);
    }
}
}