import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/editoprofilo")
public class editoprofilo extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException {

		PrintWriter out=response.getWriter();
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String address = request.getParameter("address");
		String state = request.getParameter("state");
		String zipcode = request.getParameter("zipcode");
		String country = request.getParameter("country");
		String pnumber = request.getParameter("pnumber");
		String cardi = request.getParameter("paydef");
		long cardNo = Long.parseLong(cardi);
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String username = user.getUsername();
		Statement statement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite", "root", "asdasd");//"UN", "PW"
			statement = con.createStatement();
			statement.addBatch("UPDATE users SET fname = '"+fname+"', lname = '"+lname+"', address = '"+address+"', state = '"+state+"', zipcode = '"+zipcode+"', country = '"+country+"', pnumber = '"+pnumber+"' WHERE username = '"+username+"'");
			statement.addBatch("UPDATE payment A" +
					" INNER JOIN users RA" +
					" ON A.userID = RA.id" +
					" SET A.defPay = 0 WHERE RA.username = '"+username+"'");
			statement.addBatch("UPDATE payment A" +
					" INNER JOIN users RA" +
					" ON A.userID = RA.id" +
					" SET A.defPay = 1 WHERE A.cardNo = "+cardNo+"");
			int[] success = statement.executeBatch();
			if (success[0] == 1 && success[1] > 0 && success[2] == 1) {
				out.println("<font color=red>Profile edited successfully</font>");
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/edito.jsp");
				rd.include(request, response);
				return;
			}
			out.println("<font color=red>Profile not edited successfully, please retry</font>");
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/edito.jsp");
			rd.include(request, response);
		}
		catch(Exception e) {
			out.print(e);
		}
	}
}
