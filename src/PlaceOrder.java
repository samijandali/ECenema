import model.User;

import javax.management.modelmbean.RequiredModelMBean;
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
import java.util.Map;

@WebServlet("/PlaceOrder")
public class PlaceOrder extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        int showtimeID = (Integer) session.getAttribute("showtimeID");
        String[] seatNbs = (String[]) session.getAttribute("seatNbs");
        Map<Integer, Integer> seatFre = (Map) session.getAttribute("seatFre");
        User user = (User) session.getAttribute("user");
        int total = Integer.parseInt(request.getParameter("total"));
        int ccID = Integer.parseInt(request.getParameter("payment"));
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite","root", "asdasd");//"UN", "PW"
            String query = "INSERT INTO orderinfo (userid, total, showtimeID, ccID, numAdult, numKids, numSenior, numStudent) VALUES ("+user.getId()+", "+total +", "+showtimeID+", "+ccID+", ";
            Statement stmt = con.createStatement();
            if(seatFre.get(2) != null) {
                query += (seatFre.get(2)) + ", ";
            }else{
                query += "0, ";
            }
            if(seatFre.get(1) != null) {
                query += (seatFre.get(1)) + ", ";
            }else{
                query +=  "0, ";
            }
            if(seatFre.get(3) != null) {
                query += (seatFre.get(3)) + ", ";
            }else{
                query += "0, ";
            }
            if(seatFre.get(4) != null) {
                query += (seatFre.get(4))+ ")";
            }else{
                query += "0)";
            }
            stmt.addBatch(query);
            for(String seat: seatNbs) {
                int seato = Integer.parseInt(seat);
                String query1 = "UPDATE seat set status = 'disabled' where showID = " + showtimeID + " AND seatnb = "+seato+" ";
                stmt.addBatch(query1);
            }
            int[] success = stmt.executeBatch();
            if(success[0] == 1) {
                out.println("<font color=red>Order has been ordered</font>");
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/checkout.jsp");
                rd.include(request, response);
            }else{
                out.println("<font color=red>Order wasn't complete please check your order again</font>");
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/checkout.jsp");
                rd.include(request, response);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
