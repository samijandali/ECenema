import model.MovieService;
import model.User;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.spec.KeySpec;
import java.sql.*;
import java.util.Base64;

/**
 * Servlet implementation class Servlet1
 */
@WebServlet("/addcard")
public class addcard extends HttpServlet {
    private static String secretKey = "boooooooooom!!!!";
    private static String salt = "ssshhhhhhhhhhh!!!!";
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
            String cardi = request.getParameter("newcard");
            String cvvi = request.getParameter("cvv");
            String expi = request.getParameter("exp");
            String address = request.getParameter("newadd");
            String state = request.getParameter("newstate");
            String zipcode = request.getParameter("newzip");
            String country = request.getParameter("newcoun");
            int cvv = Integer.parseInt(cvvi);
            int exp = Integer.parseInt(expi);
            cardi = encrypt(cardi, secretKey);
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            String username = user.getUsername();
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite", "root", "asdasd");//"UN", "PW"
                Statement stmt = con.createStatement();
                int success = stmt.executeUpdate("INSERT INTO payment (userID, exp, cvv, address, state, zipcode, country, cardNo) values ((SELECT id from users where username = '"+username+"'), "+exp+","+cvv+",'"+address+"','"+state+"','"+zipcode+"','"+country+"','"+cardi+"')");
                if (success == 1) {
                    out.println("<font color=red>Card added successfully</font>");
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/edito.jsp");
                    rd.include(request, response);
                    return;
                }
                out.println("<font color=red>Card not added successfully, please retry</font>");
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/edito.jsp");
                rd.include(request, response);
            } catch (Exception p) {
                out.print(p);
            }


    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);

    }
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

}
