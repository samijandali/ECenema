import controller.UserServlet;
import model.User;
import model.UserService;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.security.spec.KeySpec;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
/**
 * Servlet implementation class Servlet1
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static String secretKey = "boooooooooom!!!!";
	private static String salt = "ssshhhhhhhhhhh!!!!";
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		UserService userService = new UserService();
		HttpSession session = request.getSession();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite","root", "asdasd");//"UN", "PW"
			Statement stmt=con.createStatement();
			request.setAttribute("user", userService.getUser(userid));
			User trial = (User) request.getAttribute("user");
			if (encrypt(password, secretKey).equals(trial.getPassword())) {
				session.setAttribute("user", trial);
				if(0 == trial.getSuspended()) {
					if (0 == trial.getAdmin()) {
						stmt.executeUpdate("update users set activity='" + 1 + "' where username='" + trial.getUsername() + "'");
						session.setMaxInactiveInterval(10 * 60);
						response.sendRedirect("./index.jsp");
					} else if (1 == trial.getAdmin()) {
						stmt.executeUpdate("update users set activity='" + 1 + "' where username='" + trial.getUsername() + "'");
						session.setMaxInactiveInterval(10 * 60);
						response.sendRedirect("./adminPage.jsp");
					}
				} else {
					request.removeAttribute("user");
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login.html");
					out.println("<font color=red>User is suspended. Contact an admin for more information.</font>");
					rd.include(request, response);
				}
			}else{
				request.removeAttribute("user");
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login.html");
				out.println("<font color=red>Invalid Username or password, please re-enter information. </font>");
				rd.include(request, response);
			}
		} catch (Exception p) {
			out.print(p);
		}

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);

	}

	private static String encrypt(String strToEncrypt, String secret)
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
