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

@WebServlet("/editoProfilo")
public class editoProfilo extends HttpServlet {

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
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String username = user.getUsername();
		Statement statement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite", "root", "asdasd");//"UN", "PW"
			statement = con.createStatement();
			statement.addBatch("UPDATE users SET fname = '"+fname+"', lname = '"+lname+"', address = '"+address+"', state = '"+state+"', zipcode = '"+zipcode+"', country = '"+country+"', pnumber = '"+pnumber+"' WHERE username = '"+username+"'");
			statement.addBatch("UPDATE payment SET ");
			int[] success = statement.executeBatch();
			if (success[0] == 1 && success[1] == 1) {
				out.println("<font color=red>Movie edited successfully</font>");
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/manageMovies.jsp");
				rd.include(request, response);
				return;
			}
			out.println("<font color=red>Movie not edited successfully, please retry</font>");
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/manageMovies.jsp");
			rd.include(request, response);
		}
		catch(Exception e) {
			out.print(e);
		}
	}
}
