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

@WebServlet("/editPromoServ")
public class editPromoServ extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException {

        PrintWriter out=response.getWriter();
        String name = request.getParameter("name");
        String discount = request.getParameter("discount");
        String exp = request.getParameter("exp");
        String description = request.getParameter("description");
        HttpSession session = request.getSession();
        String isTitle =(String)session.getAttribute("editPromo");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite", "root", "asdasd");//"UN", "PW"
            String query = "UPDATE promotion SET name = ?, discount = ?, exp = ?, description = ? WHERE name = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, discount);
            stmt.setString(3, exp);
            stmt.setString(4, description);
            stmt.setString(5, isTitle);

            int success = stmt.executeUpdate();
            if (success == 1) {
                out.println("<font color=red>Promotion edited successfully</font>");
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/managePromos.jsp");
                rd.include(request, response);
                return;
            }
            out.println("<font color=red>Promotion not edited successfully, please retry</font>");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/managePromos.jsp");
            rd.include(request, response);
        }
        catch(Exception e) {
            out.print(e);
        }
    }
}
