import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/PassShowtime")
public class PassShowtime extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int showID = Integer.parseInt(request.getParameter("showtime"));
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/seating.jsp");
        HttpSession session = request.getSession();
        session.setAttribute("showtimeID", showID);
        rd.include(request, response);
    }
}
