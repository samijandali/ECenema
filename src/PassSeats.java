import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/PassSeats")
public class PassSeats extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] seatNbs = request.getParameterValues("seatsCheck");
        int[] seatNb = new int[seatNbs.length];
        for(int i = 0;i < seatNbs.length;i++)
        {
            seatNb[i] = Integer.parseInt(seatNbs[i]);
        }
        HttpSession session = request.getSession();
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/pricing.jsp");
        session.setAttribute("seatNbs", seatNbs);
        rd.include(request, response);
    }
}
