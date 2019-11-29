package model;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.KeySpec;
import java.sql.*;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Base64;

public class PaymentService {
    private static String secretKey = "boooooooooom!!!!";
    private static String salt = "ssshhhhhhhhhhh!!!!";
    private static final long serialVersionUID = 1L;
    public ArrayList<Long> getAllPayments(String username) throws ClassNotFoundException, SQLException {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite","root", "asdasd");//"UN", "PW"
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT payment.cardNo FROM payment" +
                    " INNER JOIN users ON payment.userID = users.id" +
                    " WHERE users.username ='"+username+"'");
            ArrayList<Long> payments = new ArrayList<>();
            while(rs.next()) {
                long stringo = Long.parseLong(decrypt(rs.getString(1), secretKey));
                payments.add(stringo);
            }
            return payments;
        }
        private static String decrypt(String strToDecrypt, String secret) {
            try
            {
                byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
                IvParameterSpec ivspec;
                ivspec = new IvParameterSpec(iv);

                SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
                KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), 65536, 256);
                SecretKey tmp = factory.generateSecret(spec);
                SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
                cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
                return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
            }
            catch (Exception e) {
                System.out.println("Error while decrypting: " + e.toString());
            }
            return null;
        }
    }

