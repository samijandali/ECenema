import model.Movie;
import model.MovieService;
import model.ShowtimeService;

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
@WebServlet("/CreateSeats")
public class CreateSeats extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        int seats = (Integer) request.getAttribute("seats");
        int id = (Integer) request.getAttribute("showtimeID");
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite", "root", "asdasd");//"UN", "PW"
            Statement stmt = con.createStatement();
            for(int i = 1; i<=seats; i++){
                stmt.addBatch("INSERT INTO seat (showID, seatnb) VALUES ("+id+", "+i+")");
            }
            stmt.executeBatch();
            out.println("<font color=red> Showtime got added successfully</font>");
            RequestDispatcher rd = request.getRequestDispatcher("/addShowtime.jsp");
            rd.include(request, response);
            return;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
