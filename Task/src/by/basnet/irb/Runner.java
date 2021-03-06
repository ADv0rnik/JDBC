package by.basnet.irb;

import java.sql.*;

public class Runner {
    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/mysql";
    static final String USER = "root";
    static final String PASS = "root";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try{
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql = "SELECT ROUND(ABS((x1 - x2)+0.5)) AS len, Count(*) AS num FROM coord GROUP BY ROUND(ABS((x1 - x2)+0.5)) ORDER BY ROUND(ABS((x1 - x2)+0.5)) ASC";

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){

            }

        } catch (SQLException e) {
            System.out.println("Connection error: " + e.toString());
        }

    }
}
