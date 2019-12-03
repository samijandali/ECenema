package model;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class PromotionService extends Promotion{
    private int id;
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
    public boolean exists(String promoCode) throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite","root", "asdasd");//"UN", "PW"
        Statement stmt=con.createStatement();
        ResultSet resultSet=stmt.executeQuery("Select * from promotion where name='"+promoCode+"'");
        return resultSet.next();
    }

    public int getIDByName(String promoCode) throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite","root", "asdasd");//"UN", "PW"
        Statement stmt=con.createStatement();
        ResultSet resultSet=stmt.executeQuery("Select id from promotion where name='"+promoCode+"'");
        if(resultSet.next()){
            return resultSet.getInt(1);
        }
        else return 0;
    }

    public double getDiscount(String promoCode) throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite","root", "asdasd");//"UN", "PW"
        Statement stmt=con.createStatement();
        ResultSet resultSet=stmt.executeQuery("Select discount from promotion where name='"+promoCode+"'");
        if(resultSet.next()){
            return resultSet.getDouble(1);
        }
        else return 0;
    }

    public boolean expired(String promoCode) throws ClassNotFoundException, SQLException, ParseException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite","root", "asdasd");//"UN", "PW"
        Statement stmt=con.createStatement();
        ResultSet resultSet=stmt.executeQuery("Select exp from promotion where name='"+promoCode+"'");
        String exp = "";
        if(resultSet.next()){
            exp = resultSet.getString(1);
        }
        LocalDate todayLocalDate = LocalDate.now();
        String[] dateSep = exp.split("/");
        int one = Integer.parseInt(dateSep[0]);
        int two = Integer.parseInt(dateSep[1]);
        LocalDate anotherLocalDate = LocalDate.of(2019, one, two);

        return compareLocalDates(todayLocalDate, anotherLocalDate);
    }
    private static boolean compareLocalDates(LocalDate todayLocalDate, LocalDate pastLocalDate)
    {
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date1 = sdf.format(todayLocalDate);
        String date2 = sdf.format(pastLocalDate);

        return todayLocalDate.isAfter(pastLocalDate);

    }

}
