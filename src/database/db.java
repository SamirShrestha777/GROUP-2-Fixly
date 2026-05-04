package database;

import java.sql.Connection;
import java.sql.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Omne
 */
public interface db {
    Connection openConnection();
    void closeConnection(Connection conn);
    ResultSet runQuery(Connection conn, String Query);
    int executeUpdate(Connection conn, String Query);

}
