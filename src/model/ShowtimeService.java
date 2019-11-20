package model;

import java.sql.*;
import java.util.ArrayList;

public class ShowtimeService {
    private int id;
    private int dayID;
    private int showroomID;
    private int timeID;
    private int movieID;

    public ArrayList<Showtime> getAllShowtimes() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite","root", "asdasd");//"UN", "PW"
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("Select * from showtime");
        ArrayList<Showtime> showtimes = new ArrayList<>();
        while(rs.next()) {
            showtimes.add(new Showtime(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5)));
        }
        return showtimes;
    }

    public boolean exists(String title) throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite","root", "asdasd");//"UN", "PW"
        Statement stmt = con.createStatement();
        ResultSet resultSet = stmt.executeQuery("SELECT showtime.id, showroom.name, timeslot.time FROM showtime " +
                "INNER JOIN showroom ON showtime.showroomID = showroom.id " +
                "INNER JOIN timeslot ON showtime.timeID = timeslot.id" +
                "INNER JOIN day ON showtime.dayID = day.id" +
                "WHERE movie.title = '"+title+"'");
        return resultSet.next();
    }


}
