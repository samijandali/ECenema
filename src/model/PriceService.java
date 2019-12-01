package model;

import java.sql.*;
import java.util.ArrayList;

public class PriceService {
    private int id;
    private int day;

    public ArrayList<Price> getPrices() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite","root", "asdasd");//"UN", "PW"
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("Select * from prices");
        ArrayList<Price> prices = new ArrayList<>();
        while(rs.next()) {
            prices.add(new Price(rs.getInt(1), rs.getString(2), rs.getInt(3)));
        }
        return prices;
    }
}


