import model.User;
import model.UserService;

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

@WebServlet("/Refund")
public class Refund extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		int orderid = Integer.parseInt(request.getParameter("orderid"));
		System.out.println(orderid);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Class.forName("com.mysql.jdbc.Driver");
			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite","root", "asdasd");//"UN", "PW"
			Statement stmt=con.createStatement();
			String query = "DELETE FROM orderinfo WHERE id = "+orderid+"";
			String query1 = "UPDATE seat set status = 'available' where orderID = " + orderid + "";
			String query2 = "UPDATE seat set orderID = 0 where orderID = " + orderid + "";
			stmt.addBatch(query);
			stmt.addBatch(query1);
			stmt.addBatch(query2);
			int[] success = stmt.executeBatch();
			if(success[0] == 1){
				out.println("<font color=red>Order was refunded successful</font>");
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
				rd.include(request, response);
			} else {
				out.println("<font color=red>Refund unsuccessful, contact admin pls</font>");
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
				rd.include(request, response);
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
