import model.Movie;
import model.Promotion;
import model.User;

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
import java.util.ArrayList;

@WebServlet("/adminServ")
public class adminServ extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/adminPage.jsp");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite?verifyServerCertificate=false&useSSL=true", "root", "asdasd");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from users");
            HttpSession session = request.getSession();
            ArrayList<User> users = new ArrayList<>();
            rs.beforeFirst();
            if (!rs.next()) {
                session.setAttribute("userlist", users);
            } else {
                do {
                    users.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getInt(14), rs.getString(15), rs.getInt(16)));
                    session.setAttribute("userlist", users);
                } while (rs.next());

            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        try {
            PrintWriter out = response.getWriter();
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite?verifyServerCertificate=false&useSSL=true", "root", "asdasd");
            String Query = "select * from movie";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(Query);
            Movie movie = new Movie();
            HttpSession session = request.getSession();
            ArrayList<Movie> movies = new ArrayList<Movie>();
            rs.beforeFirst();
            if (!rs.next()) {
                session.setAttribute("movieList", movies);
            } else {
                do {
                    movies.add(new Movie(rs.getInt(6), rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getInt(14)));
                    session.setAttribute("movieList", movies);
                } while (rs.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite?verifyServerCertificate=false&useSSL=true", "root", "asdasd");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from promotion");
            HttpSession session = request.getSession();
            ArrayList<Promotion> promos = new ArrayList<>();
            rs.beforeFirst();
            if (!rs.next()) {
                session.setAttribute("promolist", promos);
            } else {
                do {
                    promos.add(new Promotion(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5)));
                    session.setAttribute("promolist", promos);
                } while (rs.next());

            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        rd.include(request, response);
    }
}
