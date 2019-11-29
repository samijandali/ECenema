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

@WebServlet("/ResetPassword")
public class ResetPassword extends HttpServlet {

    private static String secretKey = "boooooooooom!!!!";
    private static String salt = "ssshhhhhhhhhhh!!!!";
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        PrintWriter out=response.getWriter();
        String currentPass = request.getParameter("currentPass");
        String newPass = request.getParameter("newPass");
        String confirmPass = request.getParameter("confirmPass");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String password = user.getPassword();
        String username = user.getUsername();
        System.out.println(password);
        System.out.println(currentPass);
        if(!password.equals(encrypt(currentPass, secretKey))){
            out.println("<font color=red>Current entered password doesn't match actual password, try again</font>");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/resetPassword.jsp");
            rd.include(request, response);
        }else if(!newPass.equals(confirmPass)) {
            out.println("<font color=red>New password and confirm password don't match, try again</font>");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/resetPassword.jsp");
            rd.include(request, response);
        }else{
            newPass = encrypt(newPass, secretKey);
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite", "root", "asdasd");//"UN", "PW"
                String query = "UPDATE users SET password = ? WHERE username = ?";
                PreparedStatement stmt = con.prepareStatement(query);
                stmt.setString(1, newPass);
                stmt.setString(2, username);
                int success = stmt.executeUpdate();
                if (success == 1) {
                    out.println("<font color=red>Password updated successfully</font>");
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/edito.jsp");
                    rd.include(request, response);
                    return;
                }
                out.println("<font color=red>Password was not updated, please retry</font>");
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/edito.jsp");
                rd.include(request, response);
            } catch (Exception e) {
                out.print(e);
            }
        }
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
