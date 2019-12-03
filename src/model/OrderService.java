package model;

import java.sql.*;
import java.util.ArrayList;

public class OrderService {
    private int id;
    private int day;

    public ArrayList<String[]> getUserOrders(int id) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite","root", "asdasd");//"UN", "PW"
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("Select * from orderinfo where userID ="+id+"");
        ArrayList<String[]> orders = new ArrayList<>();
        ShowtimeService showtimeService = new ShowtimeService();
        while(rs.next()) {
            int total = rs.getInt(7) + rs.getInt(8) + rs.getInt(9) + rs.getInt(10);
            String[] stringo = {showtimeService.getMovie(rs.getInt(4)), rs.getString(3), String.valueOf(total), rs.getString(1)};
            orders.add(stringo);
        }
        return orders;
    }
    public boolean bookingExist(int movieId) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite","root", "asdasd");//"UN", "PW"
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("SELECT * FROM orderinfo " +
                "INNER JOIN showtime ON orderinfo.showtimeID = showtime.id " +
                "WHERE showtime.movieID = '"+movieId+"'");
        return rs.next();
    }
}

