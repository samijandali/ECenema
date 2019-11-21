import model.MovieService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * Servlet implementation class Servlet1
 */
@WebServlet("/movieServlet")
public class movieServlet extends HttpServlet {
    private static String secretKey = "boooooooooom!!!!";
    private static String salt = "ssshhhhhhhhhhh!!!!";
    private static final long serialVersionUID = 1L;
    private MovieService movieService = new MovieService();


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite", "root", "asdasd");//"UN", "PW"
            Statement stmt = con.createStatement();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        if("add".equals(request.getParameter("hidden"))) {
            String title = request.getParameter("title");
            String summary = request.getParameter("summary");
            String genre = request.getParameter("genre");
            String rating = request.getParameter("rating");
            String length = request.getParameter("length");
            String cast = request.getParameter("cast");
            String director = request.getParameter("director");
            String producer = request.getParameter("producer");
            String review1 = request.getParameter("review1");
            String review2 = request.getParameter("review2");
            String review3 = request.getParameter("review3");
            String link = request.getParameter("link");
            int available = Integer.parseInt(request.getParameter("available"));
            try {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/manageMovies.jsp");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite", "root", "asdasd");//"UN", "PW"
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("select * from movie");
                while (rs.next()) {
                    if (rs.getString("title").equalsIgnoreCase(title)) {
                        out.println("<font color=red>Title already exits, please try another</font>");
                        rd.include(request, response);
                        return;
                    }
                }
            }

                /*if (movieService.exists(title)) {
                    out.println("<font color=red>Movie already exists, please check your entry</font>");
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/manageMovies.jsp");
                    rd.include(request, response);
                    return;*/
            //if was here
            catch (SQLException e) {
                e.printStackTrace();
            } //if moved here
            try {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/adminPage.jsp");
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite", "root", "asdasd");//"UN", "PW"
                Statement stmt = con.createStatement();
                stmt.executeUpdate("INSERT INTO movie (title, summary, genre, rating, length, cast, director, producer, review1, review2, review3, link, available) VALUES ('" + title + "', '" + summary + "', '" + genre + "', '" + rating + "', '" + length + "', '" + cast + "', '" + director + "', '" + producer + "', '" + review1 + "', '" + review2 + "', '" + review3 + "', '" + link + "', '" + available + "')");
                out.println("<font color=red>Movie Successfully Added!</font>");
                rd.include(request, response);
            } catch (Exception p) {
                out.print(p);
            }
            //response.sendRedirect("./index.jsp");
        }

        else{
            try {
                String title = request.getParameter("isTitles");
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite", "root", "asdasd");//"UN", "PW"
                Statement stmt = con.createStatement();
                int success = stmt.executeUpdate("DELETE FROM movie WHERE title ='" + title + "'");
                if(success == 1){
                    out.println("<font color=red>" + title + " got deleted successfully</font>");
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/manageMovies.jsp");
                    rd.include(request, response);
                }else{
                    out.println("<font color=red>'" + title + " wasn't deleted, please check if movie is in the database</font>");
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/manageMovies.jsp");
                    rd.include(request, response);
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }

        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);

    }

}
