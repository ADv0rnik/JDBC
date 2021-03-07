package by.basnet.irb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Runner {
    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/mysql";
    static final String USER = "root";
    static final String PASS = "root";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        List<Segment> list = new ArrayList<>();
        //1
        try{
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            String sql = "SELECT ROUND(ABS((x1 - x2)+0.5)) AS len, Count(*) AS num FROM coordinates.coord GROUP BY ROUND(ABS((x1 - x2)+0.5)) ORDER BY ROUND(ABS((x1 - x2)+0.5)) ASC";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                int len = (int) rs.getDouble("len");
                int num = rs.getInt("num");
                Segment segment = new Segment(len, num);
                list.add(segment);
            }
            for(Segment segment: list)
                System.out.println(segment);
        } catch (SQLException e) {
            System.out.println("Wrong SQL query : " + e.toString());
        } catch (IndexOutOfBoundsException e){
            System.out.println("Wrong column index:" + e.toString());
        }

    }
}
