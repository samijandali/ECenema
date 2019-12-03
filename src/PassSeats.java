import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/PassSeats")
public class PassSeats extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] seatNb = request.getParameterValues("seatsCheck");
        ArrayList<String> seatNbsarr = new ArrayList<>();
        ArrayList<String> seatLocoarr = new ArrayList<>();
        for(String seat: seatNb)
        {
            Pattern pattern = Pattern.compile("- *");
            Matcher matcher = pattern.matcher(seat);
            if (matcher.find()) {
                seatNbsarr.add(seat.substring(0, matcher.start()));
                seatLocoarr.add(seat.substring(matcher.end()));
            }
        }
        String[] seatNbs = seatNbsarr.toArray(new String[seatNbsarr.size()]);
        String[] seatLoco = seatLocoarr.toArray(new String[seatLocoarr.size()]);
        HttpSession session = request.getSession();
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/pricing.jsp");
        session.setAttribute("seatNbs", seatNbs);
        session.setAttribute("seatLoc", seatLoco);
        rd.include(request, response);
    }
}
