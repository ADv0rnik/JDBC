package by.basnet.irb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Runner {
    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/mysql";
    static final String USER = "root";
    static final String PASS = "root";

    public static void main(String[] args) {
        Connection conn;
        Statement stmt;
        List<Segment> list = new ArrayList<>();
        try {
            System.out.println("Connecting database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Сonnection is established");
            stmt = conn.createStatement();
            //1
            try {
                String sql = "SELECT ROUND(ABS((x1 - x2)+0.5)) AS len, Count(*) AS num FROM coordinates.coord GROUP BY ROUND(ABS((x1 - x2)+0.5)) ORDER BY ROUND(ABS((x1 - x2)+0.5)) ASC";
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    int len = (int) rs.getDouble("len");
                    int num = rs.getInt("num");
                    Segment segment = new Segment(len, num);
                    list.add(segment);
                }
                System.out.println("SQL query:");
                for (Segment segment : list)
                    System.out.println(segment);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Wrong column index:" + e.toString());
            } catch (SQLException e) {
                System.out.println("Wrong SQL query : " + e.toString());
            } finally {
                try {
                    if (stmt != null)
                        stmt.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            //2
            try{
                stmt = conn.createStatement();
                stmt.executeUpdate("TRUNCATE TABLE coordinates.frequencies");
                PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO coordinates.frequencies(len, num) VALUES (?, ?)");
                for (Segment segment : list) {
                    preparedStatement.setString(1, String.valueOf(segment.getLen()));
                    preparedStatement.setString(2, String.valueOf(segment.getNum()));
                    preparedStatement.addBatch();
                }
                preparedStatement.executeBatch();

            } catch (Exception e) {
                System.out.println(e.toString());
            } finally {
                try {
                    if (stmt != null)
                        stmt.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            //3
            try{
                stmt = conn.createStatement();
                ResultSet resultSet = stmt.executeQuery("SELECT len, num FROM coordinates.frequencies WHERE len > num");
                System.out.println("Record found:");
                while (resultSet.next()){
                    String len = resultSet.getString(1);
                    String num = resultSet.getString(2);
                    System.out.println(len + ";" + num);
                }
            }catch (SQLSyntaxErrorException e) {
                System.out.println("SQL syntax error:" + e.toString());
            } finally {
                try {
                    if (stmt != null)
                        stmt.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}