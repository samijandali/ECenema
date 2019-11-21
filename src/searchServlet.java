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
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class Servlet1
 */
@WebServlet("/searchServlet")
public class searchServlet extends HttpServlet {
    private static String secretKey = "boooooooooom!!!!";
    private static String salt = "ssshhhhhhhhhhh!!!!";
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PrintWriter out = response.getWriter();
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite?verifyServerCertificate=false&useSSL=true", "root", "asdasd");
            String Query="select * FROM movie where title = '"+request.getParameter("search")+"' or genre = '"+request.getParameter("search")+"'";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(Query);
            Movie movie = new Movie();
            HttpSession session = request.getSession();
            ArrayList<Movie> movies = new ArrayList<Movie>();
            rs.beforeFirst();
            if(!rs.next()){
                session.setAttribute("movieList", movies);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
                rd.include(request, response);
            } else {
                do {
                    movies.add(new Movie(rs.getInt(6), rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getInt(14)));
                    session.setAttribute("movieList", movies);
                } while (rs.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
        rd.include(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);

    }

}
