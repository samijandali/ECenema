package model;

import java.sql.*;
import java.util.ArrayList;

public class ShowtimeService {
    private int id;
    private int dayID;
    private int showroomID;
    private int timeID;
    private int movieID;

    public ArrayList<String[]> getAllShowtimes(String title) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite","root", "asdasd");//"UN", "PW"
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("SELECT showroom.name, timeslot.time, day.day FROM showtime " +
                "INNER JOIN showroom ON showtime.showroomID = showroom.id " +
                "INNER JOIN timeslot ON showtime.timeID = timeslot.id " +
                "INNER JOIN day ON showtime.dayID = day.id " +
                "INNER JOIN movie ON showtime.movieID = movie.id " +
                "WHERE movie.title = '"+title+"'");
        ArrayList<String[]> showtimes = new ArrayList<>();
        while(rs.next()) {
            String[] stringo = {rs.getString(1), rs.getString(2), rs.getString(3)};
            showtimes.add(stringo);
        }
        return showtimes;
    }

    public ArrayList<Integer> getAllIDs(String title) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite","root", "asdasd");//"UN", "PW"
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("SELECT showtime.id FROM showtime " +
                "INNER JOIN movie ON showtime.movieID = movie.id " +
                "WHERE movie.title = '"+title+"'");
        ArrayList<Integer> ids = new ArrayList<>();
        while(rs.next()) {
            ids.add(rs.getInt(1));
        }
        return ids;
    }

    public boolean exists(String name, String day, String time) throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite","root", "asdasd");//"UN", "PW"
        Statement stmt = con.createStatement();
        ResultSet resultSet = stmt.executeQuery("\n" +
                "SELECT day.id, showroom.id, timeslot.id FROM showtime INNER JOIN day ON showtime.dayID = day.id " +
                "INNER JOIN showroom ON showtime.showroomID = showroom.id " +
                "INNER JOIN movie ON showtime.movieID = movie.id " +
                "INNER JOIN timeslot ON showtime.timeID = timeslot.id " +
                "WHERE timeslot.time = '"+time+"' AND showroom.name ='"+name+"' AND day.day = '"+day+"' ");
        //ResultSet resultSet = stmt.executeQuery("SELECT showtime.id, day.day, showroom.name, timeslot.time FROM showtime INNER JOIN day ON showtime.dayID = day.id INNER JOIN showroom ON showtime.showroomID = showroom.id INNER JOIN movie ON showtime.movieID = movie.id INNER JOIN timeslot ON showtime.timeID = timeslot.id");
        return resultSet.next();
    }


}
