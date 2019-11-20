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
@WebServlet("/addShowtime")
public class addShowtime extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String showroom = request.getParameter("showroom");
        String day = request.getParameter("day");
        String time = request.getParameter("time");
        ShowtimeService showtimeService = new ShowtimeService();
        PrintWriter out=response.getWriter();
        try {
            if(showtimeService.exists(title)){
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/addShowtime.html");
                out.println("<font color=red>Showtime already exists for another movie, please pick another</font>");
                rd.include(request, response);
            }

            // Check if show day time all exist at the same time
            // Check if movie exist
            //if not then add the shit

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/addShowtime.jsp");
        rd.include(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
