import model.PriceService;
import model.PromotionService;
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
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Map;
import java.util.Properties;

@WebServlet("/PlaceOrder")
public class PlaceOrder extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        DecimalFormat df = new DecimalFormat("#.##");
        PrintWriter out = response.getWriter();
        double discount = 1.0;
        int promoID = 0;
        PromotionService promotionService = new PromotionService();
        if(request.getParameter("promo")!=null){
            String promo = request.getParameter("promo");
            try {
                if(promotionService.exists(promo)){
                    if(!promotionService.expired(promo)) {
                        promoID = promotionService.getIDByName(promo);
                        discount = promotionService.getDiscount(promo) / 100.00;
                    }else{
                        out.println("<font color=red>Promo is expired</font>");
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/checkout.jsp");
                        rd.include(request, response);
                        return;
                    }
                }else{
                    out.println("<font color=red>Promo doesn't exist, please check your code</font>");
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/checkout.jsp");
                    rd.include(request, response);
                    return;
                }
            } catch (ClassNotFoundException | ParseException | SQLException e) {
                e.printStackTrace();
            }
        }
        int showtimeID = (Integer) session.getAttribute("showtimeID");
        String[] seatNbs = (String[]) session.getAttribute("seatNbs");
        String[] seatLoc =  (String[]) session.getAttribute("seatLoc");
        String seatnabs = String.join(", ", seatNbs);
        String seatlocos = String.join(", ", seatLoc);
        Map<Integer, Integer> seatFre = (Map) session.getAttribute("seatFre");
        User user = (User) session.getAttribute("user");
        double total = Double.parseDouble(request.getParameter("total"));
        session.setAttribute("discount", discount);
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
            String query = "INSERT INTO orderinfo (userid, total, showtimeID, ccID, numAdult, numKids, numSenior, numStudent) VALUES ("+user.getId()+", "+df.format(total * (1.0 - discount)) +", "+showtimeID+", "+ccID+", ";
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
            String queue3 = "UPDATE orderinfo" +
                    " SET promoID = '"+promoID+"'" +
                    " ORDER BY id DESC" +
                    " LIMIT 1";
            stmt.addBatch(queue3);
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
                mess += "Total Before Discount: $" + total;
                mess += "Discount: " + discount * 100 + "%";
                mess += "Total After Discount: $" + df.format(total * (1.0 - discount));
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
