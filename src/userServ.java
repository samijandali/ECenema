import model.Movie;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;

@WebServlet("/userServ")
public class userServ extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite?verifyServerCertificate=false&useSSL=true", "root", "asdasd");
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from users");
            HttpSession session = request.getSession();
            ArrayList<User> users = new ArrayList<>();
            rs.beforeFirst();
            if(!rs.next()){
                session.setAttribute("userlist", users);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/manageUsers.jsp");
                rd.include(request, response);
            } else {
                do {
                users.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4), rs.getString(5), rs.getString(6),rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getInt(14), rs.getString(15), rs.getInt(16)));
                    session.setAttribute("userlist", users);
                } while(rs.next());

            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/manageUsers.jsp");
        rd.include(request, response);
}
}
