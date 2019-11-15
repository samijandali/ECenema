<%--
  Created by IntelliJ IDEA.
  User: hussa
  Date: 11/14/2019
  Time: 1:17 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*"%>
<html>
<head>
    <title>Search</title>
</head>
<body>
    <%
    String movie = request.getParameter("search");
    try{
    Class.forName("com.mysql.jdbc.Driver");
    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/moviesite?verifyServerCertificate=false&useSSL=true", "root", "asdasd");
    PreparedStatement stmt=con.prepareStatement("select * from movies where (title like '?') or (genre like '?')");
    stmt.setString('1', movie+"%");
    ResultSet Rs = stmt.executeQuery();
    while(Rs.next()) {
        out.println(Rs.getInt(1) + " " + Rs.getString(2) + " " + Rs.getString(3));
        out.println("<br>");
    }
    } catch(Exception ex){
        ex.getMessage();
    }

    %>
</body>
</html>
