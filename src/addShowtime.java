import model.Movie;
import model.MovieService;

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

/**
 * Servlet implementation class Servlet1
 */
@WebServlet("/addShowtime")
public class addShowtime extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String showroom = request.getParameter("showroom");
        String day = request.getParameter("day");
        String time = request.getParameter("time");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite","root", "asdasd");//"UN", "PW"
            Statement stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery("Select * from showtime WHERE showroom='"+showroom+"'");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        // Check if show day time all exist at the same time
        // Check if movie exist
        //if not then add the shit
        HttpSession session = request.getSession();
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/addShowtime.jsp");
        rd.include(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
