package model;

import java.sql.*;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class PaymentService {
    public ArrayList<Long> getAllPayments(String username) throws ClassNotFoundException, SQLException {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite","root", "asdasd");//"UN", "PW"
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT payment.cardNo FROM payment" +
                    " INNER JOIN users ON payment.userID = users.id" +
                    " WHERE users.username ='"+username+"'");
            ArrayList<Long> payments = new ArrayList<>();
            while(rs.next()) {
                long stringo = rs.getLong(1);
                payments.add(stringo);
            }
            return payments;
        }
    }

