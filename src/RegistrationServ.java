

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
@WebServlet("/RegistrationServ")
public class RegistrationServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String secretKey = "boooooooooom!!!!";
	private static String salt = "ssshhhhhhhhhhh!!!!";
	int id;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Error: ");

		PrintWriter out=response.getWriter();
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String fname=request.getParameter("fname");
		String lname=request.getParameter("lname");
		String email=request.getParameter("email");
		String address=request.getParameter("address");
		String state=request.getParameter("state");
		String zipcode=request.getParameter("zipcode");
		String country=request.getParameter("country");
		String gender=request.getParameter("gender");
		String promo=request.getParameter("promo");
		String pnumber=request.getParameter("pnumber");
		String active= "inactive";
		boolean unique = true;
		password = encrypt(password, secretKey);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/registrationConfirmation.html");

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

					out.println("<font color=red>Username has already been taken </font>");
					rd.include(request, response);
				} else if(rs.getString("email").equalsIgnoreCase(email)) {
					unique = false;
					//throw new SQLException("Email is already in use.");

					out.println("<font color=red>Email is already in use.</font>");
					rd.include(request, response);
				}
			}
			if(unique) {
				while(rs.next())
					id++;
				int userid = id;
				stmt.executeUpdate("insert into users values('"+id+"','"+username+"','"+password+"','"+fname+"','"+lname+"','"+email+"','"+address+"','"+state+"','"+zipcode+"','"+country+"','"+pnumber+"','"+gender+"','"+promo+"','"+0+"','"+active+"')");
				//stmt.executeUpdate("insert into payment values('"+0+"','"+userid+"','"+0+"','"+0+"','"+0+"','"+0+"','"+0+"','"+0+"')");
			}
		} catch (Exception p) {
			out.print(p);
		}

		response.getWriter().append("Served at: ").append(request.getContextPath());
		String uname = "miguelangello96@gmail.com";
		String pword = "pchepwzyepkhdoig";

		Properties p = new Properties();
		p.put("mail.smtp.host", "smtp.gmail.com");
		p.put("mail.smtp.port", "587");
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.starttls.enable", "true");
		System.out.println("Setup server");

		Session session = Session.getInstance(p, new javax.mail.Authenticator(){
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication("miguelangello96@gmail.com","pchepwzyepkhdoig");
			}//PassAuth
		});//mailAuth
		System.out.println("Creating email session");

		try
		{
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("miguelangello96@gmail.com"));
			msg.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("hussainsqadri@gmail.com")
			);
			msg.setSubject("NotAMC Theatre Confirmation Message");
			msg.setText("Congrats on signing up with our amazing Blockbuster theatre.");

			System.out.println("Sending email");

			Transport.send(msg);

			System.out.println("Complete");

			rd.include(request, response);
		}

		catch(Exception e)
		{
			e.printStackTrace();
		}

	}//main
//class





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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
