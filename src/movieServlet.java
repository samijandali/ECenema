import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
/**
 * Servlet implementation class Servlet1
 */
@WebServlet("/movieServlet")
public class movieServlet extends HttpServlet {
    private static String secretKey = "boooooooooom!!!!";
    private static String salt = "ssshhhhhhhhhhh!!!!";
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String title = request.getParameter("title");
        String summary = request.getParameter("summary");
        String genre = request.getParameter("genre");
        int rating = Integer.parseInt(request.getParameter("rating"));
        String length = request.getParameter("length");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite","root", "asdasd");//"UN", "PW"
            Statement stmt = con.createStatement();
            stmt.executeUpdate("INSERT INTO movie (title, summary, genre, rating, length) VALUES ('"+title+"', '"+summary+"', '"+genre+"', '"+rating+"', '"+length+"')");
        } catch (Exception p) {
            out.print(p);
        }
        response.sendRedirect("./index.jsp");
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);

    }

}
