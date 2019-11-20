package model;

import java.sql.*;
import java.util.ArrayList;

public class TimeService {
    private int id;
    private int time;

    public ArrayList<Time> getAllTimes() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite","root", "asdasd");//"UN", "PW"
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("Select * from timeslot");
        ArrayList<Time> times = new ArrayList<>();
        while(rs.next()) {
            times.add(new Time(rs.getInt(1), rs.getString(2)));
        }
        return times;
    }
}
