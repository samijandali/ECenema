package model;

import java.sql.*;
import java.util.ArrayList;

public class UserService extends User{
    private int id;
    private String username;
    private String password;
    private String fname;
    private String lname;
    private String email;
    private String address;
    private String state;
    private String zipcode;
    private String country;
    private String pnumber;
    private String gender;
    private String promo;
    private int admin;
    private String activity;
    private int suspended;

    public User getUser(String username) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite","root", "asdasd");//"UN", "PW"
        Statement stmt=con.createStatement();
        ResultSet resultSet=stmt.executeQuery("Select * from users WHERE username='"+username+"'");
        while(resultSet.next()) {
            id = resultSet.getInt(1);
            password = resultSet.getString(3);
            fname = resultSet.getString(4);
            lname = resultSet.getString(5);
            email = resultSet.getString(6);
            address = resultSet.getString(7);
            state = resultSet.getString(8);
            zipcode = resultSet.getString(9);
            country = resultSet.getString(10);
            pnumber = resultSet.getString(11);
            gender = resultSet.getString(12);
            promo = resultSet.getString(13);
            admin = resultSet.getInt(14);
            activity = resultSet.getString(15);
            suspended = resultSet.getInt(16);
        }
        return new User(id, username, password, fname, lname, email, address, state, zipcode, country, pnumber, gender, promo, admin, activity, suspended);
    }

    public User getByEmail(String email) throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite","root", "asdasd");//"UN", "PW"
        Statement stmt=con.createStatement();
        ResultSet resultSet=stmt.executeQuery("Select * from users WHERE email='"+email+"'");
        while(resultSet.next()) {
            id = resultSet.getInt(1);
            username = resultSet.getString(2);
            password = resultSet.getString(3);
            fname = resultSet.getString(4);
            lname = resultSet.getString(5);
            email = resultSet.getString(6);
            address = resultSet.getString(7);
            state = resultSet.getString(8);
            zipcode = resultSet.getString(9);
            country = resultSet.getString(10);
            pnumber = resultSet.getString(11);
            gender = resultSet.getString(12);
            promo = resultSet.getString(13);
            admin = resultSet.getInt(14);
            activity = resultSet.getString(15);
            suspended = resultSet.getInt(16);
        }
        return new User(id, username, password, fname, lname, email, address, state, zipcode, country, pnumber, gender, promo, admin, activity, suspended);
    }
    public int getNumUsers() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite","root", "asdasd");//"UN", "PW"
        Statement stmt=con.createStatement();
        ResultSet resultSet=stmt.executeQuery("Select COUNT(*) from users");
        int num = 0;
        while(resultSet.next()){
            num = resultSet.getInt(1);
        }
        return num;
    }

    public String[] getAllEmails() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite","root", "asdasd");//"UN", "PW"
        Statement stmt=con.createStatement();
        ResultSet resultSet=stmt.executeQuery("Select email from users");
        ArrayList<String> emails = new ArrayList<>();
        while(resultSet.next()){
            emails.add(resultSet.getString(1));
        }

        return emails.toArray(new String[emails.size()]);
    }
}
