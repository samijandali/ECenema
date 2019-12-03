package model;

import java.sql.*;
import java.util.ArrayList;

public class PromotionService extends Promotion{
    private int id;
    private String name;
    private int discount;
    private String exp;
    private String description;


    public Promotion getByName(String name) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite","root", "asdasd");//"UN", "PW"
        Statement stmt=con.createStatement();
        ResultSet resultSet=stmt.executeQuery("Select * from promotion WHERE name='"+name+"'");
        while(resultSet.next()) {
            id = resultSet.getInt(1);
            name = resultSet.getString(2);
            discount = resultSet.getInt(3);
            exp = resultSet.getString(4);
            description = resultSet.getString(5);
        }
        return new Promotion(id, name, discount, exp, description);

    }

    public ArrayList<Promotion> getAllPromotions() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite","root", "asdasd");//"UN", "PW"
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("Select * from promotion");
        ArrayList<Promotion> promo = new ArrayList<>();
        while(rs.next()) {
            promo.add(new Promotion(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5)));
        }
        return promo;
    }
        public int getNumPromo() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite","root", "asdasd");//"UN", "PW"
        Statement stmt=con.createStatement();
        ResultSet resultSet=stmt.executeQuery("Select COUNT(*) from promotion");
        int num = 0;
        while(resultSet.next()){
            num = resultSet.getInt(1);
        }
        return num;
    }
    public boolean exists(String title) throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite","root", "asdasd");//"UN", "PW"
        Statement stmt=con.createStatement();
        ResultSet resultSet=stmt.executeQuery("Select COUNT(*) from promotion where name='"+name+"'");
        return !resultSet.next();
    }
}
