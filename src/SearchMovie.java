import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;
import java.util.*;

/**
 * Servlet implementation class SearchMovie
 */
@WebServlet("/SearchMovie")
public class SearchMovie extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("searchResult.jsp");
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/searchresult.jsp");
        HttpSession session = request.getSession(true);
        List movielist = new ArrayList();

        //String searchType = "";
        String search = "";

        //searchType = request.getParameter("searchType");
        search = request.getParameter("search");

        String sqlStr = null;

        if(search.equals("title"))
            sqlStr = "Select id, title, summary, genre, rating, length FROM movie where title like '%" + search + "%'";
        else if(search.equals("genre"))
            sqlStr = "Select id, title, summary, genre, rating, length FROM movie where genre like '%" + search + "%'";
            else
            System.out.println("How did you not select from the drop down table?");
        System.out.println(sqlStr);
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite?verifyServerCertificate=false&useSSL=true", "root", "asdasd");
            try{

                Statement statement = con.createStatement();
                ResultSet rs = statement.executeQuery(sqlStr);
                while (rs.next()) {
                    List movie = new ArrayList();
                    movie.add(rs.getInt(1));
                    movie.add(rs.getString(2));
                    movie.add(rs.getString(3));
                    movie.add(rs.getString(4));
                    movie.add(rs.getString(5));
                    movie.add(rs.getString(6));

                    movielist.add(movie);
                }
            } catch (SQLException s){
                System.out.println("Value could not be found");
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        request.setAttribute("movielist", movielist);
        dispatcher.forward(request, response);
    }


    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

}
