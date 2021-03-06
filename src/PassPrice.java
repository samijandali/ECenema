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
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/PassPrice")
public class PassPrice extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int ordo = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite","root", "asdasd");//"UN", "PW"
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT id FROM orderinfo ORDER BY id DESC LIMIT 1");
            while (rs.next()){
                ordo =rs.getInt(1);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        String[] priceselect = request.getParameterValues("priceSelect");
        int[] numbers = new int[priceselect.length];
        for(int i = 0;i < priceselect.length;i++)
        {
            numbers[i] = Integer.parseInt(priceselect[i]);
        }
        Map<Integer, Integer> seatFre = countOccurences(numbers);
        HttpSession session = request.getSession();
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/checkout.jsp");
        session.setAttribute("seatFre", seatFre);
        session.setAttribute("lastordo", ordo);
        rd.include(request, response);
    }
    private  static Map<Integer, Integer> countOccurences (int[] arr) {
        int len = arr.length;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        for(int i = 0; i < len; i++) {
            int key = arr[i];
            if (map.containsKey(key)) {
                int value = map.get(key);
                map.put(key, value + 1);
            } else {
                map.put(key, 1);
            }
        }

        return map;
    }
}
