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

@WebServlet("/suspendServ")
public class suspendServ extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String urlWithQueryString = request.getRequestURL().append("").append(request.getQueryString()).toString();
        String suspend = "1";
        String unsuspend = "0";
        String email = request.getParameter("email");
        UserService userService = new UserService();
        HttpSession session = request.getSession();
        User user = new User();
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/manageUsers.jsp");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite?verifyServerCertificate=false&useSSL=true", "root", "asdasd");
            user = userService.getByEmail(email);
            String Query = "update users set suspend = ? where email='"+request.getParameter("email")+"'";
            PreparedStatement stmt = con.prepareStatement(Query);
            session.setAttribute("email",user);

            out.print(email + " and " + user.getEmail() + " And " + request.getAttribute("email"));

            if(request.getParameter("suspend").equals("0")) {
                stmt.setString(1, suspend);
            } else {
                stmt.setString(1, unsuspend);
            }

            int success = stmt.executeUpdate();
            if(success==1){
                out.println("<font color=red>Change was successful</font>");
                rd.include(request,response);
            } else {
                out.println("<font color=red>Change was unsuccessful</font>");
                rd.include(request,response);
        }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
