package model;

import java.sql.*;
import java.util.ArrayList;

public class DayService {
    private int id;
    private int day;

    public ArrayList<Day> getAllDays() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite","root", "asdasd");//"UN", "PW"
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("Select * from day");
        ArrayList<Day> days = new ArrayList<>();
        while(rs.next()) {
            days.add(new Day(rs.getInt(1), rs.getString(2)));
        }
        return days;
    }
}

