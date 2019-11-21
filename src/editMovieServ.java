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

@WebServlet("/editMovieServ")
public class editMovieServ extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     //   doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        PrintWriter out=response.getWriter();
        String title = request.getParameter("title");
        String summary = request.getParameter("summary");
        String genre = request.getParameter("genre");
        String rating = request.getParameter("rating");
        String length = request.getParameter("length");
        String cast = request.getParameter("cast");
        String director = request.getParameter("director");
        String producer = request.getParameter("producer");
        String link = request.getParameter("link");
        String available = request.getParameter("available");
        HttpSession session = request.getSession();
        String isTitle =(String)session.getAttribute("editMovie");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite", "root", "asdasd");//"UN", "PW"
            String query = "UPDATE movie SET title = ?, summary = ?, genre = ?, rating = ?, length = ?, cast = ?, director = ?, producer = ?, link = ?, available = ? WHERE title = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, title);
            stmt.setString(2, summary);
            stmt.setString(3, genre);
            stmt.setString(4, rating);
            stmt.setString(5, length);
            stmt.setString(6, cast);
            stmt.setString(7, director);
            stmt.setString(8, producer);
            stmt.setString(9, link);
            stmt.setString(10, available);
            stmt.setString(11, isTitle);

            int success = stmt.executeUpdate();
            if (success == 1) {
                out.println("<font color=red>Movie edited successfully</font>");
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/manageMovies.jsp");
                rd.include(request, response);
                return;
            }
            out.println("<font color=red>Movie not edited successfully, please retry</font>");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/manageMovies.jsp");
            rd.include(request, response);
        }
        catch(Exception e) {
            out.print(e);
        }
    }
}
