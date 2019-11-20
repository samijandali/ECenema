package model;

import java.sql.*;
import java.util.ArrayList;

public class ShowroomService extends Showroom {
    private int id;
    private String name;
    private int nbOfSeats;


    public ArrayList<Showroom> getAllShowRooms() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite","root", "asdasd");//"UN", "PW"
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("Select * from showroom");
        ArrayList<Showroom> showrooms = new ArrayList<>();
        while(rs.next()) {
            showrooms.add(new Showroom(rs.getInt(1), rs.getString(2), rs.getInt(3)));
        }
        return showrooms;
}
}