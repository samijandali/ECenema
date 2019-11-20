package model;

import java.sql.*;

public class ShowroomService extends Showroom {
    private int id;
    private String name;
    private int nbOfSeats;


    public int getAllShowRooms() throws ClassNotFoundException, SQLException {
    Class.forName("com.mysql.jdbc.Driver");
    Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite","root", "asdasd");//"UN", "PW"
    Statement stmt=con.createStatement();
    ResultSet resultSet=stmt.executeQuery("Select COUNT(*) from showroom");
    int num = 0;
    while(resultSet.next()){
        num = resultSet.getInt(1);
    }
    return num;
}
}