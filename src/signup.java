

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.spec.KeySpec;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Base64;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
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
import javax.servlet.http.HttpSession;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


/**
 * Servlet implementation class signup
 */
@WebServlet("/signup")
public class signup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static String secretKey = "boooooooooom!!!!";
	private static String salt = "ssshhhhhhhhhhh!!!!";

	protected String getSaltString() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 18) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String username = "miguelangello96@gmail.com";
		String password = "pchepwzyepkhdoig";
		String recipient = request.getParameter("email");
		String subject = "Your Password has been reset";
		String pword;
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login.html");

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
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite?verifyServerCertificate=false&useSSL=true", "root", "asdasd");
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from users");
			pword = getSaltString();
			stmt.executeUpdate("update users set password='"+encrypt(pword, secretKey)+"' where email='"+recipient+"'");
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("miguelangello96@gmail.com"));
			msg.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("hussainsqadri@gmail.com")
			);
			msg.setSubject("NotAMC Forgot Password");
			msg.setText("Hi, this is your new password: " +pword+
					". For security reasons, you must change your password after loggin in. ");

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
		// Recipient's email ID needs to be mentioned.


	}

}