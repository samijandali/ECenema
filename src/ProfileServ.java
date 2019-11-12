import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.



		servlet.http.*;
import java.sql.*;

@WebServlet("/ProfileServ")

public class ProfileServ extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{

		PrintWriter out = res.getWriter();
		HttpSession session =req.getSession(false);
		String username=(String)session.getAttribute("user");
		String address=(String)session.getAttribute("address");
		String zipcode=(String)session.getAttribute("zipcode");
		String state=(String)session.getAttribute("state");
		String country=(String)session.getAttribute("country");
		String pnumber=(String)session.getAttribute("pnumber");
		String fname=(String)session.getAttribute("fname");
		String lname=(String)session.getAttribute("lname");

		out.print("<!DOCTYPE html>");
		out.print("<html lang=\"en\">");
		out.print("<head>");
		out.print("<meta charset=\"utf-8\">");
		out.print("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">");
		out.print("<meta name=\"description\" content=\"\">");
		out.print("<meta name=\"keywords\" content=\"\">");
		out.print("<title>Account Information</title>");
		out.print("<link href=\"https://mono.flatheme.net/assets/images/favicon.png\" rel=\"shortcut icon\">");
		out.print("<link href=\"https://mono.flatheme.net/assets/plugins/bootstrap/bootstrap.min.css\" rel=\"stylesheet\">");
		out.print("<link href=\"https://mono.flatheme.net/assets/plugins/owl-carousel/owl.carousel.min.css\" rel=\"stylesheet\">");
		out.print("<link href=\"https://mono.flatheme.net/assets/plugins/owl-carousel/owl.theme.default.min.css\" rel=\"stylesheet\">");
		out.print("<link href=\"https://mono.flatheme.net/assets/plugins/magnific-popup/magnific-popup.min.css\" rel=\"stylesheet\">");
		out.print("<link href=\"https://mono.flatheme.net/assets/css/app.min.css\" rel=\"stylesheet\">");
		out.print("<link href=\"https://mono.flatheme.net/assets/plugins/font-awesome/css/all.css\" rel=\"stylesheet\">");
		out.print("<link href=\"https://mono.flatheme.net/assets/plugins/themify/themify-icons.min.css\" rel=\"stylesheet\">");
		out.print("</head>");
		try
		{
			Class.forName("com.mysql.jdbc.Driver");

			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/movieSite","root", "asdasd");//"UN", "PW"

			out.print("<body data-preloader=\"2\">");
			out.print("<header>");
			out.print("<nav class=\"navbar\">");
			out.print("<div class=\"container\">");
			out.print("<a class=\"navbar-brand\" href=\"https://mono.flatheme.net/\">");
			out.print("<h5>NotAMC Theatres</h5>");
			out.print("</a>");
			out.print("<ul class=\"list-horizontal-unstyled\">");
			out.print("<li class=\"nav-item\">");
			out.print("<a style=\"white-space:pre\" class=\"nav-link\" href=\"userHome.html\">Home</a>");
			out.print("</li>");
			out.print("<li class=\"nav-item\">");
			out.print("<a class=\"nav-link\" href=\"Logout.html\">Logout</a>");
			out.print("</li>");
			out.print("<li class=\"nav-item\">");
			out.print("<a class=\"nav-link\" href=\"./ProfileServ\">Profile</a>");
			out.print("</li>");
			out.print("</ul>");
			out.print("</div>");
			out.print("</nav>");
			out.print("</header>");
			out.print("<div class=\"section\">");
			out.print("<div class=\"container\">");
			out.print("<div class=\"row\">");
			out.print("<div class=\"col-12 col-lg-8\">");
			out.print("<div class=\"return-login\">");
			out.print("<div class=\"row\">");
			out.print("<div class=\"col-12 col-sm-6\">");
			out.print("<label>Email</label>");
			out.print("<input type=\"email\" name=\"email\">");
			out.print("</div>");
			out.print("<div class=\"col-12 col-sm-6\">");
			out.print("<label>Password</label>");
			out.print("<input type=\"password\" name=\"pw\">");
			out.print("</div>");
			out.print("</div>");
			out.print("<button class=\"button button-lg button-dark\">Login</button>");
			out.print("</div>");
			out.print("<h4 class=\"margin-bottom-20\">Your Profile</h4>");
			out.print("<br></br>");
			out.print("<h4 class=\"font-weight-light margin-bottom-30\">Personal Information</h4>");
			out.print("<div class=\"form-row\">");
			out.print("<div class=\"col\">");

			out.print("<label>Username: </label>");
			out.print(" ");

			out.print(username);
			out.print("</div>");
			out.print("</div>");
			out.print("<div class=\"form-row\">");
			out.print("<div class=\"col\">");
			out.print("<label>First Name: </label>");
			out.print(" ");
			out.print(fname);
			//<!--Print First Name-->");
			out.print("</div>");
			out.print("<div class=\"col\">");
			out.print("<label>Last Name: </label>");
			out.print(" ");
			out.print(lname);
			// <!--Print Last Name  -->
			out.print("</div>");
			out.print("</div>");

			out.print("<div>");
			out.print("<div class=\"form-row\">");
			out.print("<div class=\"col\">");
			out.print("<label>Address: </label>");
			out.print(" ");
			out.print(address);
			out.print("</div>");
			out.print("<div class=\"col\">");
			out.print("<label>State: </label>");
			out.print(" ");

			out.print(state);
			out.print("</div>");
			out.print("</div>");

			out.print("<div class=\"form-row\">");
			out.print("<div class=\"col\">");
			out.print("<label>Zip Code: </label>");
			out.print(" ");

			out.print(zipcode);
			out.print("</div>");
			out.print("<div class=\"col\">");
			out.print("<label>Country: </label>");
			out.print(" ");

			out.print(country);
			out.print("</div>");

			out.print("</div>");
			out.print("<div>");
			out.print("<label>Phone Number: </label>");
			out.print(" ");
			out.print(pnumber);
			out.print("</div>");
			out.print("<br></br>");

			//	<!-- billing info -->
			out.print("<h4 class=\"font-weight-light margin-bottom-30\">Billing Information</h4>");
			out.print("<div>");
			out.print("<div class=\"form-row\">");
			out.print("<div class=\"col\">");
			out.print("<label>Address: </label>");
			out.print(" ");

			//  <!-- Print Baddress -->
			out.print("</div>");
			out.print("<div class=\"col\">");
			out.print("<label>State: </label>");
			out.print(" ");

			//	<!--  Print BState-->
			out.print("</div>");
			out.print("</div>");

			out.print("<div class=\"form-row\">");
			out.print("<div class=\"col\">");
			out.print("<label>Zip Code:</label>");
			out.print(" ");

			//								<!--Print Bzip  -->
			out.print("</div>");
			out.print("<div class=\"col\">");
			out.print("<label>Country: </label>");
			out.print(" ");

			//            <!--  Print Bcountry-->
			out.print("</div>");

			out.print("</div>");
			out.print("<br></br>");

			out.print("<a href= \"editProfile.html\"> Edit Profile</a>");

			out.print("</div>");
			out.print("<div>");
			out.print("</div>");

			out.print("</div>");
			out.print("</div>");
			out.print("<footer>");
			out.print("</footer>");
			con.close();
		}
		catch (Exception e)
		{
			out.println("error");

		}

		out.print("<script src=\"https://mono.flatheme.net/assets/plugins/jquery.min.js\"></script>");
		out.print("<script src=\"https://mono.flatheme.net/assets/plugins/bootstrap/popper.min.js\"></script>");
		out.print("<script src=\"https://mono.flatheme.net/assets/plugins/bootstrap/bootstrap.min.js\"></script>");
		out.print("<script src=\"https://mono.flatheme.net/assets/plugins/appear.min.js\"></script>");
		out.print("<script src=\"https://mono.flatheme.net/assets/plugins/easing.min.js\"></script>");
		out.print("<script src=\"https://mono.flatheme.net/assets/plugins/retina.min.js\"></script>");
		out.print("<script src=\"https://mono.flatheme.net/assets/plugins/countdown.min.js\"></script>");
		out.print("<script src=\"https://mono.flatheme.net/assets/plugins/imagesloaded.pkgd.min.js\"></script>");
		out.print("<script src=\"https://mono.flatheme.net/assets/plugins/isotope.pkgd.min.js\"></script>");
		out.print("<script src=\"https://mono.flatheme.net/assets/plugins/jarallax/jarallax.min.js\"></script>");
		out.print("<script src=\"https://mono.flatheme.net/assets/plugins/jarallax/jarallax-video.min.js\"></script>");
		out.print("<script src=\"https://mono.flatheme.net/assets/plugins/magnific-popup/magnific-popup.min.js\"></script>");
		out.print("<script src=\"https://mono.flatheme.net/assets/plugins/owl-carousel/owl.carousel.min.js\"></script>");
		out.print("<script src=\"https://mono.flatheme.net/assets/plugins/jquery.easypiechart.min.js\"></script>");
		out.print("<script src=\"https://maps.googleapis.com/maps/api/js?key=AIzaSyBUma4oJ7_6VbfGNdUYdv6VQ0Ph07Fz0k8\"></script>");
		out.print("<script src=\"https://mono.flatheme.net/assets/plugins/gmaps.min.js\"></script>");
		out.print("<script src=\"https://mono.flatheme.net/assets/js/functions.min.js\"></script>");
		out.print("</body>");
		out.print("</html>");

	}
}