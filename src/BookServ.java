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
import java.sql.SQLException;
/**
 * Servlet implementation class Servlet1
 */
@WebServlet("/BookServ")
public class BookServ extends HttpServlet {
    private static String secretKey = "boooooooooom!!!!";
    private static String salt = "ssshhhhhhhhhhh!!!!";
    private static final long serialVersionUID = 1L;
    MovieService movieService = new MovieService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String urlWithQueryString = request.getRequestURL().append("").append(request.getQueryString()).toString();
        String title = urlWithQueryString.replace("http://localhost:8080/ECenema_war_exploded/BookServmovie=", "");
        title = title.replace("%20", " ");
        MovieService movieService = new MovieService();
        Movie movie = new Movie();
        try {
            movie = movieService.getByTitle(title);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        HttpSession session = request.getSession();
        session.setAttribute("movie", movie);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/moviePage.jsp");
        rd.include(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
