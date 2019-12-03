import model.User;
import model.UserService;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Properties;

@WebServlet("/PromoServ")
public class PromoServ extends HttpServlet {

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
            String name = request.getParameter("name");
            int discount = Integer.parseInt(request.getParameter("discount"));
            String exp = request.getParameter("exp");
            String description = request.getParameter("description"); //if moved here
            try {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/adminPage.jsp");
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite", "root", "asdasd");//"UN", "PW"
                Statement stmt = con.createStatement();
                stmt.executeUpdate("INSERT INTO promotion (name, discount, exp, description) VALUES ('" + name + "', '" + discount + "', '" + exp + "', '" + description + "')");
                out.println("<font color=red>Promotion Successfully Added!</font>");

                UserService userService = new UserService();
                String[] emails = userService.getAllEmails();
                String uname = "miguelangello96@gmail.com";
                String pword = "pchepwzyepkhdoig";
                Properties p = new Properties();
                p.put("mail.smtp.host", "smtp.gmail.com");
                p.put("mail.smtp.port", "587");
                p.put("mail.smtp.auth", "true");
                p.put("mail.smtp.starttls.enable", "true");

                Session session = Session.getInstance(p, new javax.mail.Authenticator(){
                    protected PasswordAuthentication getPasswordAuthentication(){
                        return new PasswordAuthentication("miguelangello96@gmail.com","pchepwzyepkhdoig");
                    }//PassAuth
                });//mailAuth

                try
                {
                    Message msg = new MimeMessage(session);
                    msg.setFrom(new InternetAddress("miguelangello96@gmail.com"));
                    for(String cc:emails){
                        msg.addRecipients(Message.RecipientType.CC,InternetAddress.parse(cc));
                    }
                    msg.setSubject("NotAMC Theatre Discount Code");
                    msg.setText("NotAMC is offering a promotional discount for all movie!" +
                            " Use the code: '" + name + "' to get " + discount + "% on your next purchase!");

                    Transport.send(msg);
                }

                catch(Exception e)
                {
                    e.printStackTrace();
                }
                rd.include(request, response);
            } catch (Exception p) {
                out.print(p);
            }
            //response.sendRedirect("./index.jsp");
        } else{
            try {
                String name = request.getParameter("isTitles");
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite", "root", "asdasd");//"UN", "PW"
                Statement stmt = con.createStatement();
                int success = stmt.executeUpdate("DELETE FROM promotion WHERE name ='" + name + "'");
                if(success == 1){
                    out.println("<font color=red>" + name + " got deleted successfully</font>");
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/managePromos.jsp");
                    rd.include(request, response);
                }else{
                    out.println("<font color=red>'" + name + " got deleted successfully</font>");
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/managePromos.jsp");
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
