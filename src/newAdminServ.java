

import java.io.IOException;
import java.io.PrintWriter;
import java.security.spec.KeySpec;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Base64;
import java.util.Properties;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegistrationServ
 */
@WebServlet("/newAdminServ")
public class newAdminServ extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String secretKey = "boooooooooom!!!!";
    private static String salt = "ssshhhhhhhhhhh!!!!";
    int id;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out=response.getWriter();
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String email=request.getParameter("email");
        String fname = "admin";
        String lname = "admin";
        String address = "123 admin str";
        String state = "ga";
        String zipcode = "12345";
        String country = "United States";
        String  pnumber = "123456587";
        String gender = "other";
        String promo = "0";
        int admin = 1;
        String active= "0";
        boolean unique = true;
        password = encrypt(password, secretKey);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/manageUsers.jsp");

        String myHash;
        Random random = new Random();
        random.nextInt(999999);


        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite?verifyServerCertificate=false&useSSL=true", "root", "asdasd");
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from users");
            while(rs.next()){
                if(rs.getString("username").equalsIgnoreCase(username)){
                    unique = false;

                    out.println("<font color=red>Username has already been taken</font>");
                    rd.include(request, response);
                } else if(rs.getString("email").equalsIgnoreCase(email)) {
                    unique = false;
                    //throw new SQLException("Email is already in use.");

                    out.println("<font color=red>Email is already in use</font>");
                    rd.include(request, response);
                }
            }
            if(unique) {
                while(rs.next())
                    id++;
                int userid = id;
                stmt.executeUpdate("INSERT INTO users (username, password, fname, lname, email, address, state, zipcode, country, pnumber, gender, promo, admin, activity) VALUES ('"+username+"','"+password+"','"+fname+"','"+lname+"','"+email+"','"+address+"','"+state+"','"+zipcode+"','"+country+"','"+pnumber+"','"+gender+"','"+promo+"','"+admin+"','"+active+"')");
                out.println("<font color=red>User Successfully Added!</font>");
                rd.include(request, response);
            }
        } catch (Exception p) {
            out.print(p);
        }

    }//main
    public static String encrypt(String strToEncrypt, String secret)
    {
        try
        {
            byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        }
        catch (Exception e)
        {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
