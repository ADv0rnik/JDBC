package by.basnet.irb;

import java.sql.*;

public class Runner {
    static final String JDBC_DRIVER = "sun.jdbc.odbc.JdbcOdbcDriver";
    static final String DB_URL = "jdbc:odbc:coordinates";

    static final String USER = "";
    static final String PASS = "";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;

        try{
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql = "SELECT id, x1, x2 FROM coordinates";
            ResultSet rs = stmt.executeQuery(sql);

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Connection error: " + e.toString());
        }

    }
}
