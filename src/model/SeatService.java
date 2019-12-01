package model;

import java.sql.*;
import java.util.ArrayList;

public class SeatService {
    private int id;
    private int day;

    public ArrayList<Seat> getAllShowSeats(int showID) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite","root", "asdasd");//"UN", "PW"
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("Select * from seat where showID = "+showID+"");
        ArrayList<Seat> seats = new ArrayList<>();
        while(rs.next()) {
            seats.add(new Seat(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4)));
        }
        return seats;
    }
}


