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
        String email = request.getParameter("email");
        UserService userService = new UserService();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite","root", "asdasd");//"UN", "PW"
            Statement stmt=con.createStatement();
            User user = userService.getByEmail(email);
            String query = "";
            if(user.getSuspended() == 0) {
                query = "update users set suspend = 1 where email ='"+email+"'";
            } else {
                query = "update users set suspend = 0 where email ='"+email+"'";
            }
            int success = stmt.executeUpdate(query);
            if(success==1){
                out.println("<font color=red>Change was successful</font>");
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/adminPage.jsp");
                rd.include(request, response);
            } else {
                out.println("<font color=red>Change was unsuccessful</font>");
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/adminPage.jsp");
                rd.include(request, response);
        }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
