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
		PrintWriter out=response.getWriter();
		String userid=request.getParameter("userid");
		String password=request.getParameter("password");
		String address;
		String zipcode;
		String state;
		String country;
		String pnumber;
		String fname;
		String lname;
		String admin;
		HttpSession session=request.getSession();
		boolean flag=false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/movieSite","root", "asdasd");//"UN", "PW"
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("Select * from users");
			while(rs.next()) {

				if(userid.equals(rs.getString(2)) && password.equals(decrypt(rs.getString(3),secretKey))) {
					fname = rs.getString(4);
					lname = rs.getString(5);
					address = rs.getString(7);
					zipcode = rs.getString(9);
					state = rs.getString(8);
					country = rs.getString(10);
					pnumber = rs.getString(11);
					admin = rs.getString(14);
					session.setAttribute("fname", fname);
					session.setAttribute("lname", lname);
					session.setAttribute("username", userid);
					session.setAttribute("address", address);
					session.setAttribute("zipcode", zipcode);
					session.setAttribute("state", state);
					session.setAttribute("country", country);
					session.setAttribute("pnumber", pnumber);
					session.setAttribute("admin", admin);
					if("0".equals(rs.getString(14))){
						stmt.executeUpdate("update users set activity='"+1+"' where username='"+userid+"'");
						session.setMaxInactiveInterval(10*60);
						flag=true;
						response.sendRedirect("./index.jsp");
					} else if("1".contentEquals(rs.getString(14))) {
						session.setAttribute("admin", admin);
						stmt.executeUpdate("update users set activity='"+1+"' where username='"+userid+"'");
						session.setMaxInactiveInterval(10*60);
						flag=true;
						response.sendRedirect("./index.jsp");
					} else {
						System.out.println("Error message = " + userid);
						request.setAttribute("errMessage", userid);
						request.getRequestDispatcher("Login.html").forward(request, response);
						flag = true;
					}
				}
				if(flag==false) {
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login.html");
					out.println("<font color=red>Invalid Username or password, please re-enter information. </font>");
					rd.include(request, response);
					flag = true;
				}
			}
		}
		catch(Exception p){
			out.print(p);
		}

	}

	public static String decrypt(String strToDecrypt, String secret) {
		try
		{
			byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			IvParameterSpec ivspec = new IvParameterSpec(iv);

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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

	}

}
