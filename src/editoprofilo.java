import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

@WebServlet("/editoprofilo")
public class editoprofilo extends HttpServlet {
	private static String secretKey = "boooooooooom!!!!";
	private static String salt = "ssshhhhhhhhhhh!!!!";
	private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		String fname=request.getParameter("fname");
		String lname=request.getParameter("lname");
		String password=request.getParameter("newpass");
		String address=request.getParameter("address");
		String state=request.getParameter("state");
		String country=request.getParameter("country");
		String zipcode=request.getParameter("zipcode");
		String caddress=request.getParameter("caddress");
		String cstate=request.getParameter("cstate");
		String ccountry=request.getParameter("ccountry");
		String czipcode=request.getParameter("czipcode");
		int cvv=Integer.valueOf(request.getParameter("cvv"));
		int exp=Integer.valueOf(request.getParameter("exp"));
		int cardno=Integer.valueOf(request.getParameter("cardno"));
		String promo=request.getParameter("promo");
		RequestDispatcher rd = request.getRequestDispatcher("userHome.html");

		HttpSession session=request.getSession();
		boolean flag=false;
		//I need to write the JDBC code here for connecting to mysql
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite","root", "asdasd");//"UN", "PW"
			Statement stmt=con.createStatement();
			String user =(String) session.getAttribute("username");
			stmt.executeUpdate("update users set fname='"+fname+"' where username='"+user+"'");
			if(!lname.isEmpty()) {
			stmt.executeUpdate("update users set lname='"+lname+"' where username='"+user+"'");
			}
			if(!address.isEmpty()) {
				stmt.executeUpdate("update users set address='"+address+"' where username='"+user+"'");
			}
			if(!state.isEmpty()) {
				stmt.executeUpdate("update users set state='"+state+"' where username='"+user+"'");
			}
			if(!zipcode.isEmpty()) {
				stmt.executeUpdate("update users set zipcode='"+zipcode+"' where username='"+user+"'");
			}
			if(!country.isEmpty()) {
				stmt.executeUpdate("update users set country='"+country+"' where username='"+user+"'");
			}
			if(!caddress.isEmpty()) {
				stmt.executeUpdate("update users set address='"+caddress+"' where username='"+user+"'");
			}
			if(!cstate.isEmpty()) {
				stmt.executeUpdate("update users set state='"+cstate+"' where username='"+user+"'");
			}
			if(!czipcode.isEmpty()) {
				stmt.executeUpdate("update users set zipcode='"+czipcode+"' where username='"+user+"'");
			}
			if(!ccountry.isEmpty()) {
				stmt.executeUpdate("update users set country='"+ccountry+"' where username='"+user+"'");
			}
//insert into payment (cardNo,userID,exp,cvv,address,state,zipcode,country) values
//(1, 1,1234,123,"abc","as","31","sa")
//			if(cvv != 0) {
//				stmt.executeUpdate("update users set cvv="+cvv+" where username='"+user+"'");
//			}
//			if(cardno != 0) {
//				stmt.executeUpdate("update users set cardno="+cardno+" where username='"+user+"'");
//			}
//			if(exp != 0) {
//				stmt.executeUpdate("update users set exp="+exp+" where username='"+user+"'");
//			}
			//hello test
			rd.include(request, response);
						
		}
		catch(Exception e){
			out.print(e);
		}
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}

}
