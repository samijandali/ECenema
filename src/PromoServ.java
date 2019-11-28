import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

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
            String description = request.getParameter("description");
            try {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/manageMovies.jsp");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite", "root", "asdasd");//"UN", "PW"
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("select * from promotion");
                out.print(rs.getString("name") + "and " + name + " and " + request.getParameter("name"));

                while (rs.next()) {
                    if (rs.getString("name").equalsIgnoreCase(name)) {
                        out.println("<font color=red>Promotion already exits, please try another</font>");
                        rd.include(request, response);
                        return;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } //if moved here
            try {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/adminPage.jsp");
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite", "root", "asdasd");//"UN", "PW"
                Statement stmt = con.createStatement();
                stmt.executeUpdate("INSERT INTO promotion (name, discount, exp, description) VALUES ('" + name + "', '" + discount + "', '" + exp + "', '" + description + "')");
                out.println("<font color=red>Promotion Successfully Added!</font>");
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
