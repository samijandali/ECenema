import model.PriceService;
import model.ShowtimeService;
import model.User;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
import java.util.Properties;

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
        String[] seatLoc =  (String[]) session.getAttribute("seatLoc");
        String seatnabs = String.join(", ", seatNbs);
        String seatlocos = String.join(", ", seatLoc);
        System.out.println(seatnabs);
        Map<Integer, Integer> seatFre = (Map) session.getAttribute("seatFre");
        User user = (User) session.getAttribute("user");
        int total = Integer.parseInt(request.getParameter("total"));
        int ccID = Integer.parseInt(request.getParameter("payment"));
        Properties p = new Properties();
        p.put("mail.smtp.host", "smtp.gmail.com");
        p.put("mail.smtp.port", "587");
        p.put("mail.smtp.auth", "true");
        p.put("mail.smtp.starttls.enable", "true");
        Session sess = Session.getInstance(p, new javax.mail.Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication("miguelangello96@gmail.com","pchepwzyepkhdoig");
            }//PassAuth
        });//mailAuth

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
            String queue = "UPDATE orderinfo" +
                    " SET seatNbs = '"+seatnabs+"'" +
                    " ORDER BY id DESC" +
                    " LIMIT 1";
            stmt.addBatch(queue);
            String queue2 = "UPDATE orderinfo" +
                    " SET seatLoc = '"+seatlocos+"'" +
                    " ORDER BY id DESC" +
                    " LIMIT 1";
            stmt.addBatch(queue2);
            System.out.println(session.getAttribute("lastordo"));
            int ordernum = (Integer) session.getAttribute("lastordo") + 1;
            for(String seat: seatNbs) {
                String query1 = "UPDATE seat set status = 'disabled', orderID = "+ ordernum +" where id = "+seat+"";
                stmt.addBatch(query1);
            }
            int[] success = stmt.executeBatch();
            if(success[0] == 1) {
                Message msg = new MimeMessage(sess);
                msg.setFrom(new InternetAddress("miguelangello96@gmail.com"));
                msg.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(user.getEmail())
                );
                msg.setSubject("NotAMC Theatre Order Confirmation Message");
                String mess = "Thanks for placing the order with our awesome cool theatre. Here's your order details:\n";
                ShowtimeService showtimeService = new ShowtimeService();
                PriceService priceService = new PriceService();
                String movie = showtimeService.getMovie(showtimeID);
                mess += "Movie: " + movie;
                mess += "\nSeats Numbers: ";
                mess += seatlocos;
                mess += "\nSeat Type and Count: ";
                for (Map.Entry<Integer,Integer> entry : seatFre.entrySet()) {
                    mess += "\n";
                    mess += priceService.getByID(entry.getKey());
                    mess += " x ";
                    mess += entry.getValue();
                }
                msg.setText(mess);
                Transport.send(msg);
                out.println("<font color=red>Order has been ordered</font>");
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/confo.jsp");
                rd.include(request, response);
            }else{
                out.println("<font color=red>Order wasn't complete please check your order again</font>");
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/confo.jsp");
                rd.include(request, response);
            }
        } catch (SQLException | ClassNotFoundException | MessagingException e) {
            e.printStackTrace();
        }
    }
}
